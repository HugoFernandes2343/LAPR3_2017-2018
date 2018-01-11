package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lapr.project.utils.DatabaseExchangable;

public class TollFare extends DatabaseExchangable implements Serializable {

    private static final long serialVersionUID = 101L;

    private List<TollClass> listClasses = new LinkedList<>();

    /**
     * Complete Constructor
     *
     * @param classes - listClasses Classes Linked List
     */
    public TollFare(List<TollClass> classes) {
        this.listClasses = classes;
    }

    /**
     * Empty constructor
     */
    public TollFare() {
        this.listClasses = new LinkedList<>();
    }

    /**
     * Adds a vehicle class to the toll_fare linked list
     *
     * @param id - class id
     * @param price - toll_fare class price
     */
    public void addClass(String id, Double price) {
        TollClass vehicleClass = new TollClass(id, price);
        this.listClasses.add(vehicleClass);
    }

    /**
     * @return listClasses of the tollFare
     */
    public List<TollClass> getListClasses() {
        return listClasses;
    }

    /**
     * Setter for the
     *
     * @param classes listClasses of the tollFare
     */
    public void setListClasses(List<TollClass> classes) {
        this.listClasses = classes;
    }

    /**
     *  method that returns the data to relate to the dataBase
     */
    @Override
    public List<DatabaseExchangable> getDBData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.add(this);
        return temp;
    }

    /**
     * Hash code for teh tollfare class
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.listClasses);
        return hash;
    }

    /**
     * @param obj the object to compare to the tollfare
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
        final TollFare other = (TollFare) obj;
        return Objects.equals(this.listClasses, other.listClasses);       
        
    }
    
}
