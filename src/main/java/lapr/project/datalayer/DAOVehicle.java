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
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleList;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class DAOVehicle extends DAOManager {

    /**
     * Name of the function in the database that adds vehicles
     */
    private static final String ADD_VEHICLES_PROCEDURE = "{call proc_addVehicle(?,?)}";

    /**
     * Name of the function in the database that gets vehicles
     */
    private static final String GET_VEHICLES_PROCEDURE = "{call proc_getVehicle(?,?)}";

    public DAOVehicle() throws SQLException {
        super(ADD_VEHICLES_PROCEDURE, GET_VEHICLES_PROCEDURE);
    }

    @Override
    protected void read(CallableStatement stmt,DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        VehicleList list = (VehicleList) placeToAdd;
        String projectName = (String) references[0];
        ResultSet rs = null;
        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, projectName);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);
            /*fix the names!!*/
            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                String type = rs.getString("TYPE");
                String motorization = rs.getString("MOTORIZATION");
                String mass = String.valueOf(rs.getDouble("MASS"));
                String load = String.valueOf(rs.getDouble("LOAD"));
                double drag = rs.getDouble("DRAG_COEFICIENT");
                double rrc = rs.getDouble("RRC");
                double wheelSize = rs.getDouble("WHEELSIZE");
                String fuel = rs.getString("FUEL");
                int tollClass = rs.getInt("TOLLCLASS");
                double frontalArea = rs.getDouble("FRONTALAREA");
                /* Create the rest 2 objects */
                Vehicle vehicle = new Vehicle(name, description, type, tollClass, motorization, fuel, mass, load, drag, frontalArea, rrc, wheelSize, null, null);
//                Energy energy = getEnergyByVehicle(vehicle);
//                vehicle.setEnergy(energy);
//                VelocityLimitList velocityLimitList = getVelocityLimits(vehicle);
//                vehicle.setVelocityLimitList(velocityLimitList);
                list.getVehicleList().add(vehicle);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    

        @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Vehicle vehicle = (Vehicle) data;

        cs.setString(1, vehicle.getName());
        cs.setString(2, vehicle.getDescription());
        cs.setString(3, vehicle.getType());
        cs.setString(4, vehicle.getMotorization());
        cs.setString(5, vehicle.getMass());
        cs.setString(6, vehicle.getLoad());
        cs.setDouble(7, vehicle.getDrag());
        cs.setDouble(8, vehicle.getRrc());
        cs.setDouble(9, vehicle.getWheelSize());
        cs.setString(10, vehicle.getFuel());
        cs.setDouble(11, vehicle.getTollClass());
        cs.setDouble(12, vehicle.getFrontalArea());
    }
}
