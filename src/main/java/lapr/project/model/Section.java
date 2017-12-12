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
    
    
    
    public Junction getBeginningJunction() {
        return beginningJunction;
    }

    public void setBeginningJunction(Junction beginningJunction) {
        this.beginningJunction = beginningJunction;
    }

    public Junction getEndingJunction() {
        return endingJunction;
    }

    public void setEndingJunction(Junction endingJunction) {
        this.endingJunction = endingJunction;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LinkedList<Segment> getsegments() {
        return segments;
    }

    public void setsegments(LinkedList<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.beginningJunction);
        hash = 83 * hash + Objects.hashCode(this.endingJunction);
        hash = 83 * hash + Objects.hashCode(this.typology);
        hash = 83 * hash + Objects.hashCode(this.direction);
        hash = 83 * hash + Objects.hashCode(this.segments);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Section other = (Section) obj;
        if (!Objects.equals(this.typology, other.typology)) {
            return false;
        }
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        if (!Objects.equals(this.beginningJunction, other.beginningJunction)) {
            return false;
        }
        if (!Objects.equals(this.endingJunction, other.endingJunction)) {
            return false;
        }
        if (!Objects.equals(this.segments, other.segments)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Section{" + "beginningJunction=" + beginningJunction + ", endingJunction=" + endingJunction + ", typology=" + typology + ", direction=" + direction + ", segments=" + segments + '}';
    }
    
}
