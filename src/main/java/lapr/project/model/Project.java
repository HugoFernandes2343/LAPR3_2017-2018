/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author hugod
 */
public class Project {

    private String name;
    private String description;
    private VehicleList vehicleList;
    private RoadNetwork roadNetwork;

    public Project() {
        this.name = "n/a";
        this.description = "n/a";
        this.roadNetwork = new RoadNetwork();
        this.vehicleList = new VehicleList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleList getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(VehicleList vehicleList) {
        this.vehicleList = vehicleList;
    }

    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    public void setRoadNetwork(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.vehicleList);
        hash = 47 * hash + Objects.hashCode(this.roadNetwork);
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
        final Project other = (Project) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.vehicleList, other.vehicleList)) {
            return false;
        }
        if (!Objects.equals(this.roadNetwork, other.roadNetwork)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project{" + "name=" + name + ", description=" + description + ", vehicleList=" + vehicleList + ", roadNetwork=" + roadNetwork + '}';
    }

    

}
