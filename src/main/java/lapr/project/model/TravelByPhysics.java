package lapr.project.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.datalayer.DAOHandler;
import lapr.project.datalayer.DAONetworkAnalysis;
import lapr.project.datalayer.DAORegime;
import lapr.project.datalayer.DAORoadSection;
import lapr.project.datalayer.DAOThrottle;
import lapr.project.utils.Algorithm;
import lapr.project.utils.MostEfficientPathInEnergySavingModeAlgorithm;
import lapr.project.utils.ShortestTravellTimeAlgorithm;
import lapr.project.utils.TheoreticalMostEnergyEfficientAlgorithm;

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
     * Attribute that keeps the algorithms list
     */
    private List<Algorithm> algorithmsList;

    /**
     * Constructor of this class
     */
    public TravelByPhysics() {
        this.projectList = new ProjectList();
        this.algorithmsList = new ArrayList<>();
        this.algorithmsList.add(new ShortestTravellTimeAlgorithm());
        this.algorithmsList.add(new MostEfficientPathInEnergySavingModeAlgorithm());
        this.algorithmsList.add(new TheoreticalMostEnergyEfficientAlgorithm());
        createDataHandler();
        getMaxIndexes();
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
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, "Connection is not possible! Please connect to the enterprise's VPN.", ex);
        }
    }

    private void getMaxIndexes() {
        int i, j, k, g;
        try {
            DAOThrottle daoThrottle = new DAOThrottle(new Vehicle());
            i = daoThrottle.getHightestIDinThrottle(dataExchange.getConnection());
            Throttle.setFlag(i + 1);
        } catch (SQLException ex1) {
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, "Error setting highest Throttle ID's from the DB", ex1);
            Throttle.setFlag(1);
        }
        try {
            DAORegime daoRegime = new DAORegime(new Throttle());
            j = daoRegime.getHighestIDinRegime(dataExchange.getConnection());
            Regime.setFlag(j + 1);
        } catch (SQLException ex2) {
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, "Error setting highest Regime ID's from the DB", ex2);
            Regime.setFlag(1);
        }
        try {
            DAORoadSection daoRoadSection = new DAORoadSection();
            k = daoRoadSection.getHighestIDinRoadSection(dataExchange.getConnection());
            RoadSection.setFlag(k + 1);
        } catch (SQLException ex3) {
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, "Error setting highest RoadSection ID's from the DB", ex3);
            RoadSection.setFlag(1);
        }
        try {
            DAONetworkAnalysis daoNetAnal = new DAONetworkAnalysis(this.getProjectList().getActualProject());
            g = daoNetAnal.getHightestIDinNetwork(dataExchange.getConnection());
            NetworkAnalysis.setFlag(g + 1);
        } catch (SQLException ex4) {
            Logger.getLogger(TravelByPhysics.class.getName()).log(Level.SEVERE, "Error setting highest Net analysis ID's from the DB", ex4);
            NetworkAnalysis.setFlag(1);
        }
    }
}
