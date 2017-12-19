/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author
 */
public class VelocityLimit implements Serializable {

    private static final long serialVersionUID = 204L;

    private String segmentType;
    private int limit;

    /**
     * Empty constructor
     */
    public VelocityLimit() {
    }

    /**
     * Full constructor for the Velocity limit type objects
     *
     * @param segment_type type of the segment
     * @param limit velocity limit in this segment type
     */
    public VelocityLimit(String segment_type, int limit) {
        this.segmentType = segment_type;
        this.limit = limit;
    }

    /**
     * @return segment type of the limit
     */
    public String getSegmentType() {
        return segmentType;
    }

    /**
     * @return velocity limit in this type of segment
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Set method of the segment type variable
     *
     * @param segment_type type of segment to be set
     */
    public void setSegmentType(String segment_type) {
        this.segmentType = segment_type;
    }

    /**
     * Set method of the velocity limit variable
     *
     * @param limit limit to be set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * HashCode method of the velocity limit type objects
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.segmentType);
        hash = 83 * hash + this.limit;
        return hash;
    }

    /**
     * Equals method of the velocity limit type objects
     *
     * @param obj the object to compare to the velocityLimit
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
        final VelocityLimit other = (VelocityLimit) obj;
        if (this.limit != other.limit) {
            return false;
        }
        return this.segmentType.equalsIgnoreCase(other.segmentType);

    }

}
