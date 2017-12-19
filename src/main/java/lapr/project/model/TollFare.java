
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TollFare implements Serializable{
    private static final long serialVersionUID = 101L;
    
    private List<Class> tollFare = new LinkedList<>();
    
    /**
     * Complete Constructor
     * @param tollFare - tollFare Class Linked List
     */
    public TollFare(List<Class> tollFare) {
        this.tollFare = tollFare;
    }
    
    /**
     * Empty constructor
     */
    public TollFare() {
        this.tollFare = new LinkedList<>();
    }
    
    /**
     * Adds a vehicle class to the toll_fare linked list 
     * @param id - class id
     * @param price - toll_fare class price
     */
    public void addClass(String id, Double price){
        Class vehicle_class = new Class(id,price);
        this.tollFare.add(vehicle_class);
    }
    
    /**
     * @return toll_fare LinkedList 
     */
    public List<Class> getTollFare() {
        return tollFare;
    }
    
    /**
     * Setter for the
     * @param tollFare toll_fare LinkedList
     */
    public void setTollFare(List<Class> tollFare) {
        this.tollFare = tollFare;
    }
    
    
    
}
