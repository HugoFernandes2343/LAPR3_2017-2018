/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hugod
 */
public class VehicleList implements Serializable{

    private static final long serialVersionUID = 205L;
    
    private Set<Vehicle> vehicle_list;

    public VehicleList() {
        this.vehicle_list = new HashSet<>();
    }

    /**
     * @return the vehicle_list
     */
    public Set<Vehicle> getVehicleList() {
        return vehicle_list;
    }

    /**
     * @param vehicle_list the vehicle_list to set
     */
    public void setVehicleList(Set<Vehicle> vehicle_list) {
        this.vehicle_list = vehicle_list;
    }

}
