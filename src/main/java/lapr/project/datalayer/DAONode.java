/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Node;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAONode extends DAOManager{

    /**
     * Name of the function in the database that adds Nodes
     */
    private static final String ADD_NODE_PROCEDURE = "{call proc_addNode(?,?)}";

    /**
     * Name of the function in the database that gets Nodes
     */
    private static final String GET_NODE_PROCEDURE = "{call proc_getNode(?,?)}";
    
    public DAONode() throws SQLException {
        super(ADD_NODE_PROCEDURE, GET_NODE_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Node n = (Node) data;
        
        cs.setString(1, n.getId());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
