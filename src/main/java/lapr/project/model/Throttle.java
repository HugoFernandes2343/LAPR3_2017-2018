package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Throttle implements Serializable {

    private static final long serialVersionUID = 100L;

    private String id;
    private List<Regime> regimeList = new LinkedList<>();

    /**
     * Empty constructor
     */
    public Throttle() {
        this.regimeList = new LinkedList<>();
    }

    /**
     * Full constructor of the throttle type object
     * 
     * @param id id of the throttle object
     * @param regimeList List of regiments that will be placed in this object
     */
    public Throttle(String id, List<Regime> regimeList) {
        this.id = id;
        this.regimeList = regimeList;
    }

    /**
     * Setter for the Throttle id
     *
     * @param id - throttle id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the id id of the throttle 
     */
    public String getId() {
        return id;
    }

    /**
     * @return the regime_list list of regiments for this Throttle
     */
    public List<Regime> getRegimeList() {
        return regimeList;
    }

    /**
     * HashCode method for throttle type objects
     * 
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash =6;
        hash = 19 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     * Equals method for throttle type objects
     * 
     * @param obj the object to compare to the throttle
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Throttle)) {
            return false;
        }
        Throttle other = (Throttle) obj;
        return this.id.equalsIgnoreCase(other.id);

    }

}
