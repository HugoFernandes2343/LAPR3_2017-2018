/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hugod
 */
public class ProjectList {

    private Set<Project> projects;
    private Project actualProject;

    public ProjectList() {
        this.projects = new HashSet<>();
        this.actualProject = null;
    }

    /**
     * @return the projects
     */
    public Set<Project> getProjects() {
        return projects;
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
     * @param p - project to be added
     * @return true if the project is added, false otherwise.
     */
    public boolean addProject(Project p) {
        if (verifieProject(p)) {
            this.projects.add(p);
            this.actualProject = p;
            return true;
        }
        return false;
    }

    /**
     * Method that verifies if a project already exists.
     * @param p - project to be added
     * @return true if the project doesn't exists, false otherwise
     */
    private boolean verifieProject(Project p) {
        for (Project p1 : this.projects) {
            if (p1.equals(p)) {
                return false;
            }
        }
        return true;
    }
}
