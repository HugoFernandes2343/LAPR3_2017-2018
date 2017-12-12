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
public class Section {

    private Junction beginningJunction;
    private Junction endingJunction;
    private String typology;
    private String direction;
    private LinkedList<Segment> segments;

    public Section(Junction beginningJunction, Junction endingJunction, String typology, String direction, LinkedList<Segment> segments) {
        this.beginningJunction = beginningJunction;
        this.endingJunction = endingJunction;
        this.typology = typology;
        this.direction = direction;
        this.segments = segments;
    }

    /**
     * @return the beginningJunction
     */
    public Junction getBeginningJunction() {
        return beginningJunction;
    }

    /**
     * @return the endingJunction
     */
    public Junction getEndingJunction() {
        return endingJunction;
    }

    /**
     * @return the typology
     */
    public String getTypology() {
        return typology;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @return the segments
     */
    public LinkedList<Segment> getSegments() {
        return segments;
    }

    /**
     *
     * @return the integer representation of the object Section
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.beginningJunction);
        hash = 83 * hash + Objects.hashCode(this.endingJunction);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the Section
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Section other = (Section) obj;
        if (!this.beginningJunction.equals(other.beginningJunction)) {
            return false;
        }
        return this.endingJunction.equals(other.endingJunction);
    }
}
