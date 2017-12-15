
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;

public class TollFare implements Serializable{
    private static final long serialVersionUID = 101L;
    
    private LinkedList<Class> toll_fare;
    
    /**
     * Complete Constructor
     * @param toll_fare - toll_fare Class Linked List
     */
    public TollFare(LinkedList<Class> toll_fare) {
        this.toll_fare = toll_fare;
    }
    
    /**
     * Empty constructor
     */
    public TollFare() {
        this.toll_fare = new LinkedList<>();
    }
    
    /**
     * Adds a vehicle class to the toll_fare linked list 
     * @param id - class id
     * @param price - toll_fare class price
     */
    public void addClass(String id, Double price){
        Class vehicle_class = new Class(id,price);
        this.toll_fare.add(vehicle_class);
    }
    
    /**
     * @return toll_fare LinkedList 
     */
    public LinkedList<Class> getToll_fare() {
        return toll_fare;
    }
    
    /**
     * Setter for the
     * @param toll_fare toll_fare LinkedList
     */
    public void setToll_fare(LinkedList<Class> toll_fare) {
        this.toll_fare = toll_fare;
    }
    
    
    
}
