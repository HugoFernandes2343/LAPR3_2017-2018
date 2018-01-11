package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.DatabaseExchangable;

/**
 * Super class that defines the behavior of updating data in the database
 */
public abstract class DAOManager extends ConnectionManager {

    /**
     * Name of the function in the database that adds data
     */
    private final String addProcedure;

    /**
     * Name of the function in the database that adds data
     */
    private final String readProcedure;

    /**
     * Constructor
     *
     * @param addProcedure Name of the add procedure
     * @param readProcedure Name of read procedure
     * @throws SQLException If connection was not open
     */
    public DAOManager(String addProcedure, String readProcedure) throws SQLException {
        this.addProcedure = addProcedure;
        this.readProcedure = readProcedure;
    }

    /**
     * Sets the arguments of the add function
     *
     * @param cs Callable statement where the arguments will be defined
     * @param data Data to add
     * @throws SQLException If the operation was not successful
     */
    protected abstract void add(CallableStatement cs, DatabaseExchangable data) throws SQLException;

    /**
     * Reads the library in the database
     *
     * @param cs CallableStatement
     * @param placer Library where the data will be placed
     * @throws SQLException If the operation was not successful
     */
    protected abstract void read(CallableStatement stmt,DatabaseExchangable placeToAdd, Object[] references) throws SQLException;

    protected void execute(CallableStatement cs) throws SQLException {
        cs.execute();
        cs.close();
    }

    /**
     * General method that defines the addition of data. Call abstract method
     * add that will be implemented by the other DAO classes
     *
     * @param con Connection
     * @param data Data to add
     * @throws SQLException If the operation was not successful
     */
    @Override
    public void addData(Connection con, DatabaseExchangable data) throws SQLException {
        try (CallableStatement cs = con.prepareCall(addProcedure)) {
            add(cs, data);
            execute(cs);
        }
    }

    @Override
    public void readData(Connection con,DatabaseExchangable placeToAdd, Object[] refs) throws SQLException {
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(readProcedure);
            read(cs,placeToAdd, refs);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DAOManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }
}
