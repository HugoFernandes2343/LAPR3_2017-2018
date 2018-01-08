package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Vehicle;
import lapr.project.model.VelocityLimit;
import lapr.project.utils.DatabaseExchangable;

public class DAOVelocityList extends DAOManager {

    /**
     * Name of the function in the database that adds entries to the
     * VelocityLimit table
     */
    private static final String ADD_VELOCITY_LIST_PROCEDURE = "{call proc_insert_velocity_limit(?,?,?)}";

    /**
     * Name of the function in the database that gets VelocityLimits
     */
    private static final String GET_VELOCITY_LIST_PROCEDURE = "{call proc_getProject(?)}";

    private Vehicle v;

    public DAOVelocityList(Vehicle v) throws SQLException {
        super(ADD_VELOCITY_LIST_PROCEDURE, GET_VELOCITY_LIST_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        VelocityLimit list = (VelocityLimit) data;

        cs.setString(1, v.getName());
        cs.setString(2, list.getSegmentType());
        cs.setDouble(3, list.getLimit());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
