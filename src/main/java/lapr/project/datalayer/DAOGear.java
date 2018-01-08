package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Gear;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseExchangable;

public class DAOGear extends DAOManager {

    /**
     * Name of the function in the database that adds entries to the
     * VelocityLimit table
     */
    private static final String ADD_GEAR_PROCEDURE = "{call proc_insert_gear(?,?,?)}";

    /**
     * Name of the function in the database that gets VelocityLimits
     */
    private static final String GET_GEAR_PROCEDURE = "{call proc_getProject(?)}";

    private Vehicle v;

    public DAOGear(String addProcedure, String readProcedure) throws SQLException {
        super(ADD_GEAR_PROCEDURE, GET_GEAR_PROCEDURE);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
