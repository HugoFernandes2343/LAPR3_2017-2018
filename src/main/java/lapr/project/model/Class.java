/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;

public class Class implements Serializable{

    private static final long serialVersionUID = 501L;
    
    private String id;
    
    private Double price;
    
    /**
     * Empty constructor
     */
    public Class() {
    }
    
    /**
     * Complete constructor
     * @param id - id of the class
     * @param price - toll price
     */
    public Class(String id, Double price) {
        this.id = id;
        this.price = price;
    }
    
    /**
     * @return price
     */
    public Double getPrice() {
        return price;
    }

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
     * Setter for the class id
     * @param id - new id of the class
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Settercfor the price
     * @param price - toll price
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    
    /**
     * Hash code fo the object class
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
         Class other = (Class) obj;

        return this.id.equalsIgnoreCase(other.id);
    }

}

