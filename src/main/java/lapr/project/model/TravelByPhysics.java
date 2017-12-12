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
    
    private ProjectList ProjectList;
    private UserList UserList;
    
    public TravelByPhysics(){
    this.ProjectList = new ProjectList();
    this.UserList = new UserList();
    }

    /**
     * @return the ProjectList
     */
    public ProjectList getProjectList() {
        return ProjectList;
    }
}
