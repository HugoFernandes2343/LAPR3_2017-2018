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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedList<Section> getsections() {
        return sections;
    }

    public void setsections(LinkedList<Section> sections) {
        this.sections = sections;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.sections);
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
        final Road other = (Road) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.sections, other.sections)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Road{" + "name=" + name + ", description=" + description + ", sections=" + sections + '}';
    }
    
    
}
