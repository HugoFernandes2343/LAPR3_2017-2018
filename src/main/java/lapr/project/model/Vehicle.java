package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.DatabaseExchangable;

public class Vehicle extends DatabaseExchangable implements Serializable {

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
    private double frontalArea;
    private double rrc;
    private double wheelSize;
    private Energy energy;
    private VelocityLimitList velocityLimitList;

    /**
     * Empty constructor for the vehicle object
     *
     */
    public Vehicle() {
        this.velocityLimitList = new VelocityLimitList();

    }

    /**
     * Complete constructor of the vehicle type object
     *
     * @param name name to be given to the vehicle
     * @param description description given to the vehicle
     * @param type type of vehicle
     * @param tollClass class of the vehicle
     * @param motorization type of motorization of the vehicle
     * @param fuel type of fuel used by the vehicle
     * @param mass total mass of the vehicle
     * @param load load capacity of the vehicle
     * @param drag drag coeficient of the vehicle
     * @param frontalArea frontal area of the vehicle
     * @param rrc rrc of the vehicle
     * @param wheelSize wheelSize of the vehicle
     * @param energy energy object to be given to the vehicle
     * @param velocityLimitList velocity limit list object to be given to the
     * vehicle
     */
    public Vehicle(String name, String description, String type, int tollClass, String motorization, String fuel, String mass, String load, double drag, double frontalArea, double rrc, double wheelSize, Energy energy, VelocityLimitList velocityLimitList) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.tollClass = tollClass;
        this.motorization = motorization;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.drag = drag;
        this.frontalArea = frontalArea;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.energy = energy;
        this.velocityLimitList = velocityLimitList;

    }

    /**
     * Constructor that only recives the vehicle name
     *
     * @param name name of the vehicle being created
     */
    public Vehicle(String name) {
        this.name = name;
    }

    /**
     * Set of the name variable to the vehicle
     *
     * @param name name to be set onto the vehicle
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set of the description variable to the vehicle
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set of the type variable to the vehicle
     *
     * @param type type to be given to vehicle
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set of the tollClass variable to the vehicle
     *
     * @param tollClass tollClass to be given to the vehicle
     */
    public void setTollClass(int tollClass) {
        this.tollClass = tollClass;
    }

    /**
     * Set of the motorization variable to the vehicle
     *
     * @param motorization motorization to be given to the vehicle
     */
    public void setMotorization(String motorization) {
        this.motorization = motorization;
    }

    /**
     * Set of the mass variable to the vehicle
     *
     * @param mass mass to be given to the vehicle
     */
    public void setMass(String mass) {
        this.mass = mass;
    }

    /**
     * Set of the load variable to the vehicle
     *
     * @param load load to be given to the vehicle
     */
    public void setLoad(String load) {
        this.load = load;
    }

    /**
     * Set of the drag variable to the vehicle
     *
     * @param drag drag to be given to the vehicle
     */
    public void setDrag(double drag) {
        this.drag = drag;
    }

    /**
     * Set of the frontal area variable to the vehicle
     *
     * @param frontalArea frontal area to be given to the vehicle
     */
    public void setFrontalArea(double frontalArea) {
        this.frontalArea = frontalArea;
    }

    /**
     * Set of the rrc variable to the vehicle
     *
     * @param rrc rrc to be given to the vehicle
     */
    public void setRrc(double rrc) {
        this.rrc = rrc;
    }

    /**
     * Set of the wheelSize variable to the vehicle
     *
     * @param wheelSize wheelSize to be set to the vehicle
     */
    public void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    /**
     * Set of the fuel variable to the vehicle
     *
     * @param fuel the fuel to set
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * Set of the energy object to the vehicle
     *
     * @param energy energy object ot be given to the vehicle
     */
    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    /**
     * Set of the velocity limit list object variable to the vehicle
     *
     * @param velocityLimitList object of type velocity limit list to the
     * vehicle
     */
    public void setVelocityLimitList(VelocityLimitList velocityLimitList) {
        this.velocityLimitList = velocityLimitList;
    }

    /**
     * @return the name name of the vehicle
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type type of the vehicle
     */
    public String getType() {
        return type;
    }

    /**
     * @return the tollClass tollClass of the vehicle
     */
    public int getTollClass() {
        return tollClass;
    }

    /**
     * @return the motorization motorization of the vehicle
     */
    public String getMotorization() {
        return motorization;
    }

    /**
     * @return the mass mass of the vehicle
     */
    public String getMass() {
        return mass;
    }

    /**
     * @return the load load of the vehicle
     */
    public String getLoad() {
        return load;
    }

    /**
     * @return the drag drag coeficicient of the vehicle
     */
    public double getDrag() {
        return drag;
    }

    /**
     * @return the rrc rrc of the vehicle
     */
    public double getRrc() {
        return rrc;
    }

    /**
     * @return the wheelSize wheel size of the vehicle
     */
    public double getWheelSize() {
        return wheelSize;
    }

    /**
     * @return the description description of the vehicle
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the fuel fuel of the vehicle
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * @return the frontal_area frontal area of the vehicle
     */
    public double getFrontalArea() {
        return frontalArea;
    }

    /**
     * @return the energy energy object of the vehicle
     */
    public Energy getEnergy() {
        return energy;
    }

    /**
     * @return the velocity_limit_list velocity limit list type object of the
     * vehicle
     */
    public VelocityLimitList getVelocityLimitList() {
        return velocityLimitList;
    }

    /**
     * Hashcode method of the vehicle
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
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.frontalArea) ^ (Double.doubleToLongBits(this.frontalArea) >>> 32));
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
        if (Double.doubleToLongBits(this.drag) != Double.doubleToLongBits(other.drag)) {
            return false;
        }
        if (Double.doubleToLongBits(this.frontalArea) != Double.doubleToLongBits(other.frontalArea)) {
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

        return Objects.equals(this.load, other.load);
    }

    /**
     *
     * @param type the type of road that will be used as search criteria
     * @return the max velocity for specific type
     */
    public int getVelocityLimit(String type) {
        return velocityLimitList.getVelocityLimit(type);
    }

    /**
     * To string method of the vehicle class
     *
     * @return the String representation of the Vehicle
     */
    @Override
    public String toString() {
        return String.format("Vehicle: %s", name);
    }

     /**
     * method that returns the data to relate to the dataBase
     */
    @Override
    public List<DatabaseExchangable> getDBData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.add(this);
        return temp;
    }
}
