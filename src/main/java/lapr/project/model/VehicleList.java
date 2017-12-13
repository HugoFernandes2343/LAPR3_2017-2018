/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hugod
 */
public class VehicleList {

    private Set<Vehicle> vehicle_list;

    public VehicleList() {
        this.vehicle_list = new HashSet<>();
    }

    /**
     * @return the vehicle_list
     */
    public Set<Vehicle> getVehicle_list() {
        return vehicle_list;
    }
}
