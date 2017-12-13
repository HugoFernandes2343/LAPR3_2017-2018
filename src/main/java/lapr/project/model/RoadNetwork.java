/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.AdjacencyMatrixGraph;

/**
 *
 * @author hugod
 */
public class RoadNetwork {

    private AdjacencyMatrixGraph<Junction, Road> roadMap;

    public RoadNetwork() {
        this.roadMap = new AdjacencyMatrixGraph<>();
    }

    /**
     * @return the roadMap
     */
    public AdjacencyMatrixGraph<Junction, Road> getRoadMap() {
        return roadMap;
    }
    
    
}
