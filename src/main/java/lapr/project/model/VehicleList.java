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
import java.util.Set;

/**
 *
 * @author
 */
public class VehicleList implements Serializable {

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

    public void addVehicle(Vehicle car) {
        if (!this.vehicle_list.contains(car)) {
            this.vehicle_list.add(car);
        }
    }

    public ArrayList<String> getAllVehicleNames() {
        Iterator<Vehicle> itr = vehicle_list.iterator();
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
     */
    public void verifyAndAddVehicles(Set<Vehicle> newVehicles) {
        for (Vehicle vIn : newVehicles) {
            if (!vehicle_list.contains(vIn)) {
                int i = incrementName(vIn.getName(), 0, getAllVehicleNames());
                if (i > 0) {
                    vIn.setName(vIn.getName() + i);
                }
                addVehicle(vIn);
            }
        }
    }

    private int incrementName(String name, int cont, ArrayList<String> names) {
        System.out.println(cont);
        System.out.println(name);
        if (names.contains(name)) {
            cont = cont + 1;
            name = name + cont;
            return incrementName(name, cont, names);
        }
        return cont;
    }
}
