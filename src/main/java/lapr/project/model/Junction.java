/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author hugod
 */
public class Junction {

    private Object toll;

    public Junction() {
    }

    /**
     * @return the toll
     */
    public Object getToll() {
        return toll;
    }

    /**
     * @param toll the toll to set
     */
    public void setToll(Object toll) {
        this.toll = toll;
    }

    /**
     *
     * @param obj the object to compare to the Junction
     * @return the result of the comparisons made. 
     * True if the objects are the same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Junction)) {
            return false;
        }
        Junction other = (Junction) obj;
        return this.getToll().equals(other.getToll());
    }

    /**
     *
     * @return the integer representation of the object Junction
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.getToll());
        return hash;
    }

}
