/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 
 */
public class VelocityLimit implements Serializable{

    private static final long serialVersionUID = 204L;
    
    private String segment_type;
    private int limit;

    /**
     *
     * @param segment_type
     * @param limit
     */
    public VelocityLimit(String segment_type, int limit) {
        this.segment_type = segment_type;
        this.limit = limit;
    }

    public String getSegment_type() {
        return segment_type;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.segment_type);
        hash = 83 * hash + this.limit;
        return hash;
    }

    /**
     * @param obj the object to compare to the velocityLimit
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VelocityLimit other = (VelocityLimit) obj;
        if (this.limit != other.limit) {
            return false;
        }
        return this.segment_type.equalsIgnoreCase(other.segment_type);
            
        
    }

}
