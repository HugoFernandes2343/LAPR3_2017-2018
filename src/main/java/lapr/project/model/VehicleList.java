/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author hugod
 */
public class VehicleList {

    private Set<Vehicle> vehicleList;

    public VehicleList() {
        this.vehicleList = new HashSet();
    }

    public Set<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(Set<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.vehicleList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleList other = (VehicleList) obj;
        if (this.vehicleList.size() == other.vehicleList.size()) {
            return false;
        }
        Iterator itr = this.vehicleList.iterator();
        Iterator itr2 = other.vehicleList.iterator();
        while (itr.hasNext()) {
            if (!itr.next().equals(itr2.next())) {
                return false;
            }
        }
        return true;
    }

    
}
