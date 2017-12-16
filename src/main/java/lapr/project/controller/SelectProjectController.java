package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.model.Project;
import lapr.project.model.ProjectList;
import lapr.project.model.TravelByPhysics;

public class SelectProjectController {
    
    private TravelByPhysics base;
    private ProjectList projects;
    private Project project;
    
    /**
     * constructor of the controller
     * @param base ,base of the program
     */
    public SelectProjectController(TravelByPhysics base){
        this.base = base;
    }
    
    /**
     * Method that returns a list of names to show to the user
     * @return Returns all the names of all the projects in the list
     */
    public ArrayList<String> getProjects(){
        ArrayList<String> allNames = new ArrayList<>();
        this.projects =  base.getProjectList();
        allNames = projects.getAllNames();
        return allNames;
    }
    
    /**
     * method that sets the chosen project as active project
     * @param name , name of the project that will be set as active project
     */
    public void loadActiveProject(String name){
     this.project = projects.getProject(name);
     projects.setActualProject(project);
    }
    
    /**
     *  returns the data for the user confirmation
     * @return Array of data with the name in the first position and the description in the second
     */
    public String[]  getData(){
        String[] data = new String[2];
        data[0] = project.getName();
        data[1] = project.getDescription();
        return data;
    }
}

