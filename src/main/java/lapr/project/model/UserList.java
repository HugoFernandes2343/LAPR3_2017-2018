/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 
 */
public class UserList implements Serializable{
    
    private static final long serialVersionUID = 202L;
    
    private Set<User> user_list;
    
    public UserList(){
    this.user_list = new HashSet<>();
    }
}
