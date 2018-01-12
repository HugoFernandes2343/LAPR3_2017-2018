package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Energy;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAOEnergy extends DAOManager {

    /**
     * Name of the function in the database that adds entries to the
     * VelocityLimit table
     */
    private static final String ADD_ENERGY_PROCEDURE = "{call proc_insert_energy(?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets VelocityLimits
     */
    private static final String GET_ENERGY_PROCEDURE = "{call proc_getProject(?,?)}";

    private Vehicle v;

    public DAOEnergy(Vehicle v) throws SQLException {
        super(ADD_ENERGY_PROCEDURE, GET_ENERGY_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Energy energy = (Energy) data;

        cs.setString(1, v.getName());
        cs.setInt(2, energy.getMinRpm());
        cs.setInt(3, energy.getMaxRpm());
        cs.setDouble(4, energy.getFinalDriveRatio());
        cs.setDouble(5, energy.getErr());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        Energy en = (Energy) placeToAdd;
        String vehicleName = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, vehicleName);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            int minRPM = rs.getInt("min_rpm");
            int maxRPM = rs.getInt("max_rpm");
            Double finalDriveRatio = rs.getDouble("final_drive_ratio");
            double err = rs.getDouble("err");

            if (en != null) {
                en.setMinRpm(minRPM);
                en.setMaxRpm(maxRPM);
                en.setFinalDriveRatio(finalDriveRatio);
                en.setErr(err);
            } else {
                en = new Energy();
                en.setMinRpm(minRPM);
                en.setMaxRpm(maxRPM);
                en.setFinalDriveRatio(finalDriveRatio);
                en.setErr(err);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DAOEnergy.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
