package lapr.project.model;

import java.io.Serializable;
import java.util.HashSet;
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

    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.addAll(this.listUsers);
        return temp;
    }

}
