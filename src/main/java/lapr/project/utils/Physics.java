/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.List;

/**
 *
 * @author Hugo
 */
public class Physics {

    private Physics() {
        // Private Constructor of physics
    }

    /**
     *
     * @param velocity the velocity in kilometers per hour
     * @return the velocity in meters per second
     */
    public static double convertKmPerHourToMeterPerSec(double velocity) {
        return ((velocity * 1000) / 3600);
    }

    /**
     *
     * @param distance the distance in kilometers
     * @return the distance in meters
     */
    public static double convertKmToMeter(double distance) {
        return distance * 1000;
    }

    /**
     *
     * @param velocity the velocity
     * @param distance the distance
     * @return
     */
    public static double getTime(double velocity, double distance) {
        return distance / velocity;
    }

    /**
     *
     * @param time the time in seconds
     * @param distance the distance in meters
     * @return
     */
    public static double getVelocity(double time, double distance) {
        return distance / time;
    }

    /**
     *
     * @param torque the vehicles torque
     * @param rpm the rpm of the vehicle
     * @return the engine power in watts
     */
    public static double getEnginePower(double torque, double rpm) {
        return Math.PI * 2 * torque * (rpm / 60.0);
    }

    /**
     *
     * @param vehicleVelocity velocity of the vehicle
     * @param windVelocity velocity of the wind
     * @param angle angle of the wind relative to the front of the car
     * @return the engine power in watts
     */
    public static double getVehicleRelativeVelocity(double vehicleVelocity, double windVelocity, double angle) {
        return vehicleVelocity - (windVelocity * Math.cos(Math.toRadians(angle)));
    }

    /**
     *
     * @param torque vehicle torque(Nm)
     * @param fDrive final drive ratio of the vehicle
     * @param gearRatio gear ratio of the vehicle on the x gear
     * @param r radius of the tire(m)
     * @param rrc rolling resistance coefficient
     * @param m mass of the vehicle(kg)
     * @param drag drag (air resistance) coefficient
     * @param area frontal area of the car(m2)
     * @param vr velocity of the car relative to the air(m/s)
     * @return the force applied to the car in Newton
     */
    public static double getForceAppliedToVehicleOnFlatSurface(double torque, double fDrive, double gearRatio, double r, double rrc, double m, double drag, double area, double vr) {
        return (torque * fDrive * gearRatio) / r
                - (rrc * m * 9.8)
                - (0.5 * drag * area * 1.225 * Math.pow(vr, 2));
    }

    /**
     *
     * @param list the list of doubles to calculate the average
     * @return the average value of the elements inside the list
     */
    public static double getAverage(List<Double> list) {
        double res = 0;
        if (list.isEmpty()) {
            return res;
        }
        for (Double value : list) {
            res = res + value;
        }
        return res / list.size();
    }

    /**
     *
     * @param m the objects mass
     * @param v the objects velocity
     * @return the energy used as kinetic energy in joules
     */
    public static double getKineticEnergy(double m, double v) {
        return 0.5 * m * Math.pow(v, 2);
    }
}
