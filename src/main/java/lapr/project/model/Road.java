package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

public class Road implements Serializable, DatabaseExchangable {

    private static final long serialVersionUID = 604L;

    private String name;
    private String typology;
    private String id;
    private TollFare tollFare;

    /**
     * Empty Constructor
     */
    public Road() {
        this.tollFare = new TollFare();
    }

    /**
     * Constructor of the Road Object
     *
     * @param name - name of the road
     * @param description - tipology of the road
     * @param id - id of the road
     */
    public Road(String name, String description, String id) {
        this.name = name;
        this.typology = description;
        this.id = id;
    }

    /**
     * Complete constructor
     *
     * @param name - name of the road
     * @param typology - tipology of the road
     * @param id - id of the road
     * @param tollFare
     */
    public Road(String name, String typology, String id, TollFare tollFare) {
        this.name = name;
        this.typology = typology;
        this.id = id;
        this.tollFare = tollFare;
    }

    /**
     * @return the name name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * Set method of the name variable
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the typology typology of the road
     */
    public String getTypology() {
        return typology;
    }

    /**
     * Set method of the typology variable
     *
     * @param typology the typology to set
     */
    public void setTypology(String typology) {
        this.typology = typology;
    }

    /**
     * @return the id id of the road
     */
    public String getId() {
        return id;
    }

    /**
     * Set method of the id variable
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the tollFare toll fare object of the road
     */
    public TollFare getTollFare() {
        return tollFare;
    }

    /**
     * @param tollFare the tollFare to set
     */
    public void setTollFare(TollFare tollFare) {
        this.tollFare = tollFare;
    }

    /**
     * Equals method of the road type objects
     *
     * @param obj the object to compare to the Road
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
        Road other = (Road) obj;
        return this.id.equals(other.id);
    }

    /**
     * HashCode method of the road type objects
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * To string method of the road type objects
     *
     * @return the String representation of the Road
     */
    @Override
    public String toString() {
        return String.format("Road name: %s, id: %s", name, id);
    }

    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.add(this);
        return temp;
    }
}
