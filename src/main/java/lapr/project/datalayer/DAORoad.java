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
import lapr.project.model.Road;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAORoad extends DAOManager {

    /**
     * Name of the function in the database that adds roads
     */
    private static final String ADD_ROADS_PROCEDURE = "{call proc_insert_Road(?,?,?,?)}";

    /**
     * Name of the function in the database that gets roads
     */
    private static final String GET_ROADS_PROCEDURE = "{call proc_get_Road(?,?)}";

    private Network net;

    public DAORoad(Network net) throws SQLException {
        super(ADD_ROADS_PROCEDURE, GET_ROADS_PROCEDURE);
        this.net = net;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Road road = (Road) data;

        cs.setString(1, road.getId());
        cs.setString(2, road.getName());
        cs.setString(3, road.getTypology());
        cs.setString(4, net.getId());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> rList = placeToAdd.getDBData();
        String networkId = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, networkId);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String typo = rs.getString("typology");
                Road r = new Road(name,typo,id);
                rList.add(r);
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
