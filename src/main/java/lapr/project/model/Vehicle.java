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

    public Vehicle(String name,String type, int tollClass, String motorization, double mass, double load, double dragCoefficient, double rollingResistanceCoefficient, double wheelSize) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTollClass() {
        return tollClass;
    }

    public void setTollClass(int tollClass) {
        this.tollClass = tollClass;
    }

    public String getMotorization() {
        return motorization;
    }

    public void setMotorization(String motorization) {
        this.motorization = motorization;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public double getRollingResistanceCoefficient() {
        return rollingResistanceCoefficient;
    }

    public void setRollingResistanceCoefficient(double rollingResistanceCoefficient) {
        this.rollingResistanceCoefficient = rollingResistanceCoefficient;
    }

    public double getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    public Object getEnergyFunction() { // NAO IMPLEMENTADA A FORMULA AINDA
        return energyFunction;
    }

    
    public Object getBrakingEnergyRegeneration() { // NAO IMPLEMENTADA A FORMULA AINDA
        if(motorization.equalsIgnoreCase("Combustion")){
            return 0;
        }
        return brakingEnergyRegeneration;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + this.tollClass;
        hash = 89 * hash + Objects.hashCode(this.motorization);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.mass) ^ (Double.doubleToLongBits(this.mass) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.load) ^ (Double.doubleToLongBits(this.load) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.dragCoefficient) ^ (Double.doubleToLongBits(this.dragCoefficient) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.rollingResistanceCoefficient) ^ (Double.doubleToLongBits(this.rollingResistanceCoefficient) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.wheelSize) ^ (Double.doubleToLongBits(this.wheelSize) >>> 32));
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
        final Vehicle other = (Vehicle) obj;
        if (this.tollClass != other.tollClass) {
            return false;
        }
        if (Double.doubleToLongBits(this.mass) != Double.doubleToLongBits(other.mass)) {
            return false;
        }
        if (Double.doubleToLongBits(this.load) != Double.doubleToLongBits(other.load)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dragCoefficient) != Double.doubleToLongBits(other.dragCoefficient)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rollingResistanceCoefficient) != Double.doubleToLongBits(other.rollingResistanceCoefficient)) {
            return false;
        }
        if (Double.doubleToLongBits(this.wheelSize) != Double.doubleToLongBits(other.wheelSize)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.motorization, other.motorization)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "name=" + name + ", type=" + type + ", tollClass=" + tollClass + ", motorization=" + motorization + ", mass=" + mass + ", load=" + load + ", dragCoefficient=" + dragCoefficient + ", rollingResistanceCoefficient=" + rollingResistanceCoefficient + ", wheelSize=" + wheelSize + '}';
    }

    

}
