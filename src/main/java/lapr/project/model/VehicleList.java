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

    public void VerifyAndAddVehicles(Set<Vehicle> newVehicles) {

        Iterator<Vehicle> itr2 = newVehicles.iterator();
       
            while (itr2.hasNext()) {
                Vehicle vIn = itr2.next();
                if (!vehicle_list.contains(vIn)) {
                    int i = incrementName(vIn, vIn.getName(), 0);
                    vIn.setName(vIn.getName() + i);
                    addVehicle(vIn);
                }
            }
        

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

    private int incrementName(Vehicle v, String name, int i) {
        ArrayList<String> names = getAllVehicleNames();
        Vehicle temp = v;

        if (names.contains(v.getName())) {
            i++;
            v.setName(name + i);

            i = incrementName(temp, name, i);
        }
        return i;
    }

    

}
