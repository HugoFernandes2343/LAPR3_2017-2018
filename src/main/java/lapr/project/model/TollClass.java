package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

public class TollClass implements Serializable, DatabaseExchangable {

    private static final long serialVersionUID = 501L;

    private String id;

    private Double price;

    /**
     * Empty constructor
     */
    public TollClass() {
    }

    /**
     * Complete constructor
     *
     * @param id - id of the class
     * @param price - toll price
     */
    public TollClass(String id, Double price) {
        this.id = id;
        this.price = price;
    }

    /**
     *
     * @param id id given to the class
     */
    public TollClass(String id) {
        this.id = id;
    }

    /**
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     *
     * @return id of the class
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the class id
     *
     * @param id - new id of the class
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Settercfor the price
     *
     * @param price - toll price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Hash code fo the object class
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * @param obj the object to compare to the class
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
        TollClass other = (TollClass) obj;

        return this.id.equalsIgnoreCase(other.id);
    }

    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.add(this);
        return temp;
    }

}
