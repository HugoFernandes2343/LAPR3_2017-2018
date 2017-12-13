/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hugod
 */
public class ProjectList {

    private Set<Project> project_list;
    private Project actualProject;

    public ProjectList() {
        this.project_list = new HashSet<>();
        this.actualProject = null;
    }

    /**
     * @return the project_list
     */
    public Set<Project> getProject_list() {
        return project_list;
    }

    /**
     * @return the actualProject
     */
    public Project getActualProject() {
        return actualProject;
    }

    /**
     * @param actualProject the actualProject to set
     */
    public void setActualProject(Project actualProject) {
        this.actualProject = actualProject;
    }

    /**
     * Method that adds a project to the list
     *
     * @param p - project to be added
     * @return true if the project is added, false otherwise.
     */
    public boolean addProject(Project p) {
        if (p == null) {
            return false;
        }

        if (verifyProject(p)) {
            this.project_list.add(p);
            this.actualProject = p;
            return true;
        }

        return false;
    }

    /**
     * Method that verifies if a project already exists.
     *
     * @param p - project to be added
     * @return true if the project doesn't exists, false otherwise
     */
    private boolean verifyProject(Project p) {
        for (Project p1 : this.project_list) {
            if (p1.equals(p)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that returns all names of the project_list
     *
     * @return List of names
     */
    public ArrayList<String> getAllNames() {
        ArrayList<String> allNames = new ArrayList<>();
        for (Project p : project_list) {
            allNames.add(p.getName());
        }
        return allNames;
    }

    /**
     *
     * @param name , name of the project to look for
     * @return project with said name
     */
    public Project getProject(String name) {
        Project ret = new Project();
        for (Project p : project_list) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
}
