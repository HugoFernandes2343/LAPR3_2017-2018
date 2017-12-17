/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.ProjectList;
import lapr.project.model.TravelByPhysics;

/**
 *
 * @author hugod
 */
public class EditProjectController {

    /**
     * Attribute that keeps the base
     */
    private TravelByPhysics base;

    /**
     * Attribute that keeps the project's list
     */
    private ProjectList projects;

    /**
     * Attribute that keeps the active project
     */
    private Project project;

    /**
     * constructor of the controller
     * @param base base of the project
     */
    public EditProjectController(TravelByPhysics base){
        this.base = base;
    }
   
    /**
     * void method that gathers needed attributes of the controller
     * 
     */
    public void readyEdit(){
        projects = base.getProjectList();
        project = new Project(projects.getActualProject());
    }
    
    /**
     *  method that defines the name and description of the project object present in the controller
     * @param name , name inserted by the user to substitute in the project object
     * @param description , description inserted by the user to substitute in the project object
     */
    public boolean alterProject(String name,String description){
        if(name.equalsIgnoreCase("")||description.equalsIgnoreCase("")){
            return false;
        }
        project.setName(name);
        project.setDescription(description);
        return true;
    }
    
    /**
     * method that takes the name and description of the temporary project and changes the actual projects description and name
     * @return the return of this method is the return of the method it invokes in the projectList class
     */
    public boolean saveChanges(){
        return projects.updateActualProject(project);
      
    }
}
