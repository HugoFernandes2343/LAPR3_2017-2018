package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

public class UserList implements Serializable, DatabaseExchangable {

    private static final long serialVersionUID = 202L;

    private Set<User> listUsers;

    /**
     * Empty Constructor
     */
    public UserList() {
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

    /**
     *  method that returns the data to relate to the dataBase
     */
    @Override
    public List<DatabaseExchangable> getDBData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.addAll(this.listUsers);
        return temp;
    }

}
