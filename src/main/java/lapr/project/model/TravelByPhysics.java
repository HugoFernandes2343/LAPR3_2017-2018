package lapr.project.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lapr.project.datalayer.DAOHandler;
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
    private DAOHandler dataExchange;
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
        createDataHandler();
        System.out.println("Test");
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
     * @return the dataExchange
     */
    public DAOHandler getDAOHandler() {
        return this.dataExchange;
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

    private void createDataHandler() {
        try {
            this.dataExchange = new DAOHandler(this);
        } catch (SQLException ex) {
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

}
