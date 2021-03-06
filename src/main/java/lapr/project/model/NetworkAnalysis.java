package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author
 */
public class NetworkAnalysis extends DatabaseExchangable {

    private static int flag = 0;

    private int id;
    private String name;
    private BestPath bestPath;
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
    private double aceleratingAcceleration;
    private double brakingAcceleration;

    /**
     * Full constructor
     *
     * @param beginNode
     * @param endNode
     * @param vehicle
     * @param name
     * @param type
     */
    public NetworkAnalysis(Node beginNode, Node endNode, Vehicle vehicle, String name, String type) {
        this.name = name;
        this.vehicle = vehicle;
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.type = type;
        this.velocityPerSegment = new LinkedList<>();
        this.forcePerSegment = new LinkedList<>();
        this.bestPath = new BestPath();
        this.id = flag;
        flag++;
    }

    /**
     * empty constructor
     *
     * @param type
     */
    public NetworkAnalysis(String type) {
        this.type = type;
        this.id = flag;
        flag++;
    }

    /**
     * set the id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public static void setFlag(int newFlag) {
        flag = newFlag;
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

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the bestPath
     */
    public List<RoadSection> getBestPath() {
        return bestPath.getSectionList();
    }

    /**
     * @param bestPath the bestPath to set
     */
    public void setBestPath(List<RoadSection> bestPath) {
        this.bestPath.setSectionList(bestPath);
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

    /**
     * @return Acceleration
     */
    public double getAceleratingAcceleration() {
        return aceleratingAcceleration;
    }

    /**
     * @return braking accelaration
     */
    public double getBrakingAcceleration() {
        return brakingAcceleration;
    }

    /**
     * Setter of aceleratingAcceleration
     *
     * @param aceleratingAcceleration - car acceleration
     */
    public void setAceleratingAcceleration(double aceleratingAcceleration) {
        this.aceleratingAcceleration = aceleratingAcceleration;
    }

    /**
     * Setter of Car Braking Acceleration
     *
     * @param brakingAcceleration - Car Braking Acceleration
     */
    public void setBrakingAcceleration(double brakingAcceleration) {
        this.brakingAcceleration = brakingAcceleration;
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

    /**
     * Hash code fo the object node
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the network analysis
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
        NetworkAnalysis other = (NetworkAnalysis) obj;
        return name.equalsIgnoreCase(other.name);

    }

}
