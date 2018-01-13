/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Road;
import lapr.project.model.TollClass;
import lapr.project.model.TollFare;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAOTollClass extends DAOManager {

    /**
     * Name of the function in the database that adds TollClasses
     */
    private static final String ADD_TOLLCLASS_PROCEDURE = "{call proc_insert_toll_class(?,?,?)}";

    /**
     * Name of the function in the database that gets TollClasses
     */
    private static final String GET_TOLLCLASSES_PROCEDURE = "{call proc_get_toll_class(?,?)}";

    private Road r;

    public DAOTollClass(Road r) throws SQLException {
        super(ADD_TOLLCLASS_PROCEDURE, GET_TOLLCLASSES_PROCEDURE);
        this.r = r;

    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        TollClass tc = (TollClass) data;

        cs.setString(1, tc.getId());
        cs.setString(2, r.getId());
        cs.setDouble(3, tc.getPrice());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        TollFare tc = (TollFare) placeToAdd;
        String roadID = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, roadID);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String toll_id = String.valueOf(rs.getInt("CLASS_ID"));
                double price = rs.getDouble("PRICE");

                tc.addClass(toll_id, price);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DAOVehicle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }

}
