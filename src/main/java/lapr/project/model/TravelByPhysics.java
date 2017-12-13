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
public class TravelByPhysics {

    /**
     * Attribute that keeps the project list of the system.
     */
    private ProjectList project_list;

    /**
     * Attribute that keeps the users list of the system.
     */
    private UserList user_list;

    /**
     * Constructor of this class
     */
    public TravelByPhysics() {
        this.project_list = new ProjectList();
        this.user_list = new UserList();
    }

    /**
     * @return the project_list
     */
    public ProjectList getProject_list() {
        return project_list;
    }
}
