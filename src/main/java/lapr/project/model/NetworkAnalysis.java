package lapr.project.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Utilizador
 */


public abstract class NetworkAnalysis {

    private static int flag = 1;
    private int id;
    private String name;
    private List<RoadSection> bestPath;
    private List<Double> velocityPerSegment;
    private List<Double> velocityPerSection;
    private Vehicle vehicle;
    private Node beginNode;
    private Node endNode;
    private String type;
    private double travellTime;
    private double energyConsumption;
    private double averageVelocity;
    private double distance;
    private double tollCost;

    public NetworkAnalysis(Node beginNode, Node endNode, Vehicle vehicle, String name, String type) {
        this.name = name;
        this.vehicle = vehicle;
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.velocityPerSegment = new LinkedList<>();
        this.bestPath = new LinkedList<>();
        this.id = flag;
        flag++;
    }

    public NetworkAnalysis(String type){
        this.type=type;
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
     * @return the velocityPerSection
     */
    public List<Double> getVelocityPerSection() {
        return velocityPerSection;
    }

    /**
     * @param velocityPerSection the velocityPerSection to set
     */
    public void setVelocityPerSection(List<Double> velocityPerSection) {
        this.velocityPerSection = velocityPerSection;
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

}
