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
import lapr.project.model.User;
import lapr.project.model.UserList;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAOUser extends DAOManager {

    /**
     * Name of the function in the database that adds vehicles
     */
    private static final String ADD_USERS_PROCEDURE = "{call proc_addUser(?,?)}";

    /**
     * Name of the function in the database that gets vehicles
     */
    private static final String GET_USERS_PROCEDURE = "{call proc_getUsers(?)}";

    public DAOUser() throws SQLException {
        super(ADD_USERS_PROCEDURE, GET_USERS_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        UserList list = (UserList) placeToAdd;
        ResultSet rs = null;
        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.executeQuery();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String userName = rs.getString("USERNAME");
                int charKey = rs.getInt("CHARKEY");
                String name = rs.getString("NAME");
                String pass = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                User user = new User(userName, charKey, name, pass, email);
                list.getUserList().add(user);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error on the caller");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
