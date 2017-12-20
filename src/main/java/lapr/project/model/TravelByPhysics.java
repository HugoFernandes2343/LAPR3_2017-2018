package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class TravelByPhysics implements Serializable{
    private static final long serialVersionUID = 103L;
    
    /**
     * Attribute that keeps the project list of the system.
     */
    private ProjectList projectList;

    /**
     * Attribute that keeps the users list of the system.
     */
    private UserList userList;
    
    /**
     * Attribute that keeps the algorithms list
     */
    private List<Algorithm> algorithmsList;

    /**
     * Constructor of this class
     */
    public TravelByPhysics() {
        this.projectList = new ProjectList();
        this.userList = new UserList();
        this.algorithmsList = new ArrayList<>();
    }

    /**
     * @return the project_list
     */
    public ProjectList getProjectList() {
        return projectList;
    }
    
    /**
     * @return the algorithmsList
     */
    public List<Algorithm> getAlgorithmsList() {
        return algorithmsList;
    }
    
    /**
     * @return a List of all algorithms names
     */
    public List<String> getAlgorithmsByName(){
        List<String> names = new ArrayList<>();
        for(Algorithm a : this.algorithmsList){
            names.add(a.getName());
        }
        return names;
    }
    
    
}
