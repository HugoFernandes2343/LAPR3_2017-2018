/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author hugod
 */
public class Vehicle {

    private String name;
    private String description;
    private String type;
    private int tollClass;
    private String motorization;
    private String fuel;
    private double mass;
    private double load;
    private double drag;
    private double frontal_area;
    private double rrc;
    private double wheelSize;
    private Energy energy;
    private VelocityLimitList velocity_limit_list;

    public Vehicle(String name,String description, String type, int tollClass, String motorization,String fuel, double mass, double load, double drag,double frontal_area, double rrc , double wheelSize,Energy energy,VelocityLimitList velocity_limit_list) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.tollClass = tollClass;
        this.motorization = motorization;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.drag = drag;
        this.frontal_area = frontal_area;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.energy = energy;
        this.velocity_limit_list = velocity_limit_list;
    }

    public Vehicle(String name){
    this.name = name;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the tollClass
     */
    public int getTollClass() {
        return tollClass;
    }

    /**
     * @return the motorization
     */
    public String getMotorization() {
        return motorization;
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @return the load
     */
    public double getLoad() {
        return load;
    }

    /**
     * @return the drag
     */
    public double getDrag() {
        return drag;
    }

    /**
     * @return the rrc
     */
    public double getRrc() {
        return rrc;
    }

    /**
     * @return the wheelSize
     */
    public double getWheelSize() {
        return wheelSize;
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
     * @return the fuel
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * @param fuel the fuel to set
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * @return the frontal_area
     */
    public double getFrontal_area() {
        return frontal_area;
    }

    /**
     * @return the energy
     */
    public Energy getEnergy() {
        return energy;
    }

    /**
     * @return the velocity_limit_list
     */
    public VelocityLimitList getVelocity_limit_list() {
        return velocity_limit_list;
    }

    /**
     *
     * @return the integer representation of the object Vehicle
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the Vehicle
     * @return the result of the comparisons made. 
     * True if the objects are the same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return this.name.equals(other.name);
    }

    /**
     *
     * @return the String representation of the Vehicle
     */
    @Override
    public String toString() {
        return String.format("Vehicle: %s", name);
    }
}
