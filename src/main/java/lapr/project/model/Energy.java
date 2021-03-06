package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.DatabaseExchangable;

public class Energy extends DatabaseExchangable implements Serializable {

    private static final long serialVersionUID = 503L;

    private int minRpm;
    private int maxRpm;
    private Double finalDriveRatio;
    private double err;
    private List<Gear> gearList = new LinkedList<>();
    private List<Throttle> throttleList = new LinkedList<>();

    /**
     * Full constructor of the energy objects
     *
     * @param minRpm minimum rpm value
     * @param maxRpm maximum rpm value
     * @param finalDriveRatio final drive ratio value
     * @param gearList list of gears present on the vehicle the engery object
     * belongs to
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
     * @return the err
     */
    public double getErr() {
        return err;
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
     * @param err the Energy Regeneration Ratio
     */
    public void setErr(double err) {
        this.err = err;
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

    /**
     * method that returns the throttle by name
     */
    public Throttle getThrottle(String throttleId) {
        for (Throttle t : throttleList) {
            if (t.getPercentage().equals(throttleId)) {
                return t;
            }
        }
        return null;
    }

    /**
     * method that returns the data to relate to the dataBase
     */
    @Override
    public List<DatabaseExchangable> getDBData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.add(this);
        return temp;
    }
    
    public List<DatabaseExchangable> getDBGearData(){
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.addAll(this.gearList);
        return temp;
    }
    
    public List<DatabaseExchangable> getDBThrottleData(){
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.addAll(this.throttleList);
        return temp;
    }
}
