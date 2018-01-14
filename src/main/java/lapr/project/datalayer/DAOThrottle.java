/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
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
     * Gets the hihgest If from the DB
     */
    private static final String HIGHEST_THROTTLE_ID_IN_DB = "{call proc_get_number_throttle(?)}";

    /**
     * Name of the function in the database that adds throttle
     */
    private static final String ADD_THROTTLE_PROCEDURE = "{call proc_insert_throttle(?,?,?)}";

    /**
     * Name of the function in the database that gets throttles
     */
    private static final String GET_THROTTLE_PROCEDURE = "{call proc_get_throttle(?,?)}";

    private Vehicle v;

    public DAOThrottle(Vehicle v) throws SQLException {
        super(ADD_THROTTLE_PROCEDURE, GET_THROTTLE_PROCEDURE);
        this.v = v;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Throttle throttle = (Throttle) data;

        cs.setString(1, v.getName());
        cs.setInt(2, Integer.parseInt(throttle.getPercentage()));
        cs.setInt(3, throttle.getId());
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

            while (rs.next()) {
                int id = rs.getInt("id");
                int percentage = rs.getInt("percentage");
                Throttle t = new Throttle();
                t.setPercentage(String.valueOf(percentage));
                t.setId(id);
                tList.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOThrottle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public int getHightestIDinThrottle(Connection con) throws SQLException {
        int index = 0;
        ResultSet rs = null;
        try (CallableStatement cs = con.prepareCall(HIGHEST_THROTTLE_ID_IN_DB)) {
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                index = rs.getInt("nr_max");
            }
            return index;
        } catch (NullPointerException ex) {
            Logger.getLogger(DAOThrottle.class.getName()).log(Level.SEVERE, "No Data in DB", ex);
            index = 0;
            return index;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
