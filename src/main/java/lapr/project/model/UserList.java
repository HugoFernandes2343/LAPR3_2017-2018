package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserList implements Serializable{
    
    private static final long serialVersionUID = 202L;
    
    private Set<User> listUsers;
    
    /**
     *  Empty Constructor
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
