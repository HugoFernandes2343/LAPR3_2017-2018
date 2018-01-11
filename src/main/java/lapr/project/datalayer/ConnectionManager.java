package lapr.project.datalayer;

import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Handles the connection to the database
 */
public abstract class ConnectionManager {

    /**
     * Data base url
     */
    public static final String JDBCURL = "jdbc:oracle:thin://LAPR3_G42@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";

    /**
     * Data base USERNAME
     */
    public static final String USERNAME = "LAPR3_G42";

    /**
     * Data base password
     */
    public static final String PASS = "katchow";
    
    /**
     * Constructor
     */
    public ConnectionManager() {
        
    }

    /**
     * Opens a connection to the database. Auto commit set to false as default
     *
     * @return The created connection
     * @throws SQLException If the connection was not open
     */
    public static Connection openConnection() throws SQLException {
        Connection connection = null;
        try {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(JDBCURL);
            connection = ds.getConnection(USERNAME, PASS);

        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error registring the Driver : ", ex);
        }
        connection.setAutoCommit(false);

        return connection;
    }

    /**
     * Method that adds data created/imported by the user. Should only be used
     * in the "Add X" controllers/user story
     *
     * @param con Connection
     * @param data Data to be added to the database
     * @throws SQLException If it was not possible to updated data
     */
    public abstract void addData(Connection con, DatabaseExchangable data) throws SQLException;

    /**
     * Method that reads data from the database
     *
     * @param con Connection to the database
     * @param library Library library where the data will be added
     * @throws SQLException If the operation was not successful
     */
    public abstract void readData(Connection con, DatabaseExchangable placeToAdd, Object[] refs) throws SQLException;

    /**
     * Commits the changes made to the database
     *
     * @param con Connections
     * @throws SQLException If the operation was not successful
     */
    public static void commitChanges(Connection con) throws SQLException {
        try {
            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new SQLException("Impossible to commit the changes!");
        }
    }

    /**
     * Commits the changes made to the database
     *
     * @param con Connections
     * @throws SQLException If the operation was not successful
     */
    public static void rollBackChanges(Connection con) throws SQLException {
        try {
            con.rollback();
            con.close();
        } catch (SQLException e) {
            throw new SQLException("Impossible to commit the changes!");
        }
    }

    /**
     * Closes a connection to the database.
     *
     * @param con Connection to close
     * @throws SQLException If the connection was not open
     */
    public static void closeConnection(Connection con) throws SQLException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new SQLException("Impossible to close connection");
        }
    }

}
