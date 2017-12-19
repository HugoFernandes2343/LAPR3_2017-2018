package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Energy implements Serializable {

    private static final long serialVersionUID = 503L;

    private int min_rpm;
    private int max_rpm;
    private Double final_drive_ratio;
    private List<Gear> gear_List = new LinkedList<>();
    private List<Throttle> throttle_list = new LinkedList<>();

    /**
     * Full constructor of the energy objects
     * @param minRpm minimum rpm value 
     * @param maxRpm maximum rpm value 
     * @param finalDriveRatio final drive ratio value
     * @param gearList list of gears present on the vehicle the engery object belongs to
     * @param throttleList list of throttle caracteristics
     */
    public Energy(int minRpm, int maxRpm, Double finalDriveRatio, List<Gear> gearList, List<Throttle> throttleList) {
        this.min_rpm = minRpm;
        this.max_rpm = maxRpm;
        this.final_drive_ratio = finalDriveRatio;
        this.gear_List = gearList;
        this.throttle_list = throttleList;
    }

    /**
     * Empty constructor
     */
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
    public List<Gear> getGearList() {
        return gear_List;
    }

    /**
     * @return the throttle_list
     */
    public List<Throttle> getThrottleList() {
        return throttle_list;
    }

    /**
     * @param minRpm the min_rpm to set
     */
    public void setMinRpm(int minRpm) {
        this.min_rpm = minRpm;
    }

    /**
     * @param maxRpm the max_rpm to set
     */
    public void setMaxRpm(int maxRpm) {
        this.max_rpm = maxRpm;
    }

    /**
     * @param finalDriveRatio the final_drive_ratio to set
     */
    public void setFinalDriveRatio(Double finalDriveRatio) {
        this.final_drive_ratio = finalDriveRatio;
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
        if (obj == null) {
            return false;
        }
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

