/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Network;
import lapr.project.model.Project;
import lapr.project.model.TravelByPhysics;
import lapr.project.utils.FileXML;

/**
 *
 * @author filip
 */
public class AddRoadsController {
    
    /**
     * The project to which the new roads will be added
     */
    private final Project projectToAdd;
    
    /**
     * Constructor
     * @param tb - Object with the necessary data to the UC
     */
    public AddRoadsController(TravelByPhysics tb){
        this.projectToAdd = tb.getProjectList().getActualProject();
    }
    
    /**
     * 
     * @param fileRoadNoetwork - path to the file used to import all the new roads.
     * @return true if the new roads are added, false otherwise.
     */
    public boolean addNewRoads(String fileRoadNoetwork){
        Network newR = FileXML.loadXmlNetwork(fileRoadNoetwork);
        newR.loadMap();
        
        if(this.projectToAdd == null){
            return false;
        }
        
        return this.projectToAdd.getNetwork().addNewRoadsFromNetwork(newR);
    }
    
}
