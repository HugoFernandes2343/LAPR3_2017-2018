/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author Hugo
 */
public class Gear {

    private String id;
    private double ratio;

    /**
     *
     * @param id
     * @param ratio
     */
    public Gear(String id, double ratio) {
        this.id = id;
        this.ratio = ratio;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the ratio
     */
    public double getRatio() {
        return ratio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     *
     ** @param obj the object to compare to the gear
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {

        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gear other = (Gear) obj;
        return this.id.equalsIgnoreCase(other.id);

    }

}