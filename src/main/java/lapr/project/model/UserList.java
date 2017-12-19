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
    
    private Set<User> listUsers;
    
    /**
     *
     */
    public UserList(){
    this.listUsers = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public Set<User> getUserList() {
        return listUsers;
    }

    /**
     *
     * @param userList
     */
    public void setUserList(Set<User> userList) {
        this.listUsers = userList;
    }
    
    
}
