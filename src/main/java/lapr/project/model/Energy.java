package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Energy implements Serializable {

    private static final long serialVersionUID = 503L;

    private int min_rpm;
    private int max_rpm;
    private Double final_drive_ratio;
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
    public Energy(int min_rpm, int max_rpm, Double final_drive_ratio, LinkedList<Gear> gear_List, LinkedList<Throttle> throttle_list) {
        this.min_rpm = min_rpm;
        this.max_rpm = max_rpm;
        this.final_drive_ratio = final_drive_ratio;
        this.gear_List = gear_List;
        this.throttle_list = throttle_list;
    }

    public Energy() {
        this.min_rpm = 0;
        this.max_rpm = 1000;
        this.final_drive_ratio = 0.0;
        this.gear_List = new LinkedList<>();
        this.throttle_list = new LinkedList<>();
    }

    /**
     * @return the min_rpm
     */
    public int getMinRpm() {
        return min_rpm;
    }

    /**
     * @return the max_rpm
     */
    public int getMaxRpm() {
        return max_rpm;
    }

    /**
     * @return the final_drive_ratio
     */
    public Double getFinalDriveRatio() {
        return final_drive_ratio;
    }

    /**
     * @return the gear_List
     */
    public LinkedList<Gear> getGearList() {
        return gear_List;
    }

    /**
     * @return the throttle_list
     */
    public LinkedList<Throttle> getThrottleList() {
        return throttle_list;
    }

    /**
     * @param min_rpm the min_rpm to set
     */
    public void setMinRpm(int min_rpm) {
        this.min_rpm = min_rpm;
    }

    /**
     * @param max_rpm the max_rpm to set
     */
    public void setMaxRpm(int max_rpm) {
        this.max_rpm = max_rpm;
    }

    /**
     * @param final_drive_ratio the final_drive_ratio to set
     */
    public void setFinalDriveRatio(Double final_drive_ratio) {
        this.final_drive_ratio = final_drive_ratio;
    }

    /**
     * Adds a gear to the list
     *
     * @param gear - gear that needs to be added
     */
    public void addGear(Gear gear) {
        if (!this.gear_List.contains(gear)) {
            this.gear_List.add(gear);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.min_rpm;
        hash = 47 * hash + this.max_rpm;
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
        Energy other = (Energy) obj;
        if (this.min_rpm != other.min_rpm) {
            return false;
        }
        if (this.max_rpm != other.max_rpm) {
            return false;
        }
        return this.final_drive_ratio.equals(other.final_drive_ratio);

    }

}
