/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAONetworkAnalysis extends DAOManager {

    private Project p;

    /**
     * Returns the highest ID present in the DB
     */
    private static final String HIGHEST_NETANAL_ID_IN_DB = "{call proc_get_number_analysis(?)}";

    /**
     * Name of the function in the database that adds analysis
     */
    private static final String ADD_ANALYSIS_PROCEDURE = "{call proc_insert_network_analysis(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets analysis
     */
    private static final String GET_ANALYSIS_PROCEDURE = "{call proc_get_analysis(?,?,?,?,?,?)}";

    public DAONetworkAnalysis(Project p) throws SQLException {
        super(ADD_ANALYSIS_PROCEDURE, GET_ANALYSIS_PROCEDURE);
        this.p = p;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        NetworkAnalysis netAnalysis = (NetworkAnalysis) data;

        cs.setInt(1, netAnalysis.getId());
        cs.setString(2, p.getName());
        cs.setString(3, netAnalysis.getVehicle().getName());
        cs.setString(4, netAnalysis.getBeginNode().getId());
        cs.setString(5, netAnalysis.getEndNode().getId());
        cs.setString(6, netAnalysis.getType());
        cs.setString(7, netAnalysis.getName());
        cs.setDouble(8, netAnalysis.getTravellTime());
        cs.setDouble(9, netAnalysis.getEnergyConsumption());
        cs.setDouble(10, netAnalysis.getAverageVelocity());
        cs.setDouble(11, netAnalysis.getDistance());
        cs.setDouble(12, netAnalysis.getTollCost());
        cs.setDouble(13, netAnalysis.getLoad());
        cs.setDouble(14, netAnalysis.getFuelMass());
        cs.setDouble(15, netAnalysis.getFuelVolume());
        if (netAnalysis.getType().equalsIgnoreCase("Shortest Travel Time")) {
            cs.setString(16, "N/A");
            cs.setString(17, "N/A");
        } else {
            cs.setString(16, String.valueOf(netAnalysis.getAceleratingAcceleration()));
            cs.setString(17, String.valueOf(netAnalysis.getBrakingAcceleration()));
        }

    }

    public int getHightestIDinNetwork(Connection con) throws SQLException {
        int index = 0;
        ResultSet rs = null;
        try (CallableStatement cs = con.prepareCall(HIGHEST_NETANAL_ID_IN_DB)) {
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                index = rs.getInt("nr_max");
            }
            return index;
        } catch (NullPointerException ex) {
            Logger.getLogger(DAONetworkAnalysis.class.getName()).log(Level.SEVERE, "No Data in DB", ex);
            index = 0;
            return index;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> netList = placeToAdd.getDBData();
        String projectName = (String) references[0];
        Vehicle vehicle = (Vehicle) references[1];
        String vehicleName = vehicle.getName();
        Node endNode = (Node) references[2];
        String endNodeId = endNode.getId();
        Node beginNode = (Node) references[3];
        String beginNodeId = beginNode.getId();
        String typeRef = (String) references[4];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, projectName);
            stmt.setString(3, vehicleName);
            stmt.setString(4, endNodeId);
            stmt.setString(5, beginNodeId);
            stmt.setString(6, typeRef);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String type = rs.getString("TYPE");
                String name = rs.getString("NAME");
                double travelTime = rs.getDouble("TRAVEL_TIME");
                double energyConsumption = rs.getDouble("ENERGY_CONSUMPTION");
                double averageVelocity = rs.getDouble("AVERAGE_VELOCITY");
                double distance = rs.getDouble("DISTANCE");
                double tollCost = rs.getDouble("TOLL_COST");
                int load = rs.getInt("LOAD");
                double fuelMass = rs.getFloat("FUEL_MASS");
                double fuelVolume = rs.getFloat("FUEL_VOLUME");
                String aceleratingAcceleration = rs.getString("ACELERATING_ACCELERATION");
                String brakingAcceleration = rs.getString("BRAKING_ACCELERATION");

                NetworkAnalysis netAnal = new NetworkAnalysis(beginNode, endNode, vehicle, name, type);
                netAnal.setId(id);
                netAnal.setTravellTime(travelTime);
                netAnal.setEnergyConsumption(energyConsumption);
                netAnal.setAverageVelocity(averageVelocity);
                netAnal.setDistance(distance);
                netAnal.setTollCost(tollCost);
                netAnal.setLoad(load);
                netAnal.setFuelMass(fuelMass);
                netAnal.setFuelVolume(fuelVolume);
                netAnal.setAceleratingAcceleration(Double.parseDouble(aceleratingAcceleration));
                netAnal.setBrakingAcceleration(Double.parseDouble(brakingAcceleration));

                netList.add(netAnal);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAONetworkAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }

        }

    }
}
