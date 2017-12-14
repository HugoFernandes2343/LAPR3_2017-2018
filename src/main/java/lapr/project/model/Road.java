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
 * @author hugod
 */
public class Road implements Serializable{

    private static final long serialVersionUID = 604L;
    
    private String name;
    private String typology;
    private String id;
    private TollFare toll_fare;

    public Road(String name, String description,String id) {
        this.name = name;
        this.typology = description;
        this.id = id;
    }

    public Road(String name, String typology, String id, TollFare toll_fare) {
        this.name = name;
        this.typology = typology;
        this.id = id;
        this.toll_fare = toll_fare;
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
     * @return the typology
     */
    public String getTypology() {
        return typology;
    }

    /**
     * @param typology the typology to set
     */
    public void setTypology(String typology) {
        this.typology = typology;
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
        return this.id.equals(other.id);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @return the String representation of the Road
     */
    @Override
    public String toString() {
        return String.format("Road name: %s, id: %s", name,id);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the toll_fare
     */
    public TollFare getToll_fare() {
        return toll_fare;
    }

    /**
     * @param toll_fare the toll_fare to set
     */
    public void setToll_fare(TollFare toll_fare) {
        this.toll_fare = toll_fare;
    }

}
