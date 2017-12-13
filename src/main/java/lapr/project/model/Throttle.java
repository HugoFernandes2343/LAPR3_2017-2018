/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Hugo
 */
public class Throttle {

    private String id;
    private LinkedList<Regime> regime_list;

    public Throttle(String id, LinkedList<Regime> regime_list) {
        this.id = id;
        this.regime_list = regime_list;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the regime_list
     */
    public LinkedList<Regime> getRegime_list() {
        return regime_list;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     * @param obj the object to compare to the throttle
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Throttle other = (Throttle) obj;
        return this.id.equalsIgnoreCase(other.id);

    }

}