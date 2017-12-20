package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class VelocityLimitList implements Serializable{

    private static final long serialVersionUID = 203L;
    
    private Set<VelocityLimit> ListVelocityLimits;

    /**
     * Empty constructor
     */
    public VelocityLimitList() {
        this.ListVelocityLimits = new HashSet<>();
    }

    /**
     * @return the velocity_limit_list 
     */
    public Set<VelocityLimit> getVelocityLimitList() {
        return ListVelocityLimits;
    }
    
    /**
     * Adds a VelocityLimit to the list
     * @param limit - VelocityLimit
     */
    public void addVelocityLimit(VelocityLimit limit){
        if(!this.ListVelocityLimits.contains(limit)){
            this.ListVelocityLimits.add(limit);
        }
    }

}
