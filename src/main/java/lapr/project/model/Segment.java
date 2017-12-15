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
public class Segment implements Serializable{
    private static final long serialVersionUID = 506L;
    
    private String id;
    private double init_height;
    private double final_height;
    private String length;
    private double wind_direction;
    private String wind_speed;
    private String max_velocity;
    private String min_velocity;

    public Segment(String segmentIndex, double initialHeight, double slope, String length, double windDirection, String wind_speed, String max_velocity, String min_velocity) {
        this.id = segmentIndex;
        this.init_height = initialHeight;
        this.final_height = slope;
        this.length = length;
        this.wind_direction = windDirection;
        this.wind_speed = wind_speed;
        this.max_velocity = max_velocity;
        this.min_velocity = min_velocity;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the init_height
     */
    public double getInit_height() {
        return init_height;
    }

    /**
     * @return the final_height
     */
    public double getFinal_height() {
        return final_height;
    }

    /**
     * @return the length
     */
    public String getLength() {
        return length;
    }

    /**
     * @return the wind_direction
     */
    public double getWind_direction() {
        return wind_direction;
    }

    /**
     * @return the wind_speed
     */
    public String getWind_speed() {
        return wind_speed;
    }

    /**
     * @return the maximumVelocity
     */
    public String getMax_velocity() {
        return max_velocity;
    }

    /**
     * @return the minimumVelocity
     */
    public String getMin_velocity() {
        return min_velocity;
    }

    /**
     *
     * @param obj the object to compare to the Segment
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Segment other = (Segment) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @return the String representation of the Segment
     */
    @Override
    public String toString() {
        return String.format("Segment id: %s", this.id);
    }

}
