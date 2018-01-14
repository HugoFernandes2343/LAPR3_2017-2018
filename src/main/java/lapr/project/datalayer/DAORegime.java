package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Regime;
import lapr.project.model.Throttle;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAORegime extends DAOManager {

    /**
     * Gets the hihgest If from the DB
     */
    private static final String HIGHEST_REGIME_ID_IN_DB = "{call proc_get_number_regime(?)}";

    /**
     * Name of the function in the database that adds Regimes
     */
    private static final String ADD_REGIME_PROCEDURE = "{call proc_insert_regime(?,?,?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets Regimes
     */
    private static final String GET_REGIME_PROCEDURE = "{call proc_get_regime(?,?)}";

    private static int flag = 999;
    private int id_ph;
    private Throttle t;

    public DAORegime(Throttle t) throws SQLException {
        super(ADD_REGIME_PROCEDURE, GET_REGIME_PROCEDURE);
        this.t = t;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Regime regime = (Regime) data;

        if (regime != null && regime.getSfc() != null) {
            cs.setInt(1, t.getId());
            cs.setInt(2, regime.getId());
            cs.setDouble(3, regime.getTorqueHigh());
            cs.setDouble(4, regime.getTorqueLow());
            cs.setDouble(5, regime.getRpmLow());
            cs.setDouble(6, regime.getRpmHigh());
            cs.setDouble(7, regime.getSfc());
        } else if (regime != null && regime.getSfc() == null) {
            cs.setInt(1, t.getId());
            cs.setInt(2, regime.getId());
            cs.setDouble(3, regime.getTorqueHigh());
            cs.setDouble(4, regime.getTorqueLow());
            cs.setDouble(5, regime.getRpmLow());
            cs.setDouble(6, regime.getRpmHigh());
            cs.setDouble(7, 0);
        } else {
            cs.setInt(1, t.getId());
            cs.setInt(2, flag);
            cs.setDouble(3, 1);
            cs.setDouble(4, 1);
            cs.setDouble(5, 1);
            cs.setDouble(6, 1);
            cs.setDouble(7, 0);
            flag--;
        }
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> rList = placeToAdd.getDBData();
//        String percentage = (String) references[1];
        int throttleId = (int) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
//            stmt.setString(2, percentage);
            stmt.setInt(2, throttleId);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                int torqueHigh = rs.getInt("torqueHigh");
                int torqueLow = rs.getInt("torqueLow");
                int rpmLow = rs.getInt("rpmLow");
                int rpmHigh = rs.getInt("rpmHigh");
                Double sfc = rs.getDouble("sfc");
                Regime r = new Regime(torqueHigh, torqueLow, rpmLow, rpmHigh, sfc);
                rList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORegime.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public int getHighestIDinRegime(Connection con) throws SQLException {
        int index = 0;
        ResultSet rs = null;
        try (CallableStatement cs = con.prepareCall(HIGHEST_REGIME_ID_IN_DB)) {
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                index = rs.getInt("nr_max");
            }
            return index;
        } catch (NullPointerException ex) {
            Logger.getLogger(DAONetworkAnalysis.class.getName()).log(Level.SEVERE, "No Data in DB", ex);
            index = 0;
            return index;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
