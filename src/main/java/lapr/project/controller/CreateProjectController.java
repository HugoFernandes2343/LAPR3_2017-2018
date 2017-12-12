/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.TravelByPhysics;

/**
 *
 * @author filip
 */
public class CreateProjectController {

    /**
     * Attribute that keeps the base to add the new project
     */
    private TravelByPhysics base;
    
    /**
     * Atribute that keeps the project to add.
     */
    private Project newP;

    /**
     * Constructor of this controller
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
     */
    public void createProject(String name, String description) {
        Project p = new Project();
        p.setName(name);
        p.setDescription(description);
        this.newP = p;
    }

    /**
     * Method that reads all the information needed for the new project.
     */
    public void readInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method that adds the new project to the system.
     * @return true if the new project is addes, false otherwise.
     */
    public boolean addProject() {
      return this.base.getProjectList().addProject(this.newP);
    }
}
