/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Network;
import lapr.project.model.Project;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAONetwork extends DAOManager {

    private Project projectRef;
    
    /**
     * Name of the function in the database that adds Networks
     */
    private static final String ADD_NETWORKS_PROCEDURE = "{call proc_addNetwork(?,?)}";

    /**
     * Name of the function in the database that gets Networks
     */
    private static final String GET_NETWORKS_PROCEDURE = "{call proc_getNetwork(?)}";
    
    public DAONetwork(Project p) throws SQLException {
        super(ADD_NETWORKS_PROCEDURE, GET_NETWORKS_PROCEDURE);
        this.projectRef=p;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable networkToAdd) throws SQLException {
        Network net = (Network) networkToAdd;

        cs.setString(1, net.getId());
        cs.setString(2, net.getDescription());
    }
    
    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
