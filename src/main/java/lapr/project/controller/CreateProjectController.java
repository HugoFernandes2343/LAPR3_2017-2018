/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.IOException;
import lapr.project.model.Project;
import lapr.project.model.Network;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.VehicleList;
import lapr.project.utils.FileXML;

/**
 *
 * @author filip
 */
public class CreateProjectController {

    /**
     * Attribute that keeps the base to add the new project
     */
    private final TravelByPhysics base;

    /**
     * Atribute that keeps the project to add.
     */
    private Project newP;

    /**
     * Constructor of this controller
     * @param base - the base of the system.
     */
    public CreateProjectController(TravelByPhysics base) {
        this.base = base;
    }

    /**
     * Method that creates a new project with the information inserted by the
     * user.
     *
     * @param name - name introduced by the user.
     * @param description - description introduced by the user.
     * @return true if the new project is created, false otherwise.
     */
    public boolean createProject(String name, String description) {
        if (name.isEmpty() || description.isEmpty()) {
            return false;
        }

        if (this.base.getProjectList().getProject(name) != null) {
            return false;
        }

        Project p = new Project();
        p.setName(name);
        p.setDescription(description);
        this.newP = p;
        return true;
    }

    /**
     * Method that reads all the information needed for the new project.
     *
     * @param fileNetwork - path to the file with the road network.
     * @param fileVehicleList - path to the file with the vehicle list.
     * @return
     */
    public boolean readInfo(String fileNetwork, String fileVehicleList) {

        try {
            Network r = FileXML.loadXmlNetwork(fileNetwork);
            VehicleList v = FileXML.loadXmlVehicleList(fileVehicleList);

            if (v == null || r == null) {
                return false;
            }

            this.newP.setNetwork(r);
            this.newP.setVehicleList(v);
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    /**
     * Method that adds the new project to the system.
     *
     * @return true if the new project is addes, false otherwise.
     */
    public boolean addProject() {
        return this.base.getProjectList().addProject(this.newP);
    }
}
