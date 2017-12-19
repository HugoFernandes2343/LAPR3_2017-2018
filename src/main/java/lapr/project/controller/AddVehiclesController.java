/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.Set;
import lapr.project.model.Project;
import lapr.project.model.ProjectList;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleList;
import lapr.project.utils.FileXML;

/**
 *
 * @author hugod
 */
public class AddVehiclesController {

    private TravelByPhysics base;
    private ProjectList projects;
    private Project project;

    /**
     * constructor of the controller
     *
     * @param base ,base of the program
     */
    public AddVehiclesController(TravelByPhysics base) {
        this.base = base;
    }

    /**
     * returns true if there is a active project to import returns false if
     * there isnt
     *
     * @return Array of data with the name in the first position and the
     * description in the second
     */
    public boolean getActiveProjectData() {
        this.projects = base.getProjectList();
        this.project = projects.getActualProject();
        return project != null;
    }

    /**
     * returns true if the vehicles are inserted or not
     *
     * @param filename name of the xml file to be read from
     * @return
     */
    public boolean addVehicles(String filename) {
        VehicleList v = FileXML.loadXmlVehicleList(filename);
        Set<Vehicle> newVehicles = v.getVehicleList();
        return this.project.getVehicleList().verifyAndAddVehicles(newVehicles) > 0;

    }

}
