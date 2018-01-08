package lapr.project.utils;

import lapr.project.model.Gear;
import lapr.project.model.MostEfficientPathInEnergySavingModeAnalysis;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Regime;
import lapr.project.model.Vehicle;
import static lapr.project.utils.Physics.getForceAppliedToVehicle;

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

        MostEfficientPathInEnergySavingModeAnalysis analysis = new MostEfficientPathInEnergySavingModeAnalysis();
        return analysis;
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
     * Select the correct gear according with the algorithm specifications
     *
     * @param vehicle
     * @param vr
     * @param angle
     * @return
     */
    public Gear selectGear(Vehicle vehicle, double vr, double angle) {
        Gear gear = biggestGear(vehicle);
        if (gear == null) {
            return null;
        }

        Double mass = Double.valueOf(vehicle.getMass().replace(" Kg", ""));
        Regime regime = getLowestSFCRegime(vehicle);
        if (regime == null) {
            return null;
        }
        boolean flag = true;
        do{
        for (int tq = regime.getTorqueLow(); tq < regime.getTorqueHigh(); tq++) {
            if(getForceAppliedToVehicle(tq, vehicle.getEnergy().getFinalDriveRatio(), gear.getRatio(), vehicle.getWheelSize(), vehicle.getRrc(), mass, vehicle.getDrag(), vehicle.getFrontalArea(), vr, angle) <= 0) {
                flag=false;
                break;
            }
            
            if ("1".equals(gear.getId())) {
                flag =false;
                break;
                    
            }
            
        }
        if(flag)
            gear = downGear(vehicle, gear);
        
        }while(flag);

        return gear;
    }

    /**
     * Returns the Regime with the Lowest SFC int the same throttle
     *
     * @param vehicle
     * @return
     */
    private Regime getLowestSFCRegime(Vehicle vehicle) {
        double sfc = Double.MAX_VALUE;
        Regime reg = null;
        for (Regime r : vehicle.getEnergy().getThrottle(THROTTLE).getRegimeList()) {
            if (r.getSfc() < sfc) {
                sfc = r.getSfc();
                reg = r;
            }
        }

        return reg;
    }

}
