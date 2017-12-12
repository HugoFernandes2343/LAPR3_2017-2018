/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author hugod
 */
public class Segment {

    private int segmentIndex;
    private double initialHeight;
    private double slope;
    private double length;
    private double windDirection;
    private double windSpeed;
    private double maximumVelocity;
    private double minimumVelocity;
    private Object toll;

    public Segment(int segmentIndex, double initialHeight, double slope, double length, double windDirection, double windSpeed, double maximumVelocity, double minimumVelocity, Object toll) {
        this.segmentIndex = segmentIndex;
        this.initialHeight = initialHeight;
        this.slope = slope;
        this.length = length;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.maximumVelocity = maximumVelocity;
        this.minimumVelocity = minimumVelocity;
        this.toll = toll;
    }

    /**
     * @return the segmentIndex
     */
    public int getSegmentIndex() {
        return segmentIndex;
    }

    /**
     * @return the initialHeight
     */
    public double getInitialHeight() {
        return initialHeight;
    }

    /**
     * @return the slope
     */
    public double getSlope() {
        return slope;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @return the windDirection
     */
    public double getWindDirection() {
        return windDirection;
    }

    /**
     * @return the windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @return the maximumVelocity
     */
    public double getMaximumVelocity() {
        return maximumVelocity;
    }

    /**
     * @return the minimumVelocity
     */
    public double getMinimumVelocity() {
        return minimumVelocity;
    }

    /**
     * @return the toll
     */
    public Object getToll() {
        return toll;
    }

    /**
     *
     * @return the integer representation of the object Segment
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.segmentIndex;
        return hash;
    }

    
    /**
     *
     * @param obj the object to compare to the Segment
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Segment other = (Segment) obj;
        return this.segmentIndex == other.segmentIndex;
    }

    /**
     *
     * @return the String representation of the Segment
     */
    @Override
    public String toString() {
        return String.format("Segment id: %d", this.segmentIndex);
    }

}
