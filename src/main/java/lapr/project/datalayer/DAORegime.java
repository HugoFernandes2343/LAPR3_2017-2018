package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Regime;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAORegime extends DAOManager {

    /**
     * Name of the function in the database that adds Regimes
     */
    private static final String ADD_REGIME_PROCEDURE = "{call proc_insert_regime(?,?,?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets Regimes
     */
    private static final String GET_REGIME_PROCEDURE = "{call proc_get_regime(?,?,?)}";

    private Throttle t;

    public DAORegime(Throttle t) throws SQLException {
        super(ADD_REGIME_PROCEDURE, GET_REGIME_PROCEDURE);
        this.t = t;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Regime regime = (Regime) data;

        cs.setInt(1, t.getId());
        cs.setString(2, t.getPercentage());
        cs.setDouble(3, regime.getTorqueHigh());
        cs.setDouble(4, regime.getTorqueLow());
        cs.setDouble(5, regime.getRpmLow());
        cs.setDouble(6, regime.getRpmHigh());
        cs.setDouble(7, regime.getSfc());

    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> rList = placeToAdd.getDBData();
        String percentage = (String) references[0];
        int throttleId = (int) references[1];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, percentage);
            stmt.setInt(3, throttleId);
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
}
