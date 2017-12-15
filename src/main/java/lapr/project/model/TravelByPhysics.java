/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class TravelByPhysics implements Serializable{
    private static final long serialVersionUID = 103L;
    
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
    public ProjectList getProjectList() {
        return project_list;
    }
}
