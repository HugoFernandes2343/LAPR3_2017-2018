package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

public class VelocityLimitList implements Serializable, DatabaseExchangable {

    private static final long serialVersionUID = 203L;

    private Set<VelocityLimit> listVelocityLimits;

    /**
     * Empty constructor
     */
    public VelocityLimitList() {
        this.listVelocityLimits = new HashSet<>();
    }

    /**
     * @return the velocity_limit_list
     */
    public Set<VelocityLimit> getVelocityLimitList() {
        return listVelocityLimits;
    }

    /**
     * Adds a VelocityLimit to the list
     *
     * @param limit - VelocityLimit
     */
    public void addVelocityLimit(VelocityLimit limit) {
        if (!this.listVelocityLimits.contains(limit)) {
            this.listVelocityLimits.add(limit);
        }
    }

    /**
     *
     * @param type Type of segment the vehicle will travers with certain
     * velocity limits
     * @return the velocity limit allowed for that type of segment for the
     * vehicle
     */
    public int getVelocityLimit(String type) {
        int limit = 0;
        for (VelocityLimit vl : listVelocityLimits) {
            if (type.toUpperCase().contains(vl.getSegmentType().toUpperCase())) {
                limit = vl.getLimit();
            }
        }
        return limit;
    }

     /**
     * HashCode method for velocity limit type objects
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.listVelocityLimits);
        return hash;
    }

     /**
     * Equals method for velocity limit type objects
     *
     * @param obj the object to compare to the throttle
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
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
        VelocityLimitList other = (VelocityLimitList) obj;
        Iterator<VelocityLimit> itr = this.listVelocityLimits.iterator();
        while(itr.hasNext()){
           VelocityLimit vl =  itr.next();
            if(!other.getVelocityLimitList().contains(vl)){
                return false;
            }
        }
        return true;
    }

    /**
     * method that returns the data to relate to the dataBase
     */
    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.addAll(this.listVelocityLimits);
        return temp;
    }
}
