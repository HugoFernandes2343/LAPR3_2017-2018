/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import lapr.project.utils.AdjacencyMatrixGraph;

/**
 *
 * @author hugod
 */
public class Network {

    private AdjacencyMatrixGraph<Node, Road> roadMap;
    private String id;
    private String description;
    private LinkedList<Node> node_list;
    private LinkedList<Node> road_list;
    private LinkedList<RoadSection> section_list;

    public Network() {
        this.roadMap = new AdjacencyMatrixGraph<>();
    }

    /**
     * @return the roadMap
     */
    public AdjacencyMatrixGraph<Node, Road> getRoadMap() {
        return roadMap;
    }

}
