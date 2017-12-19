/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class TravelByPhysics implements Serializable{
    private static final long serialVersionUID = 103L;
    
    /**
     * Attribute that keeps the project list of the system.
     */
    private ProjectList projectList;

    /**
     * Attribute that keeps the users list of the system.
     */
    private UserList userList;
    
    /**
     * Attribute that keeps the algorithms list
     */
    private List<Algorithm> AlgorithmsList;

    /**
     * Constructor of this class
     */
    public TravelByPhysics() {
        this.projectList = new ProjectList();
        this.userList = new UserList();
        this.AlgorithmsList = new ArrayList<>();
    }

    /**
     * @return the project_list
     */
    public ProjectList getProjectList() {
        return projectList;
    }
    
    /**
     * @return the algorithmsList
     */
    public List<Algorithm> getAlgorithmsList() {
        return AlgorithmsList;
    }
    
    /**
     * @return a List of all algorithms names
     */
    public List<String> getAlgorithmsByName(){
        List<String> names = new ArrayList<>();
        for(Algorithm a : this.AlgorithmsList){
            names.add(a.getName());
        }
        return names;
    }
    
    
}
