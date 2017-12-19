
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TollFare implements Serializable{
    private static final long serialVersionUID = 101L;
    
    private List<Class> toll_fare = new LinkedList<>();
    
    /**
     * Complete Constructor
     * @param tollFare - tollFare Class Linked List
     */
    public TollFare(List<Class> tollFare) {
        this.toll_fare = tollFare;
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
    public List<Class> getTollFare() {
        return toll_fare;
    }
    
    /**
     * Setter for the
     * @param tollFare toll_fare LinkedList
     */
    public void setTollFare(List<Class> tollFare) {
        this.toll_fare = tollFare;
    }
    
    
    
}
