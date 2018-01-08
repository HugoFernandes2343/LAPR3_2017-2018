/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.NetworkAnalysis;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAONetworkAnalysis extends DAOManager {

    /**
     * Name of the function in the database that adds analysis
     */
    private static final String ADD_ANALYSIS_PROCEDURE = "{call proc_insert_network_analysis(?,?,?,?,?,?,?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets analysis
     */
    private static final String GET_ANALYSIS_PROCEDURE = "{call proc_getAnalysis(?,?)}";

    public DAONetworkAnalysis() throws SQLException {
        super(ADD_ANALYSIS_PROCEDURE, GET_ANALYSIS_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        NetworkAnalysis netAnalysis = (NetworkAnalysis) data;

        cs.setInt(1, netAnalysis.getId());
        cs.setString(2, netAnalysis.getType());
        cs.setString(3, netAnalysis.getName());
        cs.setDouble(4, netAnalysis.getTravellTime());
        cs.setDouble(5, netAnalysis.getEnergyConsumption());
        cs.setDouble(6, netAnalysis.getAverageVelocity());
        cs.setDouble(7, netAnalysis.getDistance());
        cs.setDouble(8, netAnalysis.getTollCost());
        cs.setDouble(9, netAnalysis.getLoad());
        cs.setDouble(10, netAnalysis.getFuelMass());
        cs.setDouble(11, netAnalysis.getFuelVolume());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
