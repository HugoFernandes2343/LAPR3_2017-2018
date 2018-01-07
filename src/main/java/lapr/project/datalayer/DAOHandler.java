package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import lapr.project.model.Project;
import lapr.project.model.TravelByPhysics;
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
     * FlyGreen entity
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
     * Replaces all data in the database with the current data in the project
     * (data in database + changes)
     *
     * @throws SQLException If the operation was not successful
     */
    public void saveData() throws SQLException {
        try {
            deleteAllData();
            addAllData();
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
//            cs.close();
        }
    }

    /**
     * Adds all projects to the database
     *
     * @throws SQLException If the addition was impossible
     */
    private void addAllData() throws SQLException {
        DAOProject dao = new DAOProject();

        for (Project project : travelByPhysics.getProjectList().getAllProjects()) {
            dao.addData(con, project);
            addProject(project);
        }
    }

    /**
     * Adds the library of a project to the database
     *
     * @param project Project where the libraries are located
     * @throws SQLException If the operation was not successful
     */
    private void addProject(Project project) throws SQLException {
        //addObjectData(new DAONetwork(project), project.getNetwork());
        /**
        addObjectData(new DAOAircraft(project), project.getAircraftLibrary());
        addObjectData(new DAONode(project), project.getNodeLibrary());
        addObjectData(new DAOSegment(project), project.getSegmentLibrary());
        addObjectData(new DAOAirport(project), project.getAirportLibrary());
        addObjectData(new DAOResults(project), project.getResultsLibrary());
        addObjectData(new DAOFlightPlan(project), project.getFlightPlanLibrary());
        addObjectData(new DAOCharter(project), project.getFlightLibrary());
        addObjectData(new DAORegular(project), project.getFlightLibrary());
        * */
    }

    /**
     * Adds a library to the database
     *
     * @param dao DAO class
     * @param dataHolder Library
     * @throws SQLException If the operation is not successful
     */
//    private void addObjectData(DAOManager dao, DatabaseExchangable dataHolder) throws SQLException {  //Adicionar o metodo getList à interface
//        if (!(dataHolder.getList().isEmpty())) {
//            for (Object object : dataHolder.getList()) {
//                dao.addData(con, object);
//            }
//        }
//    }

    /**
     * Reads all the data in the database.
     *
     * @throws SQLException If the operation wasn't successful
     */
    /**public void readData() throws SQLException {
        try {
            readAllData();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            ConnectionManager.closeConnection(con);
        }
    }*/

    /**
     * Reads the data from the database. Adds the data to this flyGreen
     *
     * @throws SQLException If the operation wasn't successful
     */
    /**private void readAllData() throws SQLException {
        readObjectData(new DAOProject(), travelByPhysics.getProjectList());

        for (Project project : flyGreen.getProjectLibrary().getList()) {
            readObjectData(new DAOAircraftModel(project), project.getAircraftModelLibrary());
            readObjectData(new DAOAircraft(project), project.getAircraftLibrary());
            readObjectData(new DAONode(project), project.getNodeLibrary());
            readObjectData(new DAOSegment(project), project.getSegmentLibrary());
            readObjectData(new DAOAirport(project), project.getAirportLibrary());
            readObjectData(new DAOResults(project), project.getResultsLibrary());
            readObjectData(new DAOFlightPlan(project), project.getFlightPlanLibrary());
            readObjectData(new DAOCharter(project), project.getFlightLibrary());
            readObjectData(new DAORegular(project), project.getFlightLibrary());
        }
    }
    */

    /**
     * Reads a library from the database
     *
     * @param dao Implementation of DAOManager
     * @param library library in which data will be added
     * @throws SQLException If the operation wasn't successful
     */
    private void readObjectData(DAOManager dao, DatabaseExchangable placeToAdd,Object[] refs) throws SQLException {//refs might need to get out
        dao.readData(con, placeToAdd,refs);
    }

}
