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
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.ShortestTravellTimeAnalysis;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;

/**
 *
 * @author Hugo
 */
public class ShortestTravellTimeAlgorithm implements Algorithm {

    private static final String ALGORITHM = "Shortest Travell Time (N10)";

    @Override
    public NetworkAnalysis runAlgorithm(Project project, Node begin, Node end, Vehicle vehicle, String name, double load) {
        Throttle throttle = vehicle.getEnergy().getThrottle("25");
        Regime regime = throttle.getRegimeList().get(throttle.getRegimeList().size() - 1);
        LinkedList<Double> velocityPerSegment = new LinkedList<>();
        LinkedList<Node> path = new LinkedList<>();
        LinkedList<Double> forcePerSegment = new LinkedList<>();
        double travellTime;
        double tollCost;
        double distance;
        double energy;
        NetworkAnalysis analysis = new ShortestTravellTimeAnalysis(begin, end, vehicle, name);
        AdjacencyMatrixGraph<Node, Double> timeMap = createEdgeAsDoubleGraph(project, vehicle);
        travellTime = EdgeAsDoubleGraphAlgorithms.shortestPath(timeMap, begin, end, path);
        LinkedList<RoadSection> bestPath = getConvertedPath(project.getNetwork().getRoadMap(), path);
        distance = getBaseData(project, bestPath, velocityPerSegment, vehicle);
        tollCost = getTollCost(bestPath, vehicle.getTollClass(), project);
        energy = getTotalEnergy(velocityPerSegment, bestPath, vehicle, regime, forcePerSegment, load);
        analysis.setTravellTime(travellTime);
        analysis.setBestPath(bestPath);
        analysis.setVelocityPerSegment(velocityPerSegment);
        analysis.setAverageVelocity(Physics.getVelocity(travellTime, distance));
        analysis.setDistance(distance);
        analysis.setTollCost(tollCost);
        analysis.setEnergyConsumption(energy);
        analysis.setForcePerSegment(forcePerSegment);
        analysis.setLoad(load);
        analysis.setFuelMass(0);
        analysis.setFuelVolume(0);
        if (vehicle.getFuel().equals("diesel")) {
            analysis.setFuelMass(energy / DIESEL_FUEL_MASS);
            analysis.setFuelVolume(analysis.getFuelMass() / DIESEL_FUEL_DENSITY);
        }
        if (vehicle.getFuel().equals("gasoline")) {
            analysis.setFuelMass(energy / GASOLINE_FUEL_MASS);
            analysis.setFuelVolume(analysis.getFuelMass() / GASOLINE_FUEL_DENSITY);
        }
        return analysis;
    }

    /**
     *
     * @param project the project that contains this analysis data
     * @param vehicle the vehicle that will traverse the path
     * @return the new version of the roadMap with its edges converted in time
     */
    private AdjacencyMatrixGraph<Node, Double> createEdgeAsDoubleGraph(Project project, Vehicle vehicle) {
        AdjacencyMatrixGraph<Node, Double> graph = new AdjacencyMatrixGraph<>();
        List<RoadSection> roadSections = project.getNetwork().getSectionList();
        List<Node> nodes = project.getNetwork().getNodeList();
        for (Node n : nodes) {
            graph.insertVertex(n);
        }
        for (RoadSection rs : roadSections) {
            Road test = project.getNetwork().getRoad(rs.getRoadId());
            Node begin = project.getNetwork().searchNode(rs.getBegin());
            Node end = project.getNetwork().searchNode(rs.getEnd());
            String type = test.getTypology();
            graph.insertEdge(begin, end, getSectionTravelTime(rs, vehicle, type));
        }
        return graph;
    }

    @Override
    public String toString() {
        return String.format("Algorithm: %s", ALGORITHM);
    }

