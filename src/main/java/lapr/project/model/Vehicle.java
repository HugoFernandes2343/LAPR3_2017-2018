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
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 201L;

    private String name;
    private String description;
    private String type;
    private int tollClass;
    private String motorization;
    private String fuel;
    private String mass;
    private String load;
    private double drag;
    private double frontal_area;
    private double rrc;
    private double wheelSize;
    private Energy energy;
    private VelocityLimitList velocity_limit_list;

    public Vehicle() {
    }

    public Vehicle(String name, String description, String type, int tollClass, String motorization, String fuel, String mass, String load, double drag, double frontal_area, double rrc, double wheelSize, Energy energy, VelocityLimitList velocity_limit_list) {
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

    public Vehicle(String name, String description, String type, int tollClass, String motorization, String fuel, String mass, String load, double drag, double frontal_area, double rrc, double wheelSize) {
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
    }

    public Vehicle(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTollClass(int tollClass) {
        this.tollClass = tollClass;
    }

    public void setMotorization(String motorization) {
        this.motorization = motorization;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public void setDrag(double drag) {
        this.drag = drag;
    }

    public void setFrontal_area(double frontal_area) {
        this.frontal_area = frontal_area;
    }

    public void setRrc(double rrc) {
        this.rrc = rrc;
    }

    public void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public void setVelocity_limit_list(VelocityLimitList velocity_limit_list) {
        this.velocity_limit_list = velocity_limit_list;
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
    public String getMass() {
        return mass;
    }

    /**
     * @return the load
     */
    public String getLoad() {
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
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + this.tollClass;
        hash = 97 * hash + Objects.hashCode(this.motorization);
        hash = 97 * hash + Objects.hashCode(this.fuel);
        hash = 97 * hash + Objects.hashCode(this.mass);
        hash = 97 * hash + Objects.hashCode(this.load);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.drag) ^ (Double.doubleToLongBits(this.drag) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.frontal_area) ^ (Double.doubleToLongBits(this.frontal_area) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.rrc) ^ (Double.doubleToLongBits(this.rrc) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.wheelSize) ^ (Double.doubleToLongBits(this.wheelSize) >>> 32));
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the Vehicle
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {

        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (this.tollClass != other.tollClass) {
            return false;
        }
        if (Double.doubleToLongBits(this.drag) != Double.doubleToLongBits(other.drag)) {
            return false;
        }
        if (Double.doubleToLongBits(this.frontal_area) != Double.doubleToLongBits(other.frontal_area)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rrc) != Double.doubleToLongBits(other.rrc)) {
            return false;
        }
        if (Double.doubleToLongBits(this.wheelSize) != Double.doubleToLongBits(other.wheelSize)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.motorization, other.motorization)) {
            return false;
        }
        if (!Objects.equals(this.fuel, other.fuel)) {
            return false;
        }
        if (!Objects.equals(this.mass, other.mass)) {
            return false;
        }
        if (!Objects.equals(this.load, other.load)) {
            return false;
        }
        return true;
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
