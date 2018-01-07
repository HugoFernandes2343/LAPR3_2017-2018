/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Road;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAORoad extends DAOManager {

    /**
     * Name of the function in the database that adds roads
     */
    private static final String ADD_ROADS_PROCEDURE = "{call proc_addRoad(?,?)}";

    /**
     * Name of the function in the database that gets roads
     */
    private static final String GET_ROADS_PROCEDURE = "{call proc_getRoad(?)}";

    public DAORoad() throws SQLException {
        super(ADD_ROADS_PROCEDURE, GET_ROADS_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Road road = (Road) data;

        cs.setString(1, road.getId());
        cs.setString(2, road.getName());
        cs.setString(3, road.getTypology());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
