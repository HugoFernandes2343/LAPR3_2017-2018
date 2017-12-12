/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

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

    public int getSegmentIndex() {
        return segmentIndex;
    }

    public void setSegmentIndex(int segmentIndex) {
        this.segmentIndex = segmentIndex;
    }

    public double getInitialHeight() {
        return initialHeight;
    }

    public void setInitialHeight(double initialHeight) {
        this.initialHeight = initialHeight;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getMaximumVelocity() {
        return maximumVelocity;
    }

    public void setMaximumVelocity(double maximumVelocity) {
        this.maximumVelocity = maximumVelocity;
    }

    public double getMinimumVelocity() {
        return minimumVelocity;
    }

    public void setMinimumVelocity(double minimumVelocity) {
        this.minimumVelocity = minimumVelocity;
    }

    public Object getToll() {
        return toll;
    }

    public void setToll(Object toll) {
        this.toll = toll;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.segmentIndex;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.initialHeight) ^ (Double.doubleToLongBits(this.initialHeight) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.slope) ^ (Double.doubleToLongBits(this.slope) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.length) ^ (Double.doubleToLongBits(this.length) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.windDirection) ^ (Double.doubleToLongBits(this.windDirection) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.windSpeed) ^ (Double.doubleToLongBits(this.windSpeed) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.maximumVelocity) ^ (Double.doubleToLongBits(this.maximumVelocity) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.minimumVelocity) ^ (Double.doubleToLongBits(this.minimumVelocity) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.toll);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Segment other = (Segment) obj;
        if (this.segmentIndex != other.segmentIndex) {
            return false;
        }
        if (Double.doubleToLongBits(this.initialHeight) != Double.doubleToLongBits(other.initialHeight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.slope) != Double.doubleToLongBits(other.slope)) {
            return false;
        }
        if (Double.doubleToLongBits(this.length) != Double.doubleToLongBits(other.length)) {
            return false;
        }
        if (Double.doubleToLongBits(this.windDirection) != Double.doubleToLongBits(other.windDirection)) {
            return false;
        }
        if (Double.doubleToLongBits(this.windSpeed) != Double.doubleToLongBits(other.windSpeed)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maximumVelocity) != Double.doubleToLongBits(other.maximumVelocity)) {
            return false;
        }
        if (Double.doubleToLongBits(this.minimumVelocity) != Double.doubleToLongBits(other.minimumVelocity)) {
            return false;
        }
        if (!Objects.equals(this.toll, other.toll)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Segment{" + "segmentIndex=" + segmentIndex + ", initialHeight=" + initialHeight + ", slope=" + slope + ", length=" + length + ", windDirection=" + windDirection + ", windSpeed=" + windSpeed + ", maximumVelocity=" + maximumVelocity + ", minimumVelocity=" + minimumVelocity + ", toll=" + toll + '}';
    }
    
}
