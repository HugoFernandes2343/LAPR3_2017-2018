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
import lapr.project.model.Network;
import lapr.project.model.Node;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAONode extends DAOManager {

    /**
     * Name of the function in the database that adds Nodes
     */
    private static final String ADD_NODE_PROCEDURE = "{call proc_insert_node(?,?)}";

    /**
     * Name of the function in the database that gets Nodes
     */
    private static final String GET_NODE_PROCEDURE = "{call proc_get_node(?,?)}";

    private Network net;

    public DAONode(Network net) throws SQLException {
        super(ADD_NODE_PROCEDURE, GET_NODE_PROCEDURE);
        this.net = net;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Node n = (Node) data;

        cs.setString(1, n.getId());
        cs.setString(2, net.getId());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> nList = placeToAdd.getDBData();
        String networkID = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, networkID);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while(rs.next()){
                String id = rs.getString("ID");
                Node node = new Node(id);
                nList.add(node);
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
