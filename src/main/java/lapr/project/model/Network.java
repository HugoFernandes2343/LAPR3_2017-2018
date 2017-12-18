/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.AdjacencyMatrixGraph;

/**
 *
 * @author
 */
public class Network implements Serializable {

    private static final long serialVersionUID = 502L;

    private AdjacencyMatrixGraph<Node, RoadSection> roadMap;
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
        if (obj == null) {
            return false;
        }
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

        if (roadsToAdd == null) {
            return false;
        }

        if (roadsToAdd.roadMap.numEdges() == 0 && roadsToAdd.roadMap.numVertices() == 0) {
            return false;
        }

        for (Road r : roadsToAdd.road_list) {
            this.addRoad(r);
        }

        for (Node n : roadsToAdd.roadMap.vertices()) {
            boolean insertVertex = this.roadMap.insertVertex(n);
            if (insertVertex) {
                this.addNode(n.getId());
                flag++;
            }
        }

        for (RoadSection rs : roadsToAdd.roadMap.edges()) {
            List<Node> endVertices = roadsToAdd.roadMap.endVertices(rs);

            if (endVertices != null) {
                boolean insertEdge = this.roadMap.insertEdge(endVertices.get(0), endVertices.get(1), rs);

                if (insertEdge) {
                    this.addRoadSection(rs);
                    flag++;
                }
            }

        }

        return flag != 0;
    }

    /**
     * @return the section_list
     */
    public LinkedList<RoadSection> getSection_list() {
        return section_list;
    }

    /**
     * Loads a list a inserts roads in the Road Matrix
     */
    public void loadMap() {
        for (Node vertex : node_list) {
            this.roadMap.insertVertex(vertex);
        }
        for (RoadSection section : section_list) {
            Node n1 = searchNode(section.getBegin());
            Node n2 = searchNode(section.getEnd());
            if (n1 != null && n2 != null) {
                this.roadMap.insertEdge(n1, n2, section);
            }
        }
    }

    /**
     * Method to find the node corresponding to the given id it returns the node
     * if it exists or null if not
     *
     * @param id the Node id to be searched in the node list
     * @return
     */
    private Node searchNode(String id) {
        for (Node node : node_list) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }
}
