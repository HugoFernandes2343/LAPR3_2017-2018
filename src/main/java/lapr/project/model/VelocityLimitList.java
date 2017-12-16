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
    
    private Set<VelocityLimit> velocity_limit_list;

    /**
     *
     */
    public VelocityLimitList() {
        this.velocity_limit_list = new HashSet<>();
    }

    /**
     * @return the velocity_limit_list
     */
    public Set<VelocityLimit> getVelocity_limit_list() {
        return velocity_limit_list;
    }
    
    /**
     * Adds a VelocityLimit to hte list
     * @param limit - VelocityLimit
     */
    public void addVelocityLimit(VelocityLimit limit){
        if(!this.velocity_limit_list.contains(limit)){
            this.velocity_limit_list.add(limit);
        }
    }

}
