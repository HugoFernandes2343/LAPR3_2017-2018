/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author hugod
 */
public class Project {
    
    private VehicleList vehicleList;
    private RoadNetwork roadNetwork;
    
    public Project(){
    this.roadNetwork = new RoadNetwork();
    this.vehicleList = new VehicleList();
    }
}
