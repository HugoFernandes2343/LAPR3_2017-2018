/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 
 */
public class Project implements Serializable{

    private static final long serialVersionUID = 601L;
    
    private String name;
    private String description;
    private VehicleList vehicle_list;
    private Network network;
    private NetworkAnalysis netAnal;

    public Project() {
        this.name = "n/a";
        this.description = "n/a";
        this.network = new Network();
        this.vehicle_list = new VehicleList();
    }

    public Project(Project other) {
       this.description=other.description;
       this.name=other.name;
       this.network=other.network;
       this.vehicle_list=other.vehicle_list;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the VehicleList
     */
    public VehicleList getVehicleList() {
        return vehicle_list;
    }

    /**
     * @param vehicle_list the VehicleList to set
     */
    public void setVehicleList(VehicleList vehicle_list) {
        this.vehicle_list = vehicle_list;
    }

    /**
     * @return the network
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(Network network) {
        this.network = network;
    }

    /**
     * Method to start the analysis on the project. Analyses the project following several criteria
     */
    public void doAnal(){
        netAnal = new NetworkAnalysis();
    }
    
    /**
     *
     * @return the integer representation of the object Project
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(name);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the Project
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        return this.name.equals(other.name);
    }

    /**
     *
     * @return the String representation of the Project
     */
    @Override
    public String toString() {
        return String.format("Project: %s", name);
    }

}
