package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author
 */
public class Project extends DatabaseExchangable implements Serializable {

    private static final long serialVersionUID = 601L;

    private String name;
    private String description;
    private VehicleList vehicleList;
    private Network network;
    private List<NetworkAnalysis> netAnalysis;

    public Project(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.network = new Network();
        this.vehicleList = new VehicleList();
        this.netAnalysis = new  LinkedList<>();
    }

    public Project() {
        this.name = "n/a";
        this.description = "n/a";
        this.network = new Network();
        this.vehicleList = new VehicleList();
        this.netAnalysis = new  LinkedList<>();
    }

    public Project(Project other) {
        this.description = other.description;
        this.name = other.name;
        this.network = other.network;
        this.vehicleList = other.vehicleList;
        this.netAnalysis =other.netAnalysis;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the VehicleList
     */
    public VehicleList getVehicleList() {
        return vehicleList;
    }

    /**
     * @param vehicleList the VehicleList to set
     */
    public void setVehicleList(VehicleList vehicleList) {
        this.vehicleList = vehicleList;
    }

    /**
     * @return the network
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(Network network) {
        this.network = network;
    }

    /**
     * Returns the analysis
     *
     * @return
     */
    public List<NetworkAnalysis> getNetworkAnalysis() {
        return this.netAnalysis;
    }

    /**
     * @param network the networkanalysis to set
     */
    public void setNetworkAnalysis(List<NetworkAnalysis> net) {
        this.netAnalysis = net;
    }

    /**
     * @param network the networkanalysis to set
     */
    public void addNetworkAnalysis(NetworkAnalysis net) {
        this.netAnalysis.add(net);
    }

    public NetworkAnalysis getNetAnalByName(String name)throws NullPointerException{
        for ( NetworkAnalysis  net : this.netAnalysis) {
            if (net.getName().equalsIgnoreCase(name)) {
                return net;
            }
        }
        return null;
    }
    
    /**
     *
     * @return the integer representation of the object Project
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(name);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the Project
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
        final Project other = (Project) obj;
        return this.name.equals(other.name);
    }

    /**
     *
     * @return the String representation of the Project
     */
    @Override
    public String toString() {
        return String.format("Project: %s", name);
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
    
    public List<DatabaseExchangable> getDBNetworkAnalysisData(){
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.addAll(this.netAnalysis);
        return temp;
    }

}
