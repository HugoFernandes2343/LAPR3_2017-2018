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
    private static final int NODE_POSITION = 0;

    /**
     * Constant that keeps the position of the second node.
     */
    private static final int NODE_SECOND_POSITION = 1;

    /**
     * Constant that keeps the name of this algorithm.
     */
    private static final String NAME = "Theoretical Most Energy Efficient Path N(11)";

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
     * List that keeps the velocity travelled by the vehicle in each segment
     */
    private List<Double> velocityPerSegment;

    /**
     * Vehicle used in this analysis.
     */
    private Vehicle vehicle;

    /**
     * Method that runs the algorithm.
     *
     * @param project - the project that is being analyzed.
     * @param begin - the begin point.
     * @param end - the end point.
     * @param vehicle - vehicle that is being used.
     * @param name - name that the user wants to give to this analysis.
     * @param load - load of the car.
     *
     * @return an object Network Analysis with the results necessary to present
     * to the user.
     */
    @Override
    public NetworkAnalysis runAlgorithm(Project project, Node begin, Node end, Vehicle vehicle, String name, double load) {
        AdjacencyMatrixGraph<Node, Double> edgeAsDouble = edgeAsDouble(project.getNetwork().getRoadMap(), vehicle);
        this.totalMass = Double.parseDouble(vehicle.getMass().replace(" Km", "")) + load;
        this.projectAnalyzed = project;
        this.velocityPerSegment = new ArrayList<>();
        this.vehicle = vehicle;
        TheoreticalMostEnergyEfficientAnalysis analysis = new TheoreticalMostEnergyEfficientAnalysis(begin, end, vehicle, name);
        analysis.setAceleratingAcceleration(this.aceleratingAcceleration);
        analysis.setBrakingAcceleration(this.brakingAcceleration);

        //discovering the best path and coverting it.
        LinkedList<Node> shortestPath = new LinkedList<>();
        EdgeAsDoubleGraphAlgorithms.shortestPath(edgeAsDouble, begin, end, shortestPath);
        List<RoadSection> path = recreatePath(shortestPath);
        analysis.setBestPath(path);

        //Preparing the following steps.
        double[] values = new double[2];
        double totalEnergy = getNecessaryData(path, values);

        //Setting the velocity per segment of the analysis.
        analysis.setVelocityPerSegment(velocityPerSegment);

        //Setting the force per segment in this analysis.
        List<Double> forcePerSegment = new ArrayList<>();
        for(int i = 0; i < path.size(); i++){
            forcePerSegment.set(i, 0.0);
        }
        analysis.setForcePerSegment(forcePerSegment);
        
        //Setting the load used in this analysis.
        analysis.setLoad(load);

        //Setting the travel time of the analysis.
        analysis.setTravellTime(values[0]);
        
        //Setting the energy consumption of the analysis.
        analysis.setEnergyConsumption(totalEnergy);
        
        //Setting the average velocity of the analysis.
        analysis.setAverageVelocity(Physics.getAverage(this.velocityPerSegment));

        //Setting the travelled distance.
        double travelledDistance = getTravelledDistance(path);
        analysis.setDistance(travelledDistance);

        //Setting the toll cost of the travel.
        double tollCost = getTollCost(path, vehicle.getTollClass(), project);
        analysis.setTollCost(tollCost);

        //Setting the fuel mass and volume of the analysis.
        if ("diesel".equals(vehicle.getFuel())) {
            analysis.setFuelMass(totalEnergy / DIESEL_FUEL_MASS);
            analysis.setFuelVolume(analysis.getFuelMass() / DIESEL_FUEL_DENSITY);
        }
        if ("gasoline".equals(vehicle.getFuel())) {
            analysis.setFuelMass(totalEnergy / GASOLINE_FUEL_MASS);
            analysis.setFuelVolume(analysis.getFuelMass() / GASOLINE_FUEL_DENSITY);
        }

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
     *
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
     *
     * @return a double that represent the energy costs.
     */
    private double calculateSectionEnergy(RoadSection rs, Vehicle vehicle) {
        List<Segment> segmentList = rs.getSegmentList();
        double sectionEnergy = 0;
        double lastVelocity;
        boolean flag = false;

        if (segmentList.isEmpty()) {
            return 0;
        }

        Segment first = segmentList.get(NODE_POSITION);
        lastVelocity = discoverVelocity(vehicle, first, this.projectAnalyzed.getNetwork().getRoad(first.getId()).getTypology());
        if (segmentList.size() == 1) {
            sectionEnergy += calculateSegmentEnergy(first, 0, lastVelocity, vehicle, true, null);
            flag = true;
        } else {
            sectionEnergy += calculateSegmentEnergy(first, 0, lastVelocity, vehicle, false, null);
        }

        for (int i = 1; i < segmentList.size() - 1; i++) {
            Segment actual = segmentList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(actual.getId()).getTypology());
            sectionEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, vehicle, false, null);
        }

        if (flag) {
            return sectionEnergy;
        }

        Segment last = segmentList.get(segmentList.size() - 1);
        double temp = lastVelocity;
        lastVelocity = discoverVelocity(vehicle, last, this.projectAnalyzed.getNetwork().getRoad(last.getId()).getTypology());
        sectionEnergy += calculateSegmentEnergy(last, temp, lastVelocity, vehicle, true, null);

        return sectionEnergy;
    }

    /**
     * Method that receives the segment, the vehicle and the type of the road
     * and indicates what velocity should be used to go through the segment.
     *
     * @param vehicle - vehicle used
     * @param segment - segment to go through
     * @param type - type of the road of thi segment
     *
     * @return - a double indicating the velocity.
     */
    private static double discoverVelocity(Vehicle vehicle, Segment segment, String type) {
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
    private double calculateSegmentEnergy(Segment segment, double lastVelocity, double newVelocity, Vehicle vehicle, boolean last, double[] time) {
        double energy = 0;
        double kinematicFunctions = 0;
        double temp = 0;
        // In case this is the last segment the energy taken to stop the vehicle should be considered.
        if (last) {

            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(newVelocity, 0, this.brakingAcceleration), this.brakingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            double[] values = new double[10];

            //The regenaration of energy when braking in eletric vehicles should be considered. And the algorithm to discover braking gears for combustion an eletric vehicles is different and it is taken is consideration.
            if (vehicle.getMotorization().equalsIgnoreCase("electric")) {
                Gear gear = discoverGear(values, this.brakingAcceleration, vehicle, vr, angle);
                temp = kinematicFunctions += Physics.kinematicFunctions(newVelocity, 0, this.brakingAcceleration);
                energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * kinematicFunctions;
            } else {
                Gear gear = discoverBrakingGearForCombustion(values, this.brakingAcceleration, vehicle, vr, angle);
                temp = kinematicFunctions += Physics.kinematicFunctions(newVelocity, 0, this.brakingAcceleration);
                energy -= Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * kinematicFunctions;
            }

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, this.brakingAcceleration);
            }
        }

        if (lastVelocity > newVelocity) { // In this case the car should decrease the velocity so the calculations should be made accordingly.

            //decreasing velocity
            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, this.brakingAcceleration), this.brakingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            double[] values = new double[10];

            //The regenaration of energy when braking in eletric vehicles should be considered. And the algorithm to discover braking gears for combustion an eletric vehicles are different and it is taken is consideration.
            if (vehicle.getMotorization().equalsIgnoreCase("electric")) {
                Gear gear = discoverGear(values, this.brakingAcceleration, vehicle, vr, angle);
                kinematicFunctions += Physics.kinematicFunctions(lastVelocity, newVelocity, this.brakingAcceleration);
                energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * (kinematicFunctions - temp);
            } else {
                Gear gear = discoverBrakingGearForCombustion(values, this.brakingAcceleration, vehicle, vr, angle);
                kinematicFunctions += Physics.kinematicFunctions(lastVelocity, newVelocity, this.brakingAcceleration);
                energy -= Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * (kinematicFunctions - temp);
            }

            //constant velocity
            double[] values1 = new double[10];
            double vr1 = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            discoverGear(values1, 0, vehicle, vr1, angle);
            double power = Physics.getEnginePower(values1[0], values1[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - kinematicFunctions);

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, this.brakingAcceleration);
            }

            return energy;

        } else if (lastVelocity < newVelocity) { // In this case the car should increase the velocity so the calculations should be made accordingly.

            //increasing velocity
            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, this.aceleratingAcceleration), this.aceleratingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            double[] values = new double[10];
            Gear gear = discoverGear(values, this.aceleratingAcceleration, vehicle, vr, angle);
            kinematicFunctions += Physics.kinematicFunctions(lastVelocity, newVelocity, this.aceleratingAcceleration);
            energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * (kinematicFunctions - temp);

            //constant velocity
            double[] values1 = new double[10];
            double vr1 = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            discoverGear(values1, 0, vehicle, vr1, angle);
            double power = Physics.getEnginePower(values1[0], values1[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - kinematicFunctions);

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, this.aceleratingAcceleration);
            }

            return energy;

        } else if (Math.abs(lastVelocity - newVelocity) < 0.01) { // In this case the car shouldn't alter the velocity so the calculations should be made accordingly.

            //constant velocity
            double[] values = new double[1];
            double vr = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double angle = Physics.getAngle(Double.parseDouble(segment.getLength().replace(" Km", "")), segment.getInitHeight(), segment.getFinalHeight());
            discoverGear(values, 0, vehicle, vr, angle);
            double power = Physics.getEnginePower(values[0], values[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", "")) - kinematicFunctions));

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, 0);
            }

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
    private static List<Double> getVelocities(double lastVelocity, double time, double acceleration) {
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
     *
     * @return - the gear that should be used.
     */
    private Gear discoverGear(double[] values, double acceleration, Vehicle v, double vr, double angle) {
        List<String> percentages = new ArrayList<>();
        percentages.add("25");
        percentages.add("50");
        percentages.add("100");

        List<Gear> gears = v.getEnergy().getGearList();
        for (int i = gears.size() - 1; i >= 0; i--) {
            Gear g = gears.get(i);
            for (int j = 0; j < percentages.size(); j++) {
                Throttle t = v.getEnergy().getThrottle(percentages.get(j));
                Regime r = getLowestSFCRegime(t);
                if (validateValues(g, r, acceleration, values, v, vr, angle)) {
                    return g;
                }
            }
        }

        Regime r = getLowestSFCRegime(v.getEnergy().getThrottle(percentages.get(0)));
        values[0] = r.getTorqueLow();
        values[1] = r.getRpmLow();
        return gears.get(0);
    }

    /**
     * Method used to discover the correct gear to be used.
     *
     * @param values - array to keep some necessary values for other methods.
     * @param acceleration - acceleration to keep track
     * @param v - vehicle that is being used to travel.
     *
     * @return - the gear that should be used.
     */
    private Gear discoverBrakingGearForCombustion(double[] values, double acceleration, Vehicle v, double vr, double angle) {

        List<Gear> gears = v.getEnergy().getGearList();
        for (int i = gears.size() - 1; i >= 0; i--) {
            Gear g = gears.get(i);
            Throttle t = v.getEnergy().getThrottle("25");
            Regime r = getLowestSFCRegime(t);
            if (validateValues(g, r, acceleration, values, v, vr, angle)) {
                return g;
            }
        }

        Regime r = getLowestSFCRegime(v.getEnergy().getThrottle("25"));
        values[0] = r.getTorqueLow();
        values[1] = r.getRpmLow();
        return gears.get(0);
    }

    /**
     * Method that validates the forces using some values of torque and rpm.
     *
     * @return
     */
    private boolean validateValues(Gear g, Regime r, double acceleration, double[] values, Vehicle v, double vr, double angle) {
        if (acceleration <= 0.000000001 && acceleration >= -0.000000001) {
            if (Physics.getForceAppliedToVehicle(r.getTorqueLow(), v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) <= 0.01) {
                values[0] = r.getTorqueLow();
                values[1] = r.getRpmLow();
                return true;
            } else if (Physics.getForceAppliedToVehicle(r.getTorqueHigh(), v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) <= 0.01) {
                values[0] = r.getTorqueLow();
                values[1] = r.getRpmLow();
                return true;
            }
        } else if (acceleration > 0.000000001) {
            if (Physics.getForceAppliedToVehicle(r.getTorqueLow(), v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) > this.totalMass * acceleration) {
                values[0] = r.getTorqueLow();
                values[1] = r.getRpmLow();
                return true;
            } else if (Physics.getForceAppliedToVehicle(r.getTorqueHigh(), v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) > this.totalMass * acceleration) {
                values[0] = r.getTorqueLow();
                values[1] = r.getRpmLow();
                return true;
            }
        } else if (acceleration <= -0.000000001) {
            if (Physics.getForceAppliedToVehicle(r.getTorqueLow(), v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) < this.totalMass * acceleration) {
                values[0] = r.getTorqueLow();
                values[1] = r.getRpmLow();
                return true;
            } else if (Physics.getForceAppliedToVehicle(r.getTorqueHigh(), v.getEnergy().getFinalDriveRatio(), g.getRatio(), v.getWheelSize(), v.getRrc(), this.totalMass, v.getDrag(), v.getFrontalArea(), vr, angle) < this.totalMass * acceleration) {
                values[0] = r.getTorqueLow();
                values[1] = r.getRpmLow();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Regime with the Lowest SFC int the same throttle
     *
     * @param t - thottle where is supposed to find the regime with the lowest
     * SFC
     *
     * @return - regime with the lowest SFC, or null if doesn't exists any.
     */
    private static Regime getLowestSFCRegime(Throttle t) {
        double sfc = Double.MAX_VALUE;
        Regime reg = t.getRegimeList().get(0);
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
    private List<RoadSection> recreatePath(List<Node> shortestPath) {
        List<RoadSection> path = new LinkedList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            RoadSection r = this.projectAnalyzed.getNetwork().getRoadMap().getEdge(shortestPath.get(i), shortestPath.get(i + 1));
            path.add(r);
        }
        return path;

    }

    /**
     * Method that calculates the cost of the travel.
     *
     * @param bestPath - path that the vehicle will travel.
     * @param tollClass - the vehicle toll class.
     * @param project - project that is being analyzed.
     *
     * @return The total toll cost of the travel.
     */
    private double getTollCost(List<RoadSection> bestPath, int tollClass, Project project) {
        double tollCost = 0;
        for (RoadSection road : bestPath) {
            if (!project.getNetwork().getRoad(road.getRoadId()).getTollFare().getListClasses().isEmpty()) {
                tollCost = tollCost + project.getNetwork().getRoad(road.getRoadId()).getTollFare().getListClasses().get(tollClass).getPrice();
            }
        }
        return tollCost;
    }

    /**
     * Method that calculates the travelled distance ins the best path
     *
     * @param path - path to calculate tha travelled distance
     * @return - a double with the total distance traveled
     */
    private double getTravelledDistance(List<RoadSection> path) {
        double distance = 0;
        for (RoadSection rs : path) {
            for (Segment s : rs.getSegmentList()) {
                distance += Physics.convertKmToMeter(Double.parseDouble(s.getLength().replace(" Km", "")));
            }
        }
        return distance;
    }

    /**
     * Method that calculates the total cost in terms of energy
     *
     * @param path
     * @param values
     * @return
     */
    private double getNecessaryData(List<RoadSection> path, double[] values) {
        double totalEnergy = 0;
        values[0] = 0;
        double lastVelocity;

        if (path.isEmpty()) {
            return 0;
        }

        //Considers only the first road section of the path
        RoadSection first = path.get(NODE_POSITION);
        List<Segment> firstSegmentList = first.getSegmentList();

        if (firstSegmentList.isEmpty()) {
            return 0;
        }

        Segment firstSegment = firstSegmentList.get(NODE_POSITION);
        lastVelocity = discoverVelocity(this.vehicle, firstSegment, this.projectAnalyzed.getNetwork().getRoad(firstSegment.getId()).getTypology());

        if (path.size() == 1 && firstSegmentList.size() == 1) {
            totalEnergy += calculateSegmentEnergy(firstSegment, 0, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        totalEnergy += calculateSegmentEnergy(firstSegment, 0, lastVelocity, this.vehicle, false, values);
        for (int i = 1; i < firstSegmentList.size() - 1; i++) {
            Segment actual = firstSegmentList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(actual.getId()).getTypology());
            totalEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, this.vehicle, false, values);
        }

        Segment lastForSection = firstSegmentList.get(firstSegmentList.size() - 1);
        double temp1 = lastVelocity;
        lastVelocity = discoverVelocity(this.vehicle, lastForSection, this.projectAnalyzed.getNetwork().getRoad(lastForSection.getId()).getTypology());

        if (path.size() == 1 && firstSegmentList.size() > 1) {
            totalEnergy += calculateSegmentEnergy(lastForSection, temp1, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        totalEnergy += calculateSegmentEnergy(lastForSection, temp1, lastVelocity, this.vehicle, false, values);

        if (path.size() == 2 && path.get(1).getSegmentList().size() == 1) {
            Segment s = path.get(1).getSegmentList().get(NODE_POSITION);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, lastForSection, this.projectAnalyzed.getNetwork().getRoad(lastForSection.getId()).getTypology());
            totalEnergy += calculateSegmentEnergy(s, temp, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        if (path.size() == 2) {
            RoadSection actual = path.get(1);
            List<Segment> thisList = actual.getSegmentList();
            for (int j = 0; j < thisList.size() - 1; j++) {
                Segment s = thisList.get(j);
                double temp = lastVelocity;
                lastVelocity = discoverVelocity(this.vehicle, s, this.projectAnalyzed.getNetwork().getRoad(s.getId()).getTypology());
                totalEnergy += calculateSegmentEnergy(s, temp, lastVelocity, this.vehicle, false, values);
            }
            Segment last = thisList.get(thisList.size() - 1);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, last, this.projectAnalyzed.getNetwork().getRoad(last.getId()).getTypology());
            totalEnergy += calculateSegmentEnergy(last, temp, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        //Considers the remaining road sections except the last one.
        for (int i = 1; i < path.size() - 1; i++) {
            RoadSection actual = path.get(i);
            for (Segment s : actual.getSegmentList()) {
                double temp = lastVelocity;
                lastVelocity = discoverVelocity(this.vehicle, s, this.projectAnalyzed.getNetwork().getRoad(s.getId()).getTypology());
                totalEnergy += calculateSegmentEnergy(s, temp, lastVelocity, this.vehicle, false, values);
            }
        }

        RoadSection last = path.get(path.size() - 1);
        List<Segment> lastList = last.getSegmentList();

        if (lastList.size() == 1) {
            Segment lastSegmentCondition = lastList.get(0);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, lastSegmentCondition, this.projectAnalyzed.getNetwork().getRoad(lastSegmentCondition.getId()).getTypology());
            totalEnergy += calculateSegmentEnergy(lastSegmentCondition, temp, lastVelocity, this.vehicle, true, values);
        }

        for (int i = 0; i < lastList.size() - 1; i++) {
            Segment actual = lastList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(actual.getId()).getTypology());
            totalEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, this.vehicle, false, values);
        }

        Segment lastSegment = lastList.get(lastList.size() - 1);
        double temp = lastVelocity;
        lastVelocity = discoverVelocity(this.vehicle, lastSegment, this.projectAnalyzed.getNetwork().getRoad(lastSegment.getId()).getTypology());
        totalEnergy += calculateSegmentEnergy(lastSegment, temp, lastVelocity, this.vehicle, false, values);
        return totalEnergy;
    }

    
    private double calculateSegmentTime(Segment segment, double lastVelocity, double newVelocity, double acceleration){
        if(acceleration != 0){
            return Physics.getTime(newVelocity, Double.parseDouble(segment.getLength().replace(" Km", "")));
        }
        
        double kinematicFunctions = Physics.kinematicFunctions(lastVelocity, newVelocity, acceleration);
        double kinematicFunctionsGetTimeByVelocities = Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, acceleration);
        
        return Physics.getTime(newVelocity, Double.parseDouble(segment.getLength().replace(" Km", "")) - kinematicFunctions) + kinematicFunctionsGetTimeByVelocities;
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
