package lapr.project.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Gear;
import lapr.project.model.MostEfficientPathInEnergySavingModeAnalysis;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Regime;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.Throttle;
import lapr.project.model.Vehicle;

public class MostEfficientPathInEnergySavingModeAlgorithm implements Algorithm {

    /**
     * Constant that keeps the name of this algorithm.
     */
    private static final String NAME = "Most Efficient Path In Energy Saving Mode N(12)";

    /**
     * Constant that keeps the position of the first node.
     */
    private static final int NODE_POSITION = 0;

    /**
     * Constant that keeps the position of the second node.
     */
    private static final int NODE_SECOND_POSITION = 1;

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
     * Throttle of this algorithm
     */
    private static final String THROTTLE = "25";

    /**
     * Method that runs the algorithm
     *
     * @param project
     * @param begin
     * @param end
     * @param vehicle
     * @param name
     * @param load
     * @return
     */
    @Override
    public NetworkAnalysis runAlgorithm(Project project, Node begin, Node end, Vehicle vehicle, String name, double load) {
        this.projectAnalyzed = project;
        this.totalMass = Double.parseDouble(vehicle.getMass().replace(" Kg", "")) + load;
        AdjacencyMatrixGraph<Node, Double> edgeAsDouble = edgeAsDouble(project.getNetwork().getRoadMap(), vehicle);
        this.vehicle = vehicle;
        MostEfficientPathInEnergySavingModeAnalysis analysis = new MostEfficientPathInEnergySavingModeAnalysis(begin, end, vehicle, name);

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
        List<Double> velocityPerSegment = getVelocityPerSegment(path, this.vehicle);
        analysis.setVelocityPerSegment(velocityPerSegment);

        //Setting the force per segment in this analysis.
        List<Double> forcePerSegment = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            forcePerSegment.add(0.0);
        }
        analysis.setForcePerSegment(forcePerSegment);

        //Setting the load used in this analysis.
        analysis.setLoad(load);

        //Setting the travel time of the analysis.
        analysis.setTravellTime(values[0]);

        //Setting the energy consumption of the analysis.
        analysis.setEnergyConsumption(totalEnergy);

        //Setting the average velocity of the analysis.
        analysis.setAverageVelocity(Physics.getAverage(velocityPerSegment));

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
     * Select the correct gear according with the algorithm specifications
     *
     * @param vehicle
     * @param vr
     * @param angle
     * @return
     */
    private Gear selectGear(double[] values, double acceleration, Vehicle vehicle, double vr, double angle) {
        Gear gear = biggestGear(vehicle);

        Regime regime = getLowestSFCRegime(vehicle, THROTTLE);
        if (!"eletric".equals(vehicle.getMotorization())) {
            for (int i = 0; i < vehicle.getEnergy().getGearList().size(); i++) {

                if (validateValues(gear, regime, acceleration, values, vehicle, vr, angle)) {
                    return gear;
                } else {
                    gear = downGear(vehicle, gear);
                }

            }
        }

        if (gear.equals(vehicle.getEnergy().getGearList().get(0))) {
            if (Physics.getForceAppliedToVehicle(regime.getTorqueLow(), vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) > Physics.getForceAppliedToVehicle(regime.getTorqueHigh(), vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle)) {
                values[0] = regime.getTorqueLow();
                values[1] = regime.getRpmLow();
            } else {
                values[0] = regime.getTorqueHigh();
                values[1] = regime.getRpmHigh();
            }
        }

        if (vehicle.getMotorization().equals("eletric")) {
            for (Regime r : vehicle.getEnergy().getThrottle(THROTTLE).getRegimeList()) {
                if (validateValues(gear, r, acceleration, values, vehicle, vr, angle)) {
                    return gear;
                }
            }
        }

        return gear;
    }

    /**
     * Method that returns the biggestGear of the Vehicle
     *
     * @param vehicle
     * @return the biggestGear of the Vehicle
     */
    private Gear biggestGear(Vehicle vehicle) {
        int g = 0;
        Gear gear = null;
        for (Gear gr : vehicle.getEnergy().getGearList()) {
            int value = Integer.valueOf(gr.getId());
            if (value > g) {
                g = value;
                gear = gr;
            }
        }
        return gear;
    }

