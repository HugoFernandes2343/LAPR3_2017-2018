/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Project;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAOProject extends DAOManager{

    /**
     * Name of the function in the database that adds vehicles
     */
    private static final String ADD_PROJECT_PROCEDURE = "{call proc_addProject(?,?)}";

    /**
     * Name of the function in the database that gets vehicles
     */
    private static final String GET_PROJECTS_PROCEDURE = "{call proc_getProject(?)}";
    
    public DAOProject() throws SQLException {
        super(ADD_PROJECT_PROCEDURE, GET_PROJECTS_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable projectToAdd) throws SQLException {
        Project p = (Project) projectToAdd;

        cs.setString(1, p.getName());
        cs.setString(2, p.getDescription());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
