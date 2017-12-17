/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;
import lapr.project.utils.AdjacencyMatrixGraph;

/**
 *
 * @author
 */
public class Network implements Serializable {

    private static final long serialVersionUID = 502L;

    private AdjacencyMatrixGraph<Node, Road> roadMap;
    private String id;
    private String description;
    private LinkedList<Node> node_list;
    private LinkedList<Road> road_list;
    private LinkedList<RoadSection> section_list;

    public Network() {
        this.roadMap = new AdjacencyMatrixGraph<>();
        this.id = "TestNetwork";
        this.description = "test";
        this.node_list = new LinkedList<>();
        this.road_list = new LinkedList<>();
        this.section_list = new LinkedList<>();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Defines the Description of the Network
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds a node to the node list if is not in there already
     *
     * @param text - node id to be added
     */
    public void addNode(String text) {
        Node node = new Node(text);
        if (!this.node_list.contains(node)) {
            this.node_list.add(node);
        }
    }

    /**
     * Adds a road to the road_list
     *
     * @param road - road that will be added
     */
    public void addRoad(Road road) {
        if (!this.road_list.contains(road)) {
            this.road_list.add(road);
        }
    }

    /**
     * Adds a roadSection to the section_list
     *
     * @param section - RoadSection
     */
    public void addRoadSection(RoadSection section) {
        if (!this.section_list.contains(section)) {
            this.getSection_list().add(section);
        }
    }

    /**
     *
     * @param obj the object to compare to the gear
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Network other = (Network) obj;
        return this.id.equalsIgnoreCase(other.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * to string for network
     *
     * @return
     */
    @Override
    public String toString() {
        return "Network{" + "id=" + id + "}";
    }

    /**
     * Method that receives a Network with all the new roads to add to current
     * project.
     *
     * @param roadsToAdd - network with the new roads.
     * @return true if it is possible to add the new roads, false otherwise.
     */
    public boolean addNewRoadsFromNetwork(Network roadsToAdd) {
        int flag = 0;
        
        if (roadsToAdd.roadMap.numEdges() == 0 && roadsToAdd.roadMap.numVertices() == 0) {
            return false;
        }
        
        for(Node n : roadsToAdd.roadMap.vertices()){
             boolean insertVertex = roadsToAdd.roadMap.insertVertex(n);
             if(insertVertex){
                addNode(n.getId());
                flag ++; 
             }
        }
        
        for (Road r : roadsToAdd.roadMap.edges()) {
            Node[] endVertices = roadsToAdd.roadMap.endVertices(r);

            if (endVertices != null) {
                boolean insertEdge = roadsToAdd.roadMap.insertEdge(endVertices[0], endVertices[1], r);
                
                if(insertEdge){
                    addRoad(r);
                    flag ++;
                }
            }
            
        }

        for (RoadSection s : roadsToAdd.getSection_list()) {
            addRoadSection(s);
        }

        return flag != 0;
    }

    /**
     * @return the section_list
     */
    public LinkedList<RoadSection> getSection_list() {
        return section_list;
    }
}
