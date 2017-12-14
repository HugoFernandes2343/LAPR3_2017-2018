/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Hugo
 */
public class VelocityLimitList {

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.velocity_limit_list);
        return hash;
    }


}
