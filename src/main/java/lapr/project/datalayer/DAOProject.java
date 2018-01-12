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
import lapr.project.model.Project;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAOProject extends DAOManager{

    /**
     * Name of the function in the database that adds vehicles
     */
    private static final String ADD_PROJECT_PROCEDURE = "{call proc_insert_project(?,?)}";

    /**
     * Name of the function in the database that gets vehicles
     */
    private static final String GET_PROJECTS_PROCEDURE = "{call proc_get_project(?)}";
    
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
        List<DatabaseExchangable> pList = placeToAdd.getDBData();
        ResultSet rs = null;
        
        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);
            
            while(rs.next()){
                String name = rs.getString("name");
                String desc = rs.getString("description");
                Project p = new Project(name, desc);
                pList.add(p);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    
}
