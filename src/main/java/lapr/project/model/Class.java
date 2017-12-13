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
public class Class {

    private String id;

    /**
     *
     * @param id id given to the class
     */
    public Class(String id) {
        this.id = id;
    }

    /**
     *
     * @return id of the class
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * @param obj the object to compare to the class
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {

        if (getClass() != obj.getClass()) {
            return false;
        }
        final Class other = (Class) obj;

        return this.id.equalsIgnoreCase(other.id);
    }

}
