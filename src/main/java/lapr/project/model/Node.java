package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

public class Node implements Serializable,DatabaseExchangable {

    private static final long serialVersionUID = 505L;

    private String id;

    public Node() {
        this.id = "test";
    }

    public Node(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @param obj the object to compare to the node
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node other = (Node) obj;
        return this.id.equalsIgnoreCase(other.id);
    }

    /**
     * Hash code fo the object node
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * method that returns the data to relate to the dataBase
     */
    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.add(this);
        return temp;
    }
}
