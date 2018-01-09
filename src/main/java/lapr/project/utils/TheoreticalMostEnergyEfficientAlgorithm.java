/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Gear;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Regime;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.TheoreticalMostEnergyEfficientAnalysis;
import lapr.project.model.Throttle;
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
     * The total mass of the vehicle, including the load.
     */
    private double totalMass;

    /**
     * Atribute to keep the project being analyzed
     */
    private Project projectAnalyzed;

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
        this.totalMass = Double.parseDouble(vehicle.getMass().replace(" Km", "")) + load;
        this.projectAnalyzed = project;
        TheoreticalMostEnergyEfficientAnalysis analysis = new TheoreticalMostEnergyEfficientAnalysis(begin, end, vehicle, name);

        //discovering the best path and coverting it      
        LinkedList<Node> shortestPath = new LinkedList<>();
        double energy = EdgeAsDoubleGraphAlgorithms.shortestPath(edgeAsDouble, begin, end, shortestPath);
        List<RoadSection> path = recreatePath(shortestPath);
        analysis.setBestPath(path);

        //Setting the velocity per segment of the analysis.
        //Setting the force per segment in this analysis.
        //Setting the load used in this analysis
        analysis.setLoad(load);

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
        List<Segment> segmentList = rs.getSegmentList();
        double sectionEnergy = 0;
        double lastVelocity;

        Segment first = segmentList.get(NODE_POSITION);
        lastVelocity = discoverVelocity(vehicle, first, this.projectAnalyzed.getNetwork().getRoad(first.getId()).getTypology());
        sectionEnergy += calculateSegmentEnergy(first, 0, lastVelocity, vehicle);

        for (int i = 1; i < segmentList.size() - 1; i++) {
            Segment actual = segmentList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(first.getId()).getTypology());
            sectionEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, vehicle);
        }

        Segment last = segmentList.get(segmentList.size() - 1);
        sectionEnergy += calculateSegmentEnergy(last, lastVelocity, 0, vehicle);

        return sectionEnergy;
    }

    /**
     * Method that receives the segment, the vehicle and the type of the road
     * and indicates what velocity should be used to go through the segment.
     *
     * @param vehicle - vehicle used
     * @param segment - segment to go through
     * @param type - type of the road of thi segment
     * @return - a double indicating the velocity.
     */
    private double discoverVelocity(Vehicle vehicle, Segment segment, String type) {
        double vehicleVelocity = Physics.convertKmPerHourToMeterPerSec(vehicle.getVelocityLimit(type));
        double segmentVelocity = Physics.convertKmPerHourToMeterPerSec(Double.parseDouble(segment.getMaxVelocity().replace(" Km", "")));

        if (segmentVelocity > vehicleVelocity) {
            return vehicleVelocity;
        }

        return segmentVelocity;
    }

    /**
     * Method that calculates the energy consumed to go through a segment.
     *
     * @return double with the energy or zero if it is not possible to calculate
     * the energy
     */
    private double calculateSegmentEnergy(Segment segment, double lastVelocity, double newVelocity, Vehicle vehicle) {
        double energy = 0;

        if (lastVelocity > newVelocity) { // In this case the car should decrease the velocity so the calculations should be made accordingly.

            //decreasing velocity
            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, this.brakingAcceleration), this.brakingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            double values[] = new double[10];
            Gear gear = discoverGear(values, this.brakingAcceleration, vehicle, vr, angle);
            double kinematicFunctions = Physics.kinematicFunctions(lastVelocity, newVelocity, this.brakingAcceleration);
            energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * kinematicFunctions;

            //constant velocity
            double values1[] = new double[10];
            double vr1 = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            Gear gear1 = discoverGear(values1, 0, vehicle, vr1, angle);
            double power = Physics.getEnginePower(values1[0], values1[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - kinematicFunctions);
            return energy;

        } else if (lastVelocity < newVelocity) { // In this case the car should increase the velocity so the calculations should be made accordingly.
            
            //increasing velocity
            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, this.aceleratingAcceleration), this.aceleratingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            double values[] = new double[10];
            Gear gear = discoverGear(values, this.aceleratingAcceleration, vehicle, vr, angle);
            double kinematicFunctions = Physics.kinematicFunctions(lastVelocity, newVelocity, this.aceleratingAcceleration);
            energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * kinematicFunctions;

            //constant velocity
            double values1[] = new double[10];
            double vr1 = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            Gear gear1 = discoverGear(values1, 0, vehicle, vr1, angle);
            double power = Physics.getEnginePower(values1[0], values1[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - kinematicFunctions);
            return energy;

        } else if (lastVelocity == newVelocity) { // In this case the car shouldn't alter the velocity so the calculations should be made accordingly.
            
            //constant velocity
            double values[] = new double[1];
            double vr = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            Gear gear = discoverGear(values, 0, vehicle, vr, angle);
            double power = Physics.getEnginePower(values[0], values[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))));
            return energy;

        }

        return energy;
    }

    /**
     * Method that creates a list with the velocities of the time that the
     * vehicle velocity is being increased
     *
     * @param lastVelocity - initial velocity of the vehicle.
     * @param newVelocity - final velocity of the vehicle
     * @param time - time that the vehicle takes to reach the final velocity
     * @param acceleration - acceleration of the movement.
     *
     * @return a list with all the velocitites.
     */
    private List<Double> getVelocities(double lastVelocity, double time, double acceleration) {
        List<Double> velocities = new ArrayList<>();

        for (double i = 0; i <= time; i++) {
            velocities.add(Physics.kinematicFunctionsGetVelocity(lastVelocity, acceleration, time));
        }

        return velocities;
    }

    /**
     * Method used to discover the correct gear to be used.
     *
     * @param values - array to keep some necessary values for other methods.
     * @param acceleration - acceleration to keep track
     * @param v - vehicle that is being used to travel.
     * @return - the gear that should be used.
     */
    private Gear discoverGear(double[] values, double acceleration, Vehicle v, double vr, double angle) {
        List<String> percentages = new ArrayList<>();
        percentages.add("25");
        percentages.add("50");
        percentages.add("100");

        if (acceleration < 0) {
            acceleration = -acceleration;
        }

        List<Gear> gears = v.getEnergy().getGearList();
        for (int i = gears.size() - 1; i >= 0; i++) {
            Gear g = gears.get(i);
            for (int j = 0; i < percentages.size(); i++) {
                Throttle t = v.getEnergy().getThrottle(percentages.get(j));
                Regime r = getLowestSFCRegime(t);
                for (int torque = r.getTorqueLow(); torque < r.getTorqueHigh(); torque++) {
                    if (acceleration == 0) {
                        if (Physics.getForceAppliedToVehicle(torque, v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) <= 0) {
                            values[0] = torque;
                            values[1] = r.getRpmLow();
                            return g;
                        }
                    } else {
                        if (Physics.getForceAppliedToVehicle(torque, v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) > this.totalMass * acceleration) {
                            values[0] = torque;
                            values[1] = r.getRpmLow();
                            return g;
                        }
                    }
                }
            }
        }
        values[0] = getLowestSFCRegime(v.getEnergy().getThrottle(percentages.get(0))).getTorqueLow();
        values[1] = getLowestSFCRegime(v.getEnergy().getThrottle(percentages.get(0))).getRpmLow();
        return gears.get(0);
    }

    /**
     * Returns the Regime with the Lowest SFC int the same throttle
     *
     * @param t - thottle where is supposed to find the regime with the lowest
     * SFC
     * @return - regime with the lowest SFC, or null if doesn't exists any.
     */
    private Regime getLowestSFCRegime(Throttle t) {
        double sfc = Double.MAX_VALUE;
        Regime reg = null;
        for (Regime r : t.getRegimeList()) {
            if (r.getSfc() < sfc) {
                sfc = r.getSfc();
                reg = r;
            }
        }

        return reg;
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
     * Method that receives a list with all the nodes in the path and recreates
     * it using all the road sections necessary to pass.
     *
     * @param shortestPath - List with the shortest path in nodes
     * @return - List with the same path but in road sections.
     */
    private List<RoadSection> recreatePath(LinkedList<Node> shortestPath) {
        List<RoadSection> path = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            RoadSection r = this.projectAnalyzed.getNetwork().getRoadMap().getEdge(shortestPath.get(i), shortestPath.get(i + 1));
            path.add(r);
        }
        return path;

    }

    /**
     * Setter of the acelerating Acceleration
     *
     * @param aceleratingAcceleration the aceleratingAcceleration to set
     */
    public void setAceleratingAcceleration(double aceleratingAcceleration) {
        this.aceleratingAcceleration = aceleratingAcceleration;
    }

    /**
     * Setter of the braking acceleration
     *
     * @param brakingAcceleration the brakingAcceleration to set
     */
    public void setBrakingAcceleration(double brakingAcceleration) {
        this.brakingAcceleration = brakingAcceleration;
    }
}
