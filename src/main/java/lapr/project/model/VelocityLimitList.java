package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class VelocityLimitList implements Serializable{

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
     * @param limit - VelocityLimit
     */
    public void addVelocityLimit(VelocityLimit limit){
        if(!this.listVelocityLimits.contains(limit)){
            this.listVelocityLimits.add(limit);
        }
    }

}
