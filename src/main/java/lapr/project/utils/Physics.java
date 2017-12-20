/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 *
 * @author Hugo
 */
public class Physics {

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
}
