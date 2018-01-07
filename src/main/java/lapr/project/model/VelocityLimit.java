package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;
import lapr.project.utils.DatabaseExchangable;

public class VelocityLimit implements Serializable,DatabaseExchangable {

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
     * @param segmentType type of the segment
     * @param limit velocity limit in this segment type
     */
    public VelocityLimit(String segmentType, int limit) {
        this.segmentType = segmentType;
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
     * @param segmentType type of segment to be set
     */
    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
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
