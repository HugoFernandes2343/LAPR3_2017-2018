package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.*;
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
     * @param flyGreen FlyGreen entity
     * @throws SQLException If the operation is not successful
     */
    public DAOHandler(TravelByPhysics tf) throws SQLException {
        con = ConnectionManager.openConnection();
        this.travelByPhysics = tf;
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

    private void addProjectData(Project p) throws SQLException {
        DAOProject dao = new DAOProject();
        dao.addData(con, p);
        addToProject(p);
    }

    /**
     * Adds the library of a project to the database
     *
     * @param project Project where the libraries are located
     * @throws SQLException If the operation was not successful
     */
    private void addToProject(Project project) throws SQLException {
        addObjectData(new DAOVehicle(project), project.getVehicleList());

        for (Vehicle tempVehicle : project.getVehicleList().getVehicleList()) {
            
            /*Add VelocityLimits*/
            for (int i = 0; i < tempVehicle.getVelocityLimitList().getVelocityLimitList().size(); i++) {
                addObjectData(new DAOVelocityList(tempVehicle), (VelocityLimit) tempVehicle.getVelocityLimitList().getDBData().get(i));
            }
            /*Add Energy*/
            addObjectData(new DAOEnergy(tempVehicle), tempVehicle.getEnergy());
            
            tempVehicle.getVelocityLimitList();
        }
        addObjectData(new DAONetwork(project), project.getNetwork());
        for (int i = 0; i < project.getNetwork().getNodeList().size(); i++) {
            addObjectData(new DAONode(), project.getNetwork().getNodeList().get(i));
        }

    }

    /**
     * Adds a library to the database
     *
     * @param dao DAO class
     * @param dataHolder Library
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
     * Reads all the data in the database.
     *
     * @throws SQLException If the operation wasn't successful
     */
    /**
     * public void readData() throws SQLException { try { readAllData(); } catch
     * (SQLException ex) { throw new SQLException(ex); } finally {
     * ConnectionManager.closeConnection(con); } }
     */
    /**
     * Reads the data from the database. Adds the data to this travelByPhysics
     *
     * @throws SQLException If the operation wasn't successful
     */
    private void readAllData() throws SQLException {
//        readObjectData(new DAOProject(), travelByPhysics.getProjectList());

//        for (Project project : flyGreen.getProjectLibrary().getList()) {
//            readObjectData(new DAOAircraftModel(project),project.getAircraftModelLibrary());
//            readObjectData(new DAOAircraft(project), project.getAircraftLibrary());
//            readObjectData(new DAONode(project), project.getNodeLibrary());
//            readObjectData(new DAOSegment(project), project.getSegmentLibrary());
//            readObjectData(new DAOAirport(project), project.getAirportLibrary());
//            readObjectData(new DAOResults(project), project.getResultsLibrary());
//            readObjectData(new DAOFlightPlan(project), project.getFlightPlanLibrary());
//            readObjectData(new DAOCharter(project), project.getFlightLibrary());
//            readObjectData(new DAORegular(project), project.getFlightLibrary());
//        }
    }

    private void readProjectData(Project p) {
        readVehicleData(p);
        //toADD: readNetworkData(p)
    }

    private void readVehicleData(Project p) {
        try {
            VehicleList vehicleList = p.getVehicleList();
            Object[] refs = {p.getName()};//Pode-se tirar o refs de tudo provavelmente
            readObjectData(new DAOVehicle(p), vehicleList, refs);

            for (Vehicle v : vehicleList.getVehicleList()) {
                /*Read the Energy*/
                Energy tempEnergy = v.getEnergy();
                Object[] refs2 = {v.getName()};//Pode-se tirar o refs de tudo provavelmente
                readObjectData(new DAOEnergy(v), tempEnergy, refs2);

                /*Read the gears*/
                readObjectData(new DAOGear(v), (DatabaseExchangable) tempEnergy.getGearList(), refs2);

                /*Read the velocityLimits*/
                VelocityLimitList tempVLimit = v.getVelocityLimitList();
                readObjectData(new DAOVelocityList(v), tempVLimit, refs2);

                /*Read the Throttle*/
                readObjectData(new DAOThrottle(v), (DatabaseExchangable) tempEnergy.getThrottleList(), refs2);

                /*Read the regimes based on Throttles*/
                for (Throttle t : tempEnergy.getThrottleList()) {
                    Object[] refs3 = {t.getPercentage(), v.getName()};
                    readObjectData(new DAORegime(t, v), (DatabaseExchangable) t.getRegimeList(), refs3);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
