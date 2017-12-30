package lapr.project.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.datalayer.DataHandler;
import lapr.project.utils.Algorithm;
import lapr.project.utils.ShortestTravellTimeAlgorithm;

/**
 *
 * @author
 */
public class TravelByPhysics implements Serializable {

    private static final long serialVersionUID = 103L;

    /**
     * Data Exchange
     */
    private DataHandler dataExchange;
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
        Algorithm a = new ShortestTravellTimeAlgorithm();
        this.algorithmsList = new ArrayList<>();
        this.algorithmsList.add(a);
        this.dataExchange = new DataHandler();
        getFromDB();
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
    public List<String> getAlgorithmsByName() {
        List<String> names = new ArrayList<>();
        for (Algorithm a : this.algorithmsList) {
            names.add(a.toString());
        }
        return names;
    }

    /**
     * Get data from the database
     * NOTE: For now only the users are meant to be uploaded for testing purposes
     */
    private void getFromDB() {
        try {
            dataExchange.getUserList(userList);
        } catch (SQLException ex) {
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
