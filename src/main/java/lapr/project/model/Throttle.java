package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class Throttle implements Serializable {

    private static final long serialVersionUID = 100L;

    private String id;
    private LinkedList<Regime> regime_list;

    /**
     * Empty constructor
     */
    public Throttle() {
        this.regime_list = new LinkedList<>();
    }

    public Throttle(String id, LinkedList<Regime> regime_list) {
        this.id = id;
        this.regime_list = regime_list;
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the regime_list
     */
    public LinkedList<Regime> getRegime_list() {
        return regime_list;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     * @param obj the object to compare to the throttle
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
        Throttle other = (Throttle) obj;
        return this.id.equalsIgnoreCase(other.id);

    }

}
