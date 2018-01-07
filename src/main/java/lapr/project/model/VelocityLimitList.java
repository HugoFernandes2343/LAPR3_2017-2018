package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
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

    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.addAll(this.listVelocityLimits);
        return temp;
    }
}
