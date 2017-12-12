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
    private String type;
    private int tollClass;
    private String motorization;
    private double mass;
    private double load;
    private double dragCoefficient;
    private double rollingResistanceCoefficient;
    private double wheelSize;
    private Object energyFunction;
    private Object brakingEnergyRegeneration;

    public Vehicle(String name, String type, int tollClass, String motorization, double mass, double load, double dragCoefficient, double rollingResistanceCoefficient, double wheelSize) {
        this.name = name;
        this.type = type;
        this.tollClass = tollClass;
        this.motorization = motorization;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.rollingResistanceCoefficient = rollingResistanceCoefficient;
        this.wheelSize = wheelSize;
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
     * @return the dragCoefficient
     */
    public double getDragCoefficient() {
        return dragCoefficient;
    }

    /**
     * @return the rollingResistanceCoefficient
     */
    public double getRollingResistanceCoefficient() {
        return rollingResistanceCoefficient;
    }

    /**
     * @return the wheelSize
     */
    public double getWheelSize() {
        return wheelSize;
    }

    /**
     * @return the energyFunction
     */
    public Object getEnergyFunction() { // NAO IMPLEMENTADA A FORMULA AINDA
        return energyFunction;
    }

    /**
     * @return brakingEnergyRegeneration
     */
    public Object getBrakingEnergyRegeneration() { // NAO IMPLEMENTADA A FORMULA AINDA
        if (getMotorization().equalsIgnoreCase("Combustion")) {
            return 0;
        }
        return brakingEnergyRegeneration;
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
