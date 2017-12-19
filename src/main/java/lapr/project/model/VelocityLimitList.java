/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 
 */
public class VelocityLimitList implements Serializable{

    private static final long serialVersionUID = 203L;
    
    private Set<VelocityLimit> velocityLimitList;

    /**
     * Empty constructor
     */
    public VelocityLimitList() {
        this.velocityLimitList = new HashSet<>();
    }

    /**
     * @return the velocity_limit_list 
     */
    public Set<VelocityLimit> getVelocityLimitList() {
        return velocityLimitList;
    }
    
    /**
     * Adds a VelocityLimit to the list
     * @param limit - VelocityLimit
     */
    public void addVelocityLimit(VelocityLimit limit){
        if(!this.velocityLimitList.contains(limit)){
            this.velocityLimitList.add(limit);
        }
    }

}
