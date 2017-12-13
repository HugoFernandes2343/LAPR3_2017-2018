/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author Hugo
 */
public class Node {
    
    private String id;
    
    public Node(){
        
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
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Node)){
            return false;
        }
        Node other = (Node) obj;
        return this.id.equalsIgnoreCase(other.id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
