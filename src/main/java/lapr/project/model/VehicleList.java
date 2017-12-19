/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author
 */
public class VehicleList implements Serializable {

    private static final long serialVersionUID = 205L;

    private Set<Vehicle> vehicleList;

    public VehicleList() {
        this.vehicleList = new HashSet<>();
    }

    /**
     * @return the vehicle_list
     */
    public Set<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * @param vehicleList the vehicle_list to set
     */
    public void setVehicleList(Set<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    /**
     * Method that adds a vehicle to the list
     * 
     * @param car
     */
    public void addVehicle(Vehicle car) {
        if (!this.vehicleList.contains(car)) {
            this.vehicleList.add(car);
        }
    }

    /**
     *  Method that returns a list with all the vehicle names present in the list
     * 
     * @return List of strings with the names of the vehicles
     */
    public List<String> getAllVehicleNames() {
        Iterator<Vehicle> itr = vehicleList.iterator();
        ArrayList<String> names = new ArrayList<>();
        while (itr.hasNext()) {
            names.add(itr.next().getName());
        }
        return names;
    }

    /**
     * Verifies witch vehicles from the list given as parameter can be added to
     * the VehicleList
     *
     * @param newVehicles the list of chicles to be added
     * @return 
     */
    public int verifyAndAddVehicles(Set<Vehicle> newVehicles) {
        int cont = 0;
        for (Vehicle vIn : newVehicles) {
            if (!vehicleList.contains(vIn)) {
                int i = incrementName(vIn.getName(), 0, getAllVehicleNames());
                if (i > 0) {
                    vIn.setName(vIn.getName() + i);
                }
                addVehicle(vIn);
                cont++;
            }
        }
        return cont;
    }

    private int incrementName(String name, int cont, List<String> names) {
        if (names.contains(name)) {
            cont = cont + 1;
            name = name + cont;
            return incrementName(name, cont, names);
        }
        return cont;
    }
}
