
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TollFare implements Serializable{
    private static final long serialVersionUID = 101L;
    
    private List<Class> listClasses = new LinkedList<>();
    
    /**
     * Complete Constructor
     * @param classes - listClasses Classes Linked List
     */
    public TollFare(List<Class> classes) {
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
     * @param id - class id
     * @param price - toll_fare class price
     */
    public void addClass(String id, Double price){
        Class vehicle_class = new Class(id,price);
        this.listClasses.add(vehicle_class);
    }
    
    /**
     * @return listClasses of the tollFare 
     */
    public List<Class> getTollFare() {
        return listClasses;
    }
    
    /**
     * Setter for the
     * @param classes listClasses of the tollFare 
     */
    public void setTollFare(List<Class> classes) {
        this.listClasses = classes;
    }
    
    
    
}
