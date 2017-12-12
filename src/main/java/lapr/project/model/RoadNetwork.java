/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
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

    @Override
    public String toString() {
        return "RoadNetwork{" + "matrix=" + roadMap + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.roadMap);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoadNetwork other = (RoadNetwork) obj;
        if (!Objects.equals(this.roadMap, other.roadMap)) {
            return false;
        }
        return true;
    }

}
