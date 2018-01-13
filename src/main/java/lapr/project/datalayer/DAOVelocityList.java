package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Vehicle;
import lapr.project.model.VelocityLimit;
import lapr.project.model.VelocityLimitList;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAOVelocityList extends DAOManager {

    /**
     * Name of the function in the database that adds entries to the
     * VelocityLimit table
     */
    private static final String ADD_VELOCITY_LIST_PROCEDURE = "{call proc_insert_velocity_limit(?,?,?)}";

    /**
     * Name of the function in the database that gets VelocityLimits
     */
    private static final String GET_VELOCITY_LIST_PROCEDURE = "{call proc_get_velocity_limit(?,?)}";

    private Vehicle v;

    public DAOVelocityList(Vehicle v) throws SQLException {
        super(ADD_VELOCITY_LIST_PROCEDURE, GET_VELOCITY_LIST_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        VelocityLimit limit = (VelocityLimit) data;

//        if (limit == null) {
//
//        }

        cs.setString(1, v.getName());
        cs.setString(2, limit.getSegmentType());
        cs.setDouble(3, limit.getLimit());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        VelocityLimitList vList = (VelocityLimitList) placeToAdd;
        String vehicleName = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, vehicleName);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String segType = rs.getString("Segment_Type");
                int limit = rs.getInt("Limit");
                VelocityLimit newLimit = new VelocityLimit(segType, limit);
                vList.getVelocityLimitList().add(newLimit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOVelocityList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
