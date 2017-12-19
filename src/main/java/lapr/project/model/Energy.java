package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Energy implements Serializable {

    private static final long serialVersionUID = 503L;

    private int minRpm;
    private int maxRpm;
    private Double finalDriveRatio;
    private List<Gear> gearList = new LinkedList<>();
    private List<Throttle> throttleList = new LinkedList<>();

    /**
     * Full constructor of the energy objects
     * @param minRpm minimum rpm value 
     * @param maxRpm maximum rpm value 
     * @param finalDriveRatio final drive ratio value
     * @param gearList list of gears present on the vehicle the engery object belongs to
     * @param throttleList list of throttle caracteristics
     */
    public Energy(int minRpm, int maxRpm, Double finalDriveRatio, List<Gear> gearList, List<Throttle> throttleList) {
        this.minRpm = minRpm;
        this.maxRpm = maxRpm;
        this.finalDriveRatio = finalDriveRatio;
        this.gearList = gearList;
        this.throttleList = throttleList;
    }

    /**
     * Empty constructor
     */
    public Energy() {
        this.minRpm = 0;
        this.maxRpm = 1000;
        this.finalDriveRatio = 0.0;
        this.gearList = new LinkedList<>();
        this.throttleList = new LinkedList<>();
    }

    /**
     * @return the minRpm
     */
    public int getMinRpm() {
        return minRpm;
    }

    /**
     * @return the maxRpm
     */
    public int getMaxRpm() {
        return maxRpm;
    }

    /**
     * @return the finalDriveRatio
     */
    public Double getFinalDriveRatio() {
        return finalDriveRatio;
    }

    /**
     * @return the gearList
     */
    public List<Gear> getGearList() {
        return gearList;
    }

    /**
     * @return the throttleList
     */
    public List<Throttle> getThrottleList() {
        return throttleList;
    }

    /**
     * @param minRpm the minRpm to set
     */
    public void setMinRpm(int minRpm) {
        this.minRpm = minRpm;
    }

    /**
     * @param maxRpm the maxRpm to set
     */
    public void setMaxRpm(int maxRpm) {
        this.maxRpm = maxRpm;
    }

    /**
     * @param finalDriveRatio the finalDriveRatio to set
     */
    public void setFinalDriveRatio(Double finalDriveRatio) {
        this.finalDriveRatio = finalDriveRatio;
    }

    /**
     * Adds a gear to the list
     *
     * @param gear - gear that needs to be added
     */
    public void addGear(Gear gear) {
        if (!this.gearList.contains(gear)) {
            this.gearList.add(gear);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.minRpm;
        hash = 47 * hash + this.maxRpm;
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
        if (this.minRpm != other.minRpm) {
            return false;
        }
        if (this.maxRpm != other.maxRpm) {
            return false;
        }
        return this.finalDriveRatio.equals(other.finalDriveRatio);

    }

}

