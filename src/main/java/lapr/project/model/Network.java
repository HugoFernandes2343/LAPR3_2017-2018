package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Node> nodeList;
    private List<Road> roadList;
    private List<RoadSection> sectionList;

    public Network() {
        this.roadMap = new AdjacencyMatrixGraph<>();
        this.id = "TestNetwork";
        this.description = "test";
        this.nodeList = new LinkedList<>();
        this.roadList = new LinkedList<>();
        this.sectionList = new LinkedList<>();
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
     * @return - true id the node is added, false otherwise.
     */
    public boolean addNode(String text) {
        Node node = new Node(text);
        if (!this.nodeList.contains(node)) {
            this.nodeList.add(node);
            return true;
        }
        return false;
    }

    /**
     * Adds a road to the roadList
     *
     * @param road - road that will be added
     * @return - true id the road is added, false otherwise.
     */
    public boolean addRoad(Road road) {
        if (!this.roadList.contains(road)) {
            this.roadList.add(road);
            return true;
        }
        return false;
    }

    /**
     * Adds a roadSection to the sectionList
     *
     * @param section - RoadSection
     * @return - true id the road section is added, false otherwise.
     */
    public boolean addRoadSection(RoadSection section) {
        if (!this.sectionList.contains(section)) {
            this.getSectionList().add(section);
            return true;
        }
        return false;
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

        if (roadsToAdd.getRoadMap().numEdges() == 0 && roadsToAdd.getRoadMap().numVertices() == 0) {
            return false;
        }

        for (Road r : roadsToAdd.roadList) {
            if (this.addRoad(r)) {
                flag++;
            }
        }

        for (Node n : roadsToAdd.getRoadMap().vertices()) {
            boolean insertVertex = this.getRoadMap().insertVertex(n);
            if (insertVertex) {
                this.addNode(n.getId());
                flag++;
            }
        }

        for (RoadSection rs : roadsToAdd.getRoadMap().edges()) {
            List<Node> endVertices = roadsToAdd.getRoadMap().endVertices(rs);

            if (!endVertices.isEmpty()) {
                boolean insertEdge = this.getRoadMap().insertEdge(endVertices.get(0), endVertices.get(1), rs);

                if (insertEdge) {
                    this.addRoadSection(rs);
                    flag++;
                }
            }

        }

        return flag != 0;
    }

    /**
     * @return the sectionList
     */
    public List<RoadSection> getSectionList() {
        return sectionList;
    }

    /**
     * Loads a list a inserts roads in the Road Matrix
     */
    public void loadMap() {
        for (Node vertex : nodeList) {
            this.getRoadMap().insertVertex(vertex);
        }
        for (RoadSection section : sectionList) {
            Node n1 = searchNode(section.getBegin());
            Node n2 = searchNode(section.getEnd());
            if (n1 != null && n2 != null) {
                this.getRoadMap().insertEdge(n1, n2, section);
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
    public Node searchNode(String id) {
        for (Node node : nodeList) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    /**
     * @return the roadMap
     */
    public AdjacencyMatrixGraph<Node, RoadSection> getRoadMap() {
        return roadMap;
    }

    /**
     * @return NodeList
     */
    public List<Node> getNodeList() {
        return nodeList;
    }

    /**
     * @return a List of all nodes ids
     */
    public List<String> getNodesByName() {
        List<String> names = new ArrayList<>();
        for (Node n : this.nodeList) {
            names.add(n.getId());
        }
        return names;
    }

    /**
     *
     * @param roadId the roadId to be searched
     * @return the road matching the id
     */
    public Road getRoad(String roadId) {
        for (Road r : roadList) {
            if (r.getId().equalsIgnoreCase(roadId)) {
                return r;
            }
        }
        return null;
    }

}
