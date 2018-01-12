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
import lapr.project.model.Network;
import lapr.project.model.Project;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAONetwork extends DAOManager {

    private final Project projectRef;

    /**
     * Name of the function in the database that adds Networks
     */
    private static final String ADD_NETWORKS_PROCEDURE = "{call proc_insert_network(?,?,?)}";

    /**
     * Name of the function in the database that gets Networks
     */
    private static final String GET_NETWORKS_PROCEDURE = "{call proc_getNetwork(?,?)}";

    public DAONetwork(Project p) throws SQLException {
        super(ADD_NETWORKS_PROCEDURE, GET_NETWORKS_PROCEDURE);
        this.projectRef = p;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable networkToAdd) throws SQLException {
        Network net = (Network) networkToAdd;

        cs.setString(1, net.getId());
        cs.setString(2, net.getDescription());
        cs.setString(3, projectRef.getName());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        Network net = (Network) placeToAdd;
        String projectName = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, projectName);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            String id = rs.getString("id");
            String desc = rs.getString("description");

            if (net != null) {
                net.setId(id);
                net.setDescription(desc);
            } else {
                net = new Network();
                net.setId(id);
                net.setDescription(desc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
