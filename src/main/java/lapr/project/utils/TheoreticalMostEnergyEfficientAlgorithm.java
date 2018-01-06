/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.RoadSection;
import lapr.project.model.TheoreticalMostEnergyEfficientAnalysis;
import lapr.project.model.Vehicle;

/**
 *
 * @author filip
 */
public class TheoreticalMostEnergyEfficientAlgorithm implements Algorithm {

    /**
     * Constant that keeps the position of the first node.
     */
    private final static int NODE_POSITION = 0;

    /**
     * Constant that keeps the position of the second node.
     */
    private final static int NODE_SECOND_POSITION = 1;

    /**
     * Constant that keeps the name of this algorithm.
     */
    private final static String NAME = "Theoretical Most Energy Efficient Path N(11)";

    /**
     * Atribute that keeps the chosen acceleration for the process of
     * acelerating.
     */
    private double aceleratingAcceleration;

    /**
     * Atribute that keeps the chosen acceleration for the process of braking.
     */
    private double brakingAcceleration;

    /**
     * Method that runs the algorithm.
     *
     * @param project - the project that is being analyzed.
     * @param begin - the begin point.
     * @param end - the end point.
     * @param vehicle - vehicle that is being used.
     * @param name - name that the user wants to give to this analysis.
     * @param load - load of the car.
     * @return an object Network Analysis with the results necessary to present
     * to the user.
     */
    @Override
    public NetworkAnalysis runAlgorithm(Project project, Node begin, Node end, Vehicle vehicle, String name, double load) {
        AdjacencyMatrixGraph<Node, Double> edgeAsDouble = edgeAsDouble(project.getNetwork().getRoadMap(), vehicle);

        LinkedList<Node> shortestPath = new LinkedList<>();
        EdgeAsDoubleGraphAlgorithms.shortestPath(edgeAsDouble, begin, end, shortestPath);

        TheoreticalMostEnergyEfficientAnalysis analysis = new TheoreticalMostEnergyEfficientAnalysis();

        return analysis;
    }

    /**
     * Method that calculates all the road sections energy and creates a
     * adjacency matrix with the edges as double with all the values of the
     * energy.
     *
     * @param roadMap - Original adjacency matrix with all the sections and
     * nodes.
     * @param vehicle - vehicle that is being used to travel.
     * @return - a graph where the edges are a double and represent the cost of
     * the travel.
     */
    private AdjacencyMatrixGraph<Node, Double> edgeAsDouble(AdjacencyMatrixGraph<Node, RoadSection> roadMap, Vehicle vehicle) {
        AdjacencyMatrixGraph<Node, Double> edgeAsDouble = new AdjacencyMatrixGraph<>();

        if (roadMap.numEdges == 0) {
            return null;
        }

        for (Node n : roadMap.vertices) {
            edgeAsDouble.insertVertex(n);
        }

        for (RoadSection rs : roadMap.edges()) {
            List<Node> endVertices = roadMap.endVertices(rs);

            if (endVertices.isEmpty()) {
                break;
            }

            edgeAsDouble.insertEdge(endVertices.get(NODE_POSITION), endVertices.get(NODE_SECOND_POSITION), calculateSectionEnergy(rs, vehicle));
        }

        return edgeAsDouble;
    }

    /**
     * Method that calculates the cost, in energy, to go through a road section.
     *
     * @param rs - road section to calculate the energy cost
     * @param vehicle - vehicle that is being used to go through this road
     * section.
     * @return a double that represent the energy costs.
     */
    private double calculateSectionEnergy(RoadSection rs, Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Deescription of this algorithm.
     *
     * @return the name of the algorithm for UI presentation.
     */
    @Override
    public String toString() {
        return String.format("Algorithm: %s", NAME);
    }

    /**
     * Setter of the acelerating Acceleration
     * @param aceleratingAcceleration the aceleratingAcceleration to set
     */
    public void setAceleratingAcceleration(double aceleratingAcceleration) {
        this.aceleratingAcceleration = aceleratingAcceleration;
    }

    /**
     * Setter of the braking acceleration
     * @param brakingAcceleration the brakingAcceleration to set
     */
    public void setBrakingAcceleration(double brakingAcceleration) {
        this.brakingAcceleration = brakingAcceleration;
    }
    
    
}
