package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Vehicle;
import lapr.project.model.Gear;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAOGear extends DAOManager {

    /**
     * Name of the function in the database that adds entries to the
     * VelocityLimit table
     */
    private static final String ADD_GEAR_PROCEDURE = "{call proc_insert_gear(?,?,?)}";

    /**
     * Name of the function in the database that gets VelocityLimits
     */
    private static final String GET_GEAR_PROCEDURE = "{call proc_get_gear(?,?)}";

    private Vehicle v;

    public DAOGear(Vehicle v) throws SQLException {
        super(ADD_GEAR_PROCEDURE, GET_GEAR_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Gear gear = (Gear) data;

        cs.setString(1, gear.getId());
        cs.setString(2, v.getName());
        cs.setDouble(3, gear.getRatio());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> gList = placeToAdd.getDBData();
        String vehicleName = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, vehicleName);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String id = rs.getString("id");
                double ratio = rs.getDouble("ratio");
                Gear g = new Gear(id, ratio);
                gList.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOGear.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
