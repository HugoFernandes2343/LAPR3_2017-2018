/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAOTollClass extends DAOManager {

    /**
     * Name of the function in the database that adds TollClasses
     */
    private static final String ADD_TOLLCLASS_PROCEDURE = "{call proc_insert_tollclass(?,?,?)}";

    /**
     * Name of the function in the database that gets TollClasses
     */
    private static final String GET_TOLLCLASSES_PROCEDURE = "{call proc_get_tollclasses(?,?)}";

    public DAOTollClass() throws SQLException {
        super(ADD_TOLLCLASS_PROCEDURE, GET_TOLLCLASSES_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