    /**
     * Method that adjusts a vehicle gear (down)
     *
     * @param vehicle
     * @param gear
     * @return the gear below the current one (or the same in case that is
     * already the lowest)
     */
    private Gear downGear(Vehicle vehicle, Gear gear) {
        int gBig = Integer.valueOf(gear.getId());
        Gear l = vehicle.getEnergy().getGearList().get(0);
        int g = Integer.valueOf(l.getId());
        for (Gear gr : vehicle.getEnergy().getGearList()) {
            int value = Integer.valueOf(gr.getId());
            if (value > g && value < gBig) {
                g = value;
                l = gr;
            }
        }
        return l;
    }

    /**
     * Returns the Regime with the Lowest SFC int the same throttle
     *
     * @param vehicle - vehicle
     * @param throttle - string with the throttle id
     * @return
     */
    private Regime getLowestSFCRegime(Vehicle vehicle, String throttle) {
        double sfc = Double.MAX_VALUE;
        Regime reg = null;
        for (Regime r : vehicle.getEnergy().getThrottle(throttle).getRegimeList()) {
            if (r.getSfc() < sfc) {
                sfc = r.getSfc();
                reg = r;
            }
        }

        return reg;
    }

    /**
     * Method that calculates the velocity used to go through each segment
     *
     * @param path - path that is being used to reach de end.
     * @param vehicle - vehicle used to travel
     *
     * @return - ArrayList with the velocity of each segment.
     */
    private List<Double> getVelocityPerSegment(List<RoadSection> path, Vehicle vehicle) {
        List<Double> velocities = new ArrayList<>();
        for (RoadSection rs : path) {
            for (Segment s : rs.getSegmentList()) {
                velocities.add(discoverVelocity(vehicle, s, this.projectAnalyzed.getNetwork().getRoad(rs.getRoadId()).getTypology()));
            }
        }
        return velocities;
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
        lastVelocity = discoverVelocity(vehicle, first, this.projectAnalyzed.getNetwork().getRoad(rs.getRoadId()).getTypology());
        if (segmentList.size() == 1) {
            sectionEnergy += calculateSegmentEnergy(first, 0, lastVelocity, vehicle, true, null);
            flag = true;
        } else {
            sectionEnergy += calculateSegmentEnergy(first, 0, lastVelocity, vehicle, false, null);
        }

        for (int i = 1; i < segmentList.size() - 1; i++) {
            Segment actual = segmentList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(rs.getRoadId()).getTypology());
            sectionEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, vehicle, false, null);
        }

        if (flag) {
            return sectionEnergy;
        }

        Segment last = segmentList.get(segmentList.size() - 1);
        double temp = lastVelocity;
        lastVelocity = discoverVelocity(vehicle, last, this.projectAnalyzed.getNetwork().getRoad(rs.getRoadId()).getTypology());
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
        double segmentVelocity = Physics.convertKmPerHourToMeterPerSec(Double.parseDouble(segment.getMaxVelocity().replace(" Km/h", "")));

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
        // In case this is the last segment the energy taken to stop the vehicle should be considered.
        if (last) {

            List<Double> velocities = getVelocities(newVelocity, Physics.kinematicFunctionsGetTimeByVelocities(newVelocity, 0, this.brakingAcceleration), this.brakingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double lengthInMeters = Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", "")));
            double angle = Physics.getAngle(lengthInMeters, segment.getInitHeight(), segment.getFinalHeight());
            double[] values = new double[10];

            //The regenaration of energy when braking in eletric vehicles should be considered. And the algorithm to discover braking gears for combustion an eletric vehicles is different and it is taken is consideration.
            if ("electric".equalsIgnoreCase(vehicle.getMotorization())) {
                Gear gear = selectGear(values, this.brakingAcceleration, vehicle, vr, angle);
                kinematicFunctions += Physics.kinematicFunctions(newVelocity, 0, this.brakingAcceleration);
                energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * kinematicFunctions;
            } else {
                Gear gear = discoverBrakingGearForCombustion(values, this.brakingAcceleration, vehicle, vr, angle);
                kinematicFunctions += Physics.kinematicFunctions(newVelocity, 0, this.brakingAcceleration);
                energy -= Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * kinematicFunctions;
            }

            if (time != null) {
                time[0] += calculateSegmentTime(segment, newVelocity, 0, this.brakingAcceleration, 0);
            }
        }

        if (lastVelocity > newVelocity) { // In this case the car should decrease the velocity so the calculations should be made accordingly.

            //decreasing velocity
            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, this.brakingAcceleration), this.brakingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double lengthInMeters = Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", "")));
            double angle = Physics.getAngle(lengthInMeters, segment.getInitHeight(), segment.getFinalHeight());
            double[] values = new double[10];

            //The regenaration of energy when braking in eletric vehicles should be considered. And the algorithm to discover braking gears for combustion an eletric vehicles are different and it is taken is consideration.
            if ("electric".equalsIgnoreCase(vehicle.getMotorization())) {
                Gear gear = selectGear(values, this.brakingAcceleration, vehicle, vr, angle);
                kinematicFunctions += Physics.kinematicFunctions(lastVelocity, newVelocity, this.brakingAcceleration);
                energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * (kinematicFunctions);
            } else {
                Gear gear = discoverBrakingGearForCombustion(values, this.brakingAcceleration, vehicle, vr, angle);
                kinematicFunctions += Physics.kinematicFunctions(lastVelocity, newVelocity, this.brakingAcceleration);
                energy -= Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * (kinematicFunctions);
            }

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, this.brakingAcceleration, 0);
            }

            //constant velocity
            double[] values1 = new double[10];
            double vr1 = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            selectGear(values1, 0, vehicle, vr1, angle);
            double power = Physics.getEnginePower(values1[0], values1[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - kinematicFunctions);

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, 0, kinematicFunctions);
            }

            return energy;

        } else if (lastVelocity < newVelocity) { // In this case the car should increase the velocity so the calculations should be made accordingly.

            //increasing velocity
            List<Double> velocities = getVelocities(lastVelocity, Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, this.aceleratingAcceleration), this.aceleratingAcceleration);
            double velocity = Physics.getAverage(velocities);
            double vr = Physics.getVehicleRelativeVelocity(velocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double lengthInMeters = Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", "")));
            double angle = Physics.getAngle(lengthInMeters, segment.getInitHeight(), segment.getFinalHeight());
            double[] values = new double[10];
            Gear gear = selectGear(values, this.aceleratingAcceleration, vehicle, vr, angle);
            kinematicFunctions += Physics.kinematicFunctions(lastVelocity, newVelocity, this.aceleratingAcceleration);
            energy += Physics.getForceAppliedToVehicle(values[0], vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), this.totalMass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) * (kinematicFunctions);

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, this.aceleratingAcceleration, 0);
            }

            //constant velocity
            double[] values1 = new double[10];
            double vr1 = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            selectGear(values1, 0, vehicle, vr1, angle);
            double power = Physics.getEnginePower(values1[0], values1[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - kinematicFunctions);

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, 0, kinematicFunctions);
            }

            return energy;

        } else if (Math.abs(lastVelocity - newVelocity) < 0.01) { // In this case the car shouldn't alter the velocity so the calculations should be made accordingly.

            //constant velocity
            double[] values = new double[10];
            double vr = Physics.getVehicleRelativeVelocity(newVelocity, Double.parseDouble(segment.getWindSpeed().replace(" m/s", "")), segment.getWindDirection());
            double lengthInMeters = Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", "")));
            double angle = Physics.getAngle(lengthInMeters, segment.getInitHeight(), segment.getFinalHeight());
            selectGear(values, 0, vehicle, vr, angle);
            double power = Physics.getEnginePower(values[0], values[1]);
            energy += power * Physics.getTime(newVelocity, Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", "")) - kinematicFunctions));

            if (time != null) {
                time[0] += calculateSegmentTime(segment, lastVelocity, newVelocity, 0, kinematicFunctions);
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
            velocities.add(Physics.kinematicFunctionsGetVelocity(lastVelocity, acceleration, i));
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
     * Method that receives a list with all the nodes in the path and recreates
     * it using all the road sections necessary to pass.
     *
     * @param shortestPath - List with the shortest path in nodes
     * @return - List with the same path but in road sections.
     */
    private List<RoadSection> recreatePath(List<Node> shortestPath) {
        List<RoadSection> path = new LinkedList<>();

        for (int i = 0; i < shortestPath.size() - 1; i++) {
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
     * @param path - the oath taken
     * @param values - array to keep some values needed in the caller
     *
     * @return - double representing the total cost in energy.
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
        lastVelocity = discoverVelocity(this.vehicle, firstSegment, this.projectAnalyzed.getNetwork().getRoad(first.getRoadId()).getTypology());

        if (path.size() == 1 && firstSegmentList.size() == 1) {
            totalEnergy += calculateSegmentEnergy(firstSegment, 0, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        totalEnergy += calculateSegmentEnergy(firstSegment, 0, lastVelocity, this.vehicle, false, values);
        for (int i = 1; i < firstSegmentList.size() - 1; i++) {
            Segment actual = firstSegmentList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(first.getRoadId()).getTypology());
            totalEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, this.vehicle, false, values);
        }

        Segment lastForSection = firstSegmentList.get(firstSegmentList.size() - 1);
        double temp1 = lastVelocity;
        lastVelocity = discoverVelocity(this.vehicle, lastForSection, this.projectAnalyzed.getNetwork().getRoad(first.getRoadId()).getTypology());

        if (path.size() == 1 && firstSegmentList.size() > 1) {
            totalEnergy += calculateSegmentEnergy(lastForSection, temp1, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        totalEnergy += calculateSegmentEnergy(lastForSection, temp1, lastVelocity, this.vehicle, false, values);

        if (path.size() == 2 && path.get(1).getSegmentList().size() == 1) {
            RoadSection rs = path.get(1);
            Segment s = rs.getSegmentList().get(NODE_POSITION);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, lastForSection, this.projectAnalyzed.getNetwork().getRoad(rs.getRoadId()).getTypology());
            totalEnergy += calculateSegmentEnergy(s, temp, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        if (path.size() == 2) {
            RoadSection actual = path.get(1);
            List<Segment> thisList = actual.getSegmentList();
            for (int j = 0; j < thisList.size() - 1; j++) {
                Segment s = thisList.get(j);
                double temp = lastVelocity;
                lastVelocity = discoverVelocity(this.vehicle, s, this.projectAnalyzed.getNetwork().getRoad(actual.getRoadId()).getTypology());
                totalEnergy += calculateSegmentEnergy(s, temp, lastVelocity, this.vehicle, false, values);
            }
            Segment last = thisList.get(thisList.size() - 1);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, last, this.projectAnalyzed.getNetwork().getRoad(actual.getRoadId()).getTypology());
            totalEnergy += calculateSegmentEnergy(last, temp, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        //Considers the remaining road sections except the last one.
        for (int i = 1; i < path.size() - 1; i++) {
            RoadSection actual = path.get(i);
            for (Segment s : actual.getSegmentList()) {
                double temp = lastVelocity;
                lastVelocity = discoverVelocity(this.vehicle, s, this.projectAnalyzed.getNetwork().getRoad(actual.getRoadId()).getTypology());
                totalEnergy += calculateSegmentEnergy(s, temp, lastVelocity, this.vehicle, false, values);
            }
        }

        RoadSection last = path.get(path.size() - 1);
        List<Segment> lastList = last.getSegmentList();

        if (lastList.size() == 1) {
            Segment lastSegmentCondition = lastList.get(0);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, lastSegmentCondition, this.projectAnalyzed.getNetwork().getRoad(last.getRoadId()).getTypology());
            totalEnergy += calculateSegmentEnergy(lastSegmentCondition, temp, lastVelocity, this.vehicle, true, values);
            return totalEnergy;
        }

        for (int i = 0; i < lastList.size() - 1; i++) {
            Segment actual = lastList.get(i);
            double temp = lastVelocity;
            lastVelocity = discoverVelocity(this.vehicle, actual, this.projectAnalyzed.getNetwork().getRoad(last.getRoadId()).getTypology());
            totalEnergy += calculateSegmentEnergy(actual, temp, lastVelocity, this.vehicle, false, values);
        }

        Segment lastSegment = lastList.get(lastList.size() - 1);
        double temp = lastVelocity;
        lastVelocity = discoverVelocity(this.vehicle, lastSegment, this.projectAnalyzed.getNetwork().getRoad(last.getRoadId()).getTypology());
        totalEnergy += calculateSegmentEnergy(lastSegment, temp, lastVelocity, this.vehicle, false, values);
        return totalEnergy;
    }

    /**
     * Method that calculates the time taken to go through a segment
     *
     * @param segment - segment to go through
     * @param lastVelocity - the beginning velocity.
     * @param newVelocity - the ending velocity.
     * @param acceleration - the acceleration acting on the vehicle.
     *
     * @return - a double representing that time taken.
     */
    private double calculateSegmentTime(Segment segment, double lastVelocity, double newVelocity, double acceleration, double length) {
        if (acceleration < 0.00000000001 & acceleration > -0.00000000001) {
            double lengthInMeters = Physics.convertKmToMeter(Double.parseDouble(segment.getLength().replace(" Km", ""))) - length;
            return Physics.getTime(newVelocity, lengthInMeters);
        }

        return Physics.kinematicFunctionsGetTimeByVelocities(lastVelocity, newVelocity, acceleration);
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
     *
     * @return accelaration value
     */
    public double getAceleratingAcceleration() {
        return aceleratingAcceleration;
    }

    /**
     *
     * @return braking accelaration value
     */
    public double getBrakingAcceleration() {
        return brakingAcceleration;
    }

}
