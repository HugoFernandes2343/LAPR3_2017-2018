package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;

public class Gear implements Serializable {

    private static final long serialVersionUID = 504L;

    private String id;
    private double ratio;

    public Gear() {
    }

    /**
     *
     * @param id
     * @param ratio
     */
    public Gear(String id, double ratio) {
        this.id = id;
        this.ratio = ratio;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the ratio
     */
    public double getRatio() {
        return ratio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    /**
     * Hash code fo the object gear
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     *
     ** @param obj the object to compare to the gear
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
        Gear other = (Gear) obj;
        return this.id.equalsIgnoreCase(other.id);

    }

}