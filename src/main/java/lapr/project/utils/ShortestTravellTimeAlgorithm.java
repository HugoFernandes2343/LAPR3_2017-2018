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
    public NetworkAnalysis runAlgorithm(Project project, Node begin, Node end, Vehicle vehicle, String name) {
        Throttle throttle = vehicle.getEnergy().getThrottle("25");
        Regime regime = throttle.getRegimeList().get(throttle.getRegimeList().size() - 1);
        LinkedList<Double> velocityPerSegment = new LinkedList<>();
        LinkedList<Node> path = new LinkedList<>();
        LinkedList<Double> forcePerSegment = new LinkedList<>();
        double travellTime, tollCost, distance, power, energy;
        NetworkAnalysis analysis = new ShortestTravellTimeAnalysis(begin, end, vehicle, name);
        AdjacencyMatrixGraph<Node, Double> timeMap = createEdgeAsDoubleGraph(project, vehicle);
        travellTime = EdgeAsDoubleGraphAlgorithms.shortestPath(timeMap, begin, end, path);
        LinkedList<RoadSection> bestPath = getConvertedPath(project.getNetwork().getRoadMap(), path);
        List<Double> velocityPerSection = getVelocityList(bestPath, vehicle, project, velocityPerSegment);
        distance = getTotalDistance(bestPath);
        tollCost = getTollCost(bestPath, vehicle.getTollClass(), project);
        power = Physics.getEnginePower(regime.getTorqueHigh(), regime.getRpmHigh());
        energy = getTotalEnergy(velocityPerSegment, bestPath, vehicle, regime, forcePerSegment);
        analysis.setTravellTime(travellTime);
        analysis.setBestPath(bestPath);
        analysis.setVelocityPerSegment(velocityPerSegment);
        analysis.setVelocityPerSection(velocityPerSection);
        analysis.setAverageVelocity(Physics.getVelocity(travellTime, distance));
        analysis.setDistance(distance);
        analysis.setTollCost(tollCost);
        analysis.setEnergyConsumption(energy);
        analysis.setForcePerSegment(forcePerSegment);
        analysis.setPower(power);
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
    private double getSectionTravelTime(RoadSection section, Vehicle v, String type) {
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
    private LinkedList<RoadSection> getConvertedPath(AdjacencyMatrixGraph<Node, RoadSection> roadMap, LinkedList<Node> path) {
        LinkedList<RoadSection> sectionPath = new LinkedList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            RoadSection r = roadMap.getEdge(path.get(i), path.get(i + 1));
            sectionPath.add(r);
        }
        return sectionPath;
    }

    /**
     *
     * @param bestPath path traversed by the vehicle
     * @param vehicle vehicle that trverses the path
     * @param project project that holds the data
     * @param speedBySegment speed used by the car in each segment
     * @return
     */
    private List<Double> getVelocityList(LinkedList<RoadSection> bestPath, Vehicle vehicle, Project project, LinkedList<Double> speedBySegment) {
        List<Double> velocityPerSection = new LinkedList<>();
        for (RoadSection road : bestPath) {
            String type = project.getNetwork().getRoad(road.getRoadId()).getTypology();
            velocityPerSection.add(getVelocityPerSection(road, vehicle.getVelocityLimit(type), speedBySegment));
        }
        return velocityPerSection;
    }

    /**
     *
     * @param section section that will use for Segment sorting
     * @param velocityVehicle velocity of the vehicle for the vehicle
     * @param speedBySegment speed used by the car in each segment
     * @return the average speed traversed in each Section
     */
    private double getVelocityPerSection(RoadSection section, double velocityVehicle, LinkedList<Double> speedBySegment) {
        double averageSpeed = 0;
        for (Segment segment : section.getSegmentList()) {
            double velocitySeg = Physics.convertKmPerHourToMeterPerSec(Double.valueOf(segment.getMaxVelocity().replace(" Km/h", "")));
            if (velocityVehicle < velocitySeg) {
                speedBySegment.add(velocityVehicle);
                averageSpeed = averageSpeed + velocityVehicle;
            } else {
                speedBySegment.add(velocitySeg);
                averageSpeed = averageSpeed + velocitySeg;
            }
        }
        return averageSpeed / (double) section.getSegmentList().size();
    }

    /**
     *
     * @param bestPath the path the vehicle will traverse
     * @param tollClass the vehicle toll class
     * @param project the project to be analysed
     * @return the total toll cost
     */
    private double getTollCost(LinkedList<RoadSection> bestPath, int tollClass, Project project) {
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
     * @param bestPath the path the vehicle will traverse
     * @return the total distance traversed by the car
     */
    private double getTotalDistance(LinkedList<RoadSection> bestPath) {
        double distance = 0;
        for (RoadSection road : bestPath) {
            for (Segment segment : road.getSegmentList()) {
                distance = distance + Physics.convertKmToMeter(Double.valueOf(segment.getLength().replace(" Km", "")));
            }
        }
        return distance;
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
    private double getTotalEnergy(LinkedList<Double> velocityPerSegment, LinkedList<RoadSection> bestPath, Vehicle vehicle, Regime regime, LinkedList<Double> forcePerSegment) {
        double energy = 0;
        int cont = 0;
        for (RoadSection road : bestPath) {
            for (Segment segment : road.getSegmentList()) {
                double vehicleVelocity = velocityPerSegment.get(cont);
                double mass = Double.valueOf(vehicle.getMass().replace(" Kg", ""));
                double windVelocity = Double.valueOf(segment.getWindSpeed().replace(" m/s", ""));
                double angle = segment.getWindDirection();
                double relativeVelocity = Physics.getVehicleRelativeVelocity(vehicleVelocity, windVelocity, angle);
                energy = energy + Physics.getKineticEnergy(mass, relativeVelocity);
                double gearRatio = vehicle.getEnergy().getGearList().get(vehicle.getEnergy().getGearList().size() - 1).getRatio();
                double force = Physics.getForceAppliedToVehicleOnFlatSurface(regime.getTorqueHigh(), vehicle.getEnergy().getFinalDriveRatio(), gearRatio, vehicle.getWheelSize() / 2, vehicle.getRrc(), mass, vehicle.getDrag(), vehicle.getFrontalArea(), relativeVelocity);
                forcePerSegment.add(force);
                cont++;
            }
        }
        return energy;
    }

}
