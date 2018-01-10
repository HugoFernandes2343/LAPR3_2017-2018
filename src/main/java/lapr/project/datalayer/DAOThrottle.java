/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAOThrottle extends DAOManager {

    /**
     * Name of the function in the database that adds vehicles
     */
    private static final String ADD_THROTTLE_PROCEDURE = "{call proc_insert_throttle(?,?)}";

    /**
     * Name of the function in the database that gets vehicles
     */
    private static final String GET_THROTTLE_PROCEDURE = "{call proc_getProject(?)}";

    private Vehicle v;

    public DAOThrottle(Vehicle v) throws SQLException {
        super(ADD_THROTTLE_PROCEDURE, GET_THROTTLE_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Throttle list = (Throttle) data;

        cs.setString(1, v.getName());
        cs.setDouble(2, Double.parseDouble(list.getPercentage()));
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> tList = placeToAdd.getDBData();
        String vehicleName = (String) references[0];
        ResultSet rs = null;
        
        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, vehicleName);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);
            
            while(rs.next()){
                String percentage = rs.getString("percentage");
                Throttle t = new Throttle();
                t.setPercentage(percentage);
                tList.add(t);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOGear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
