/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 
 */
public class Node implements Serializable{
    
    private static final long serialVersionUID = 505L;
    
    private String id;
    
    public Node(){ 
        this.id = "test";
    }
    
    public Node(String id){
        this.id=id;
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
     *@param obj the object to compare to the node
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Node)){
            return false;
        }
        Node other = (Node) obj;
        return this.id.equalsIgnoreCase(other.id);
    }

    /**
     *Hash code fo the object node
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }
}