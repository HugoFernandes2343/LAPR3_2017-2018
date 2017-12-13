/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;

/**
 *
 * @author Hugo
 */
public class Energy {

    private int min_rpm;
    private int max_rpm;
    private int final_drive_ratio;
    private LinkedList<Gear> gear_List;
    private LinkedList<Throttle> throttle_list;

    /**
     *
     * @param min_rpm
     * @param max_rpm
     * @param final_drive_ratio
     * @param gear_List
     * @param throttle_list
     */
    public Energy(int min_rpm, int max_rpm, int final_drive_ratio, LinkedList<Gear> gear_List, LinkedList<Throttle> throttle_list) {
        this.min_rpm = min_rpm;
        this.max_rpm = max_rpm;
        this.final_drive_ratio = final_drive_ratio;
        this.gear_List = gear_List;
        this.throttle_list = throttle_list;
    }

    /**
     * @return the min_rpm
     */
    public int getMin_rpm() {
        return min_rpm;
    }

    /**
     * @return the max_rpm
     */
    public int getMax_rpm() {
        return max_rpm;
    }

    /**
     * @return the final_drive_ratio
     */
    public int getFinal_drive_ratio() {
        return final_drive_ratio;
    }

    /**
     * @return the gear_List
     */
    public LinkedList<Gear> getGear_List() {
        return gear_List;
    }

    /**
     * @return the throttle_list
     */
    public LinkedList<Throttle> getThrottle_list() {
        return throttle_list;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.min_rpm;
        hash = 47 * hash + this.max_rpm;
        hash = 47 * hash + this.final_drive_ratio;
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the energy
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Energy other = (Energy) obj;
        if (this.min_rpm != other.min_rpm) {
            return false;
        }
        if (this.max_rpm != other.max_rpm) {
            return false;
        }
        return this.final_drive_ratio != other.final_drive_ratio;

    }

}
