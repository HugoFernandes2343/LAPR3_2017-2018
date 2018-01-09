package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Project;
import lapr.project.model.ProjectList;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.Vehicle;
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

    /**
     * Adds the library of a project to the database
     *
     * @param project Project where the libraries are located
     * @throws SQLException If the operation was not successful
     */
    private void addToProject(Project project) throws SQLException {
        addObjectData(new DAOVehicle(project), project.getVehicleList());
        
        //ADD FOREIGN KEYS
        LinkedList<Vehicle> tempVehicleList = new LinkedList<>();
        tempVehicleList.addAll(project.getVehicleList().getVehicleList());
        
        for (Vehicle tempVehicle : tempVehicleList) {
            tempVehicle.getVelocityLimitList();
        }
        addObjectData(new DAONetwork(project), project.getNetwork());
        for(int i = 0;i<project.getNetwork().getNodeList().size();i++){
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
     * Reads the data from the database. Adds the data to this flyGreen
     *
     * @throws SQLException If the operation wasn't successful
     */
    /**
     * private void readAllData() throws SQLException { readObjectData(new
     * DAOProject(), travelByPhysics.getProjectList());
     *
     * for (Project project : flyGreen.getProjectLibrary().getList()) {
     * readObjectData(new DAOAircraftModel(project),
     * project.getAircraftModelLibrary()); readObjectData(new
     * DAOAircraft(project), project.getAircraftLibrary()); readObjectData(new
     * DAONode(project), project.getNodeLibrary()); readObjectData(new
     * DAOSegment(project), project.getSegmentLibrary()); readObjectData(new
     * DAOAirport(project), project.getAirportLibrary()); readObjectData(new
     * DAOResults(project), project.getResultsLibrary()); readObjectData(new
     * DAOFlightPlan(project), project.getFlightPlanLibrary());
     * readObjectData(new DAOCharter(project), project.getFlightLibrary());
     * readObjectData(new DAORegular(project), project.getFlightLibrary()); } }
     */
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
