package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class NetworkAnalysis implements DatabaseExchangable {

    private static int flag = 1;
    private int id;
    private String name;
    private List<RoadSection> bestPath;
    private List<Double> velocityPerSegment;
    private List<Double> forcePerSegment;
    private Vehicle vehicle;
    private Node beginNode;
    private Node endNode;
    private String type;
    private double load;
    private double travellTime;
    private double energyConsumption;
    private double averageVelocity;
    private double distance;
    private double tollCost;
    private double fuelMass;
    private double fuelVolume;

    public NetworkAnalysis(Node beginNode, Node endNode, Vehicle vehicle, String name, String type) {
        this.name = name;
        this.vehicle = vehicle;
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.type = type;
        this.velocityPerSegment = new LinkedList<>();
        this.bestPath = new LinkedList<>();
        this.id = flag;
        flag++;
    }

    public NetworkAnalysis(String type) {
        this.type = type;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the bestPath
     */
    public List<RoadSection> getBestPath() {
        return bestPath;
    }

    /**
     * @param bestPath the bestPath to set
     */
    public void setBestPath(List<RoadSection> bestPath) {
        this.bestPath = bestPath;
    }

    /**
     * @return the velocityPerSegment
     */
    public List<Double> getVelocityPerSegment() {
        return velocityPerSegment;
    }

    /**
     * @param velocityPerSegment the velocityPerSegment to set
     */
    public void setVelocityPerSegment(List<Double> velocityPerSegment) {
        this.velocityPerSegment = velocityPerSegment;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle the vehicle to set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the beginNode
     */
    public Node getBeginNode() {
        return beginNode;
    }

    /**
     * @param beginNode the beginNode to set
     */
    public void setBeginNode(Node beginNode) {
        this.beginNode = beginNode;
    }

    /**
     * @return the endNode
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * @param endNode the endNode to set
     */
    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the travellTime
     */
    public double getTravellTime() {
        return travellTime;
    }

    /**
     * @param travellTime the travellTime to set
     */
    public void setTravellTime(double travellTime) {
        this.travellTime = travellTime;
    }

    /**
     * @return the energyConsumption
     */
    public double getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * @param energyConsumption the energyConsumption to set
     */
    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    /**
     * @return the averageVelocity
     */
    public double getAverageVelocity() {
        return averageVelocity;
    }

    /**
     * @param averageVelocity the averageVelocity to set
     */
    public void setAverageVelocity(double averageVelocity) {
        this.averageVelocity = averageVelocity;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the tollCost
     */
    public double getTollCost() {
        return tollCost;
    }

    /**
     * @param tollCost the tollCost to set
     */
    public void setTollCost(double tollCost) {
        this.tollCost = tollCost;
    }

    /**
     * @return the forcePerSegment
     */
    public List<Double> getForcePerSegment() {
        return forcePerSegment;
    }

    /**
     * @param forcePerSegment the forcePerSegment to set
     */
    public void setForcePerSegment(List<Double> forcePerSegment) {
        this.forcePerSegment = forcePerSegment;
    }

    /**
     * @return the load
     */
    public double getLoad() {
        return load;
    }

    /**
     * @param load the load to set
     */
    public void setLoad(double load) {
        this.load = load;
    }

    /**
     * @return the fuelMass
     */
    public double getFuelMass() {
        return fuelMass;
    }

    /**
     * @param fuelMass the fuelMass to set
     */
    public void setFuelMass(double fuelMass) {
        this.fuelMass = fuelMass;
    }

    /**
     * @return the fuelVolume
     */
    public double getFuelVolume() {
        return fuelVolume;
    }

    /**
     * @param fuelVolume the fuelVolume to set
     */
    public void setFuelVolume(double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

}
