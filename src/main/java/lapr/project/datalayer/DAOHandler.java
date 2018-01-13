package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.*;
import lapr.project.utils.DataExchangableList;
import lapr.project.utils.DatabaseExchangable;

/**
 * Handles the update of data to the database
 */
public class DAOHandler {

    /**
     * Connection
     */
    private final Connection con;

    /**
     * TravelByPhysics entity
     */
    private final TravelByPhysics travelByPhysics;

    /**
     * Opens the connection
     *
     * @param TravelByPhysics TravelByPhysics object
     * @throws SQLException If the operation is not successful
     */
    public DAOHandler(TravelByPhysics tf) throws SQLException {
        con = ConnectionManager.openConnection();
        this.travelByPhysics = tf;
    }

    public Connection getConnection() {
        return this.con;
    }

    /**
     * Commits the changes made to the database
     */
    public void commitChangesMadeToTheDatabase() {
        try {
            ConnectionManager.commitChanges(con);
        } catch (SQLException ex) {
            Logger.getLogger(DAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Replaces all data in the database with the current data in the project
     * (data in database + changes)
     *
     * @throws SQLException If the operation was not successful
     */
    public void saveData(ProjectList list) throws SQLException {
        try {
            deleteAllData();
            addAllData(list);
            ConnectionManager.commitChanges(con);
        } catch (SQLException ex) {
            ConnectionManager.rollBackChanges(con);
            throw new SQLException(ex);
        }
    }

    /**
     * Deletes all data stored in the database
     *
     * @throws SQLException If the operation was not successful
     */
    private void deleteAllData() throws SQLException {
        try (CallableStatement cs = con.prepareCall("{call REMOVE_TABLE_DATA}")) {
            cs.executeUpdate();
        }
    }

    /**
     * Adds all projects to the database
     *
     * @throws SQLException If the addition was impossible
     */
    private void addAllData(ProjectList list) throws SQLException {
        DAOProject dao = new DAOProject();

        for (Project project : list.getAllProjects()) {
            dao.addData(con, project);
            addToProject(project);
        }
    }

    public void addProjectData(Project p) throws SQLException {
        addObjectData(new DAOProject(), p);
        addToProject(p);
//        addNetworkAnalysis(p);
    }

    private void addNetworkAnalysis(Project project) throws SQLException{

        for(NetworkAnalysis netAnal : project.getNetworkAnalysis()){
            addObjectData(new DAONetworkAnalysis(project),netAnal);
            
            for(RoadSection nodeBestPath : netAnal.getBestPath()){
                addObjectData(new DAOBestPath(netAnal),nodeBestPath);
            }
        }
    }
    
    /**
     * Adds data of a project to the database
     *
     * @param project Project where the all the data is stored
     * @throws SQLException If the operation was not successful
     */
    private void addToProject(Project project) throws SQLException {
        /*Add the vehicles*/
        addObjectData(new DAOVehicle(project), project.getVehicleList());

        for (Vehicle tempVehicle : project.getVehicleList().getVehicleList()) {

            /*Add VelocityLimits*/
            for (VelocityLimit limit : tempVehicle.getVelocityLimitList().getVelocityLimitList()) {
                addObjectData(new DAOVelocityList(tempVehicle), limit);
            }

            /*Add Energy*/
            addObjectData(new DAOEnergy(tempVehicle), tempVehicle.getEnergy());

            /*Add Gears*/
            for (Gear gTemp : tempVehicle.getEnergy().getGearList()) {
                addObjectData(new DAOGear(tempVehicle), gTemp);
            }
            
            /*Add Throttles*/
            for (Throttle tTemp : tempVehicle.getEnergy().getThrottleList()) {
                addObjectData(new DAOThrottle(tempVehicle), tTemp);
                /*Add Regimes*/
                for (Regime rTemp : tTemp.getRegimeList()) {
                    addObjectData(new DAORegime(tTemp), rTemp);
                }
            }
        }

        /*Add Network*/
        addObjectData(new DAONetwork(project), project.getNetwork());
        /*Add Nodes*/
        for(Node nTemp : project.getNetwork().getNodeList()){
            addObjectData(new DAONode(project.getNetwork()), nTemp);
        }
        
        /*Add Roads*/
        for(Road roadTemp : project.getNetwork().getRoadList()){
            addObjectData(new DAORoad(project.getNetwork()),roadTemp);
            
            for(TollClass tollTemp : roadTemp.getTollFare().getListClasses()){
                addObjectData(new DAOTollClass(roadTemp),tollTemp);
            }
        }
        
        /*Add RoadSections*/
        for(RoadSection roadSectionTemp : project.getNetwork().getSectionList()){
            addObjectData(new DAORoadSection(),roadSectionTemp);
            
            for(Segment segTemp : roadSectionTemp.getSegmentList()){
                addObjectData(new DAOSegment(roadSectionTemp),segTemp);
            }
        }
        
        for(NetworkAnalysis netAnal : project.getNetworkAnalysis()){
            addObjectData(new DAONetworkAnalysis(project),netAnal);
            
            for(RoadSection nodeBestPath : netAnal.getBestPath()){
                addObjectData(new DAOBestPath(netAnal),nodeBestPath);
                
                for(Segment seg : nodeBestPath.getSegmentList()){
                    addObjectData(new DAONetSegment(nodeBestPath,netAnal),seg);
                }
            }
        }
    }

    /**
     * Adds a object to the database
     *
     * @param dao DAO class
     * @param dataHolder object
     * @throws SQLException If the operation is not successful
     */
    public void addObjectData(DAOManager dao, DatabaseExchangable dataHolder) throws SQLException {  //Adicionar o metodo getList à interface
        if (dataHolder != null) {
            for (DatabaseExchangable object : dataHolder.getDBData()) {
                dao.addData(con, object);
            }
        }
    }

    /**
     * Reads the data from the database. Adds the data to this travelByPhysics
     *
     * @throws SQLException If the operation wasn't successful
     */
    public void readAllData(ProjectList list) throws SQLException {
        /*Read All Projects, references are empty*/
        readObjectData(new DAOProject(), travelByPhysics.getProjectList(),new Object[1]);
        DAOProject dao = new DAOProject();
        
        for (Project project : list.getAllProjects()) {
            dao.readData(con, project,new Object[0]);
            readProjectData(project);
        }
    }

    private void readProjectData(Project p) {
        readVehicleData(p);
        //toADD: readNetworkData(p)
    }

    private void readVehicleData(Project p) {
        try {
            VehicleList vehicleList = p.getVehicleList();
            Object[] refs = {p.getName()};
            readObjectData(new DAOVehicle(p), vehicleList, refs);

            for (Vehicle v : vehicleList.getVehicleList()) {
                /*Read the Energy*/
                Energy tempEnergy = v.getEnergy();
                Object[] refs2 = {v.getName()};
                readObjectData(new DAOEnergy(v), tempEnergy, refs2);

                /*Read the gears*/
                readObjectData(new DAOGear(v), new DataExchangableList(tempEnergy.getDBGearData()), refs2);

                /*Read the velocityLimits*/
                VelocityLimitList tempVLimit = v.getVelocityLimitList();
                readObjectData(new DAOVelocityList(v), tempVLimit, refs2);

                /*Read the Throttle*/
                readObjectData(new DAOThrottle(v), new DataExchangableList(tempEnergy.getDBThrottleData()), refs2);

                /*Read the regimes based on Throttles*/
                for (Throttle t : tempEnergy.getThrottleList()) {
                    Object[] refs3 = {t.getPercentage(), v.getName()};
                    readObjectData(new DAORegime(t), new DataExchangableList(t.getDBRegimeData()), refs3);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void readNetworkData(Project p){
//        try{
//            Network network = p.getNetwork();
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    /**
     * Reads a library from the database
     *
     * @param dao Implementation of DAOManager
     * @param library library in which data will be added
     * @throws SQLException If the operation wasn't successful
     */
    private void readObjectData(DAOManager dao, DatabaseExchangable placeToAdd, Object[] refs) throws SQLException {//refs might need to get out
        dao.readData(con, placeToAdd, refs);
    }

}
