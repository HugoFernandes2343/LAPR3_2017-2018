package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Energy;
import lapr.project.model.Regime;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseExchangable;

public class DAOEnergy extends DAOManager {

    /**
     * Name of the function in the database that adds entries to the
     * VelocityLimit table
     */
    private static final String ADD_ENERGY_PROCEDURE = "{call proc_insert_energy(?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets VelocityLimits
     */
    private static final String GET_ENERGY_PROCEDURE = "{call proc_getProject(?)}";

    private Vehicle v;

    public DAOEnergy(Vehicle v) throws SQLException {
        super(ADD_ENERGY_PROCEDURE, GET_ENERGY_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Energy energy = (Energy) data;

        cs.setString(1, v.getName());
        cs.setDouble(2, energy.getMinRpm());
        cs.setDouble(3, energy.getMaxRpm());
        cs.setDouble(4, energy.getFinalDriveRatio());
        cs.setDouble(5, energy.getErr());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
