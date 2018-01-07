package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

public class Segment implements Serializable, DatabaseExchangable {

    private static final long serialVersionUID = 506L;

    private String id;
    private double initHeight;
    private double finalHeight;
    private String length;
    private double windDirection;
    private String windSpeed;
    private String maxVelocity;
    private String minVelocity;

    /**
     * Empty Constructor
     */
    public Segment() {
    }

    /**
     * Full Constructor of segment objects
     *
     * @param segmentIndex Index of a segment object
     * @param initialHeight initial height of a segment
     * @param finalHeight angle of the slope
     * @param length length of the segment
     * @param windDirection direction of the wind ( discuss with team)
     * @param windSpeed speed of the wind that is blowing in said segment
     * @param maxVelocity maximum velocity allowed in the segment
     * @param minVelocity minimum velocity allowed in the segment
     */
    public Segment(String segmentIndex, double initialHeight, double finalHeight, String length, double windDirection, String windSpeed, String maxVelocity, String minVelocity) {
        this.id = segmentIndex;
        this.initHeight = initialHeight;
        this.finalHeight = finalHeight;
        this.length = length;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.maxVelocity = maxVelocity;
        this.minVelocity = minVelocity;
    }

    /**
     * @return the id id of the segment
     */
    public String getId() {
        return id;
    }

    /**
     * @return the init_height initial height of the segment
     */
    public double getInitHeight() {
        return initHeight;
    }

    /**
     * @return the final_height final height of the segment
     */
    public double getFinalHeight() {
        return finalHeight;
    }

    /**
     * @return the length length of the segment
     */
    public String getLength() {
        return length;
    }

    /**
     * @return the wind_direction direction in which the wind is blowing
     */
    public double getWindDirection() {
        return windDirection;
    }

    /**
     * @return the wind_speed speed of the wind that blows in this segment
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * @return the maximumVelocity maximum allowed velocity in the segment
     */
    public String getMaxVelocity() {
        return maxVelocity;
    }

    /**
     * @return the minimumVelocity minimum allowed velocity in the segment
     */
    public String getMinVelocity() {
        return minVelocity;
    }

    /**
     * Set method of the id variable
     *
     * @param id id to give the segment
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set method of the initial height variable
     *
     * @param initHeight inital height of the segment
     */
    public void setInitHeight(double initHeight) {
        this.initHeight = initHeight;
    }

    /**
     * Set method of the final height variable
     *
     * @param finalHeight final height of the segment
     */
    public void setFinalHeight(double finalHeight) {
        this.finalHeight = finalHeight;
    }

    /**
     * Set method of the length variable
     *
     * @param length length variable to set on the segment
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * Set method of the wind direction variable
     *
     * @param windDirection direction to set on the segment
     */
    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Set method of the wind speed variable
     *
     * @param windSpeed speed of the blowing wind to set on the segment
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Set method of the maximum velocity variable
     *
     * @param maxVelocity maximum velocity to be set as the segment's max
     */
    public void setMaxVelocity(String maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    /**
     * Set method of the minimum velocity variable
     *
     * @param minVelocity minimum velocity to be set as the segment's min
     */
    public void setMinVelocity(String minVelocity) {
        this.minVelocity = minVelocity;
    }

    /**
     * Equals method of segment objects
     *
     * @param obj the object to compare to the Segment
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
        Segment other = (Segment) obj;
        return this.id.equals(other.id);
    }

    /**
     * hashcode method of segment objects
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * To string method of segment objects
     *
     * @return the String representation of the Segment
     */
    @Override
    public String toString() {
        return String.format("Segment id: %s", this.id);
    }

    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.add(this);
        return temp;
    }

}
