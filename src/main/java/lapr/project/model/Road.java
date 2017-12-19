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
public class Road implements Serializable {

    private static final long serialVersionUID = 604L;

    private String name;
    private String typology;
    private String id;
    private TollFare toll_fare;

    /**
     * Empty Constructor
     */
    public Road() {
    }

    /**
     * Constructor of the Road Object
     *
     * @param name - name of the road
     * @param description - tipology of the road
     * @param id - id of the road
     */
    public Road(String name, String description, String id) {
        this.name = name;
        this.typology = description;
        this.id = id;
    }

    /**
     * Complete constructor
     *
     * @param name - name of the road
     * @param typology - tipology of the road
     * @param id - id of the road
     * @param tollFare
     */
    public Road(String name, String typology, String id, TollFare tollFare) {
        this.name = name;
        this.typology = typology;
        this.id = id;
        this.toll_fare = tollFare;
    }

    /**
     * @return the name name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * Set method of the name variable
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the typology typology of the road
     */
    public String getTypology() {
        return typology;
    }

    /**
     * Set method of the typology variable
     *
     * @param typology the typology to set
     */
    public void setTypology(String typology) {
        this.typology = typology;
    }

    /**
     * @return the id id of the road
     */
    public String getId() {
        return id;
    }

    /**
     * Set method of the id variable
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the toll_fare toll fare object of the road
     */
    public TollFare getTollFare() {
        return toll_fare;
    }

    /**
     * @param tollFare the toll_fare to set
     */
    public void setTollFare(TollFare tollFare) {
        this.toll_fare = tollFare;
    }

    /**
     * Equals method of the road type objects
     *
     * @param obj the object to compare to the Road
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Road other = (Road) obj;
        return this.id.equals(other.id);
    }

    /**
     * HashCode method of the road type objects
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
     * To string method of the road type objects
     *
     * @return the String representation of the Road
     */
    @Override
    public String toString() {
        return String.format("Road name: %s, id: %s", name, id);
    }
}
