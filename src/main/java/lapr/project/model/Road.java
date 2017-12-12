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
 * @author hugod
 */
public class Road {

    private String name;
    private String description;
    private LinkedList<Section> sections;

    public Road(String name, String description, LinkedList<Section> sections) {
        this.name = name;
        this.description = description;
        this.sections = sections;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the sections
     */
    public LinkedList<Section> getSections() {
        return sections;
    }

    /**
     *
     * @return the integer representation of the object Junction
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the Road
     * @return the result of the comparisons made. 
     * True if the objects are the same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Road other = (Road) obj;
        return this.name.equals(other.name);
    }

    /**
     *
     * @return the String representation of the Road
     */
    @Override
    public String toString() {
        return String.format("Road: %s", name);
    }

}