    /**
     *
     * @param section the roadSection to be analised
     * @param v the vehicle
     * @param type type of roadSection
     * @return the time it would take to traverse this section
     */
    private static double getSectionTravelTime(RoadSection section, Vehicle v, String type) {
        double travelTime = 0;
        double velocityVehicle = Physics.convertKmPerHourToMeterPerSec(v.getVelocityLimit(type));
        for (Segment segment : section.getSegmentList()) {
            double velocitySeg = Physics.convertKmPerHourToMeterPerSec(Double.valueOf(segment.getMaxVelocity().replace(" Km/h", "")));
            double distance = Physics.convertKmToMeter(Double.valueOf(segment.getLength().replace(" Km", "")));
            if (velocityVehicle < velocitySeg) {
                travelTime = travelTime + Physics.getTime(velocityVehicle, distance);
            } else {
                travelTime = travelTime + Physics.getTime(velocitySeg, distance);
            }
        }
        return travelTime;
    }

    /**
     *
     * @param roadMap the matrix for edge search based on vertices
     * @param path the list of vertices that connect each roadSection
     * @return the list of roadSections traversed until destination
     */
    private static LinkedList<RoadSection> getConvertedPath(AdjacencyMatrixGraph<Node, RoadSection> roadMap, LinkedList<Node> path) {
        LinkedList<RoadSection> sectionPath = new LinkedList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            RoadSection r = roadMap.getEdge(path.get(i), path.get(i + 1));
            sectionPath.add(r);
        }
        return sectionPath;
    }

    private static double getBaseData(Project project, LinkedList<RoadSection> bestPath, LinkedList<Double> velocityPerSegment, Vehicle vehicle) {
        double distance = 0;
        for (RoadSection section : bestPath) {
            String type = project.getNetwork().getRoad(section.getRoadId()).getTypology();
            double velocityVehicle = Physics.convertKmPerHourToMeterPerSec(vehicle.getVelocityLimit(type));
            for (Segment segment : section.getSegmentList()) {
                distance = distance + Physics.convertKmToMeter(Double.valueOf(segment.getLength().replace(" Km", "")));
                double velocitySeg = Physics.convertKmPerHourToMeterPerSec(Double.valueOf(segment.getMaxVelocity().replace(" Km/h", "")));
                if (velocityVehicle < velocitySeg) {
                    velocityPerSegment.add(velocityVehicle);
                } else {
                    velocityPerSegment.add(velocitySeg);
                }
            }
        }
        return distance;
    }

    /**
     *
     * @param bestPath the path the vehicle will traverse
     * @param tollClass the vehicle toll class
     * @param project the project to be analysed
     * @return the total toll cost
     */
    private static double getTollCost(LinkedList<RoadSection> bestPath, int tollClass, Project project) {
        double tollCost = 0;
        for (RoadSection road : bestPath) {
            if (!project.getNetwork().getRoad(road.getRoadId()).getTollFare().getListClasses().isEmpty()) {
                tollCost = tollCost + project.getNetwork().getRoad(road.getRoadId()).getTollFare().getListClasses().get(tollClass).getPrice();
            }
        }
        return tollCost;
    }

    /**
     *
     * @param velocityPerSegment velocity used by the car per segment
     * @param bestPath the path the vehicle will traverse
     * @param vehicle the test vehicle
     * @param regime the vehicle regime
     * @param forcePerSegment force applied in the vehicle for each segment
     * @return the total energy the vehicle has used during the run
     */
    private static double getTotalEnergy(LinkedList<Double> velocityPerSegment, LinkedList<RoadSection> bestPath, Vehicle vehicle, Regime regime, LinkedList<Double> forcePerSegment, double load) {
        double energy = 0;
        int cont = 0;
        double power = Physics.getEnginePower(regime.getTorqueHigh(), regime.getRpmHigh());
        for (RoadSection road : bestPath) {
            for (Segment segment : road.getSegmentList()) {
                double distance = Physics.convertKmToMeter(Double.valueOf(segment.getLength().replace(" Km", "")));
                double vehicleVelocity = velocityPerSegment.get(cont);
                double force = 0;
                energy = energy + Physics.getEnergy(power, Physics.getTime(vehicleVelocity, distance));
                forcePerSegment.add(force);
                cont++;
            }
        }
        return energy;
    }
}
