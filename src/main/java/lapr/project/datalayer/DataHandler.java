package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.*;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Handles data between this Project and the database
 */
public class DataHandler {

    /**
     * Database URL
     */
    private String jdbcUrl;

    /**
     * User's name
     */
    private String username;

    /**
     * User's password
     */
    private String password;

    /**
     * Object connection that helps to create a statment to the database
     */
    private Connection connection;

    /**
     * "Stored Procedures" are called using this object
     */
    private CallableStatement callStmt;

    /**
     * Return values from the "Stored Procedures" are stored in this object
     */
    private ResultSet rSet;

    /**
     * Creates an instance of the DataHandler recieving the URL,username and
     * password
     *
     * @param jdbcUrl database URL.
     * @param username User's name.
     * @param password User's password.
     */
    public DataHandler(/*String jdbcUrl, String username, String password*/) {
        registerDriver();
        this.jdbcUrl = "jdbc:oracle:thin://LAPR3_G42@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        this.username = "LAPR3_G42";
        this.password = getEncryptedPassword();
        connection = null;
        callStmt = null;
        rSet = null;
        /**
         * Regiter no fo inicio e fim ou sempre que se quer fazer alterações?
         */
        openConnection();
    }

    /**
     * Placeholder until credential autho is implemented
     *
     * @return
     */
    private static String getEncryptedPassword() {
        String string = "icaproanof";
        String n1 = string.substring(0, 9);
        String n2 = string.substring(9);
        string = n2 + n1;
        return string;
    }

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public /*static*/ void registerDriver() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error registring the Driver", ex);
        }
    }

    /**
     * Creates/Opens a connection to the database
     */
    public void openConnection() {
        try {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            connection = ds.getConnection(username, password);

        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error registring the Driver : ", ex);
        }
    }

    /**
     * Closes the "ResultSet" object specifically. Returns a empty string if
     * everything goes to plan.
     *
     * @return If it works properly then an empty string is returned, if not
     * then an exception is thrown
     */
    public String closeResultSet() {
        StringBuilder message = new StringBuilder("");

        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            rSet = null;
        }
        return message.toString();
    }

    public String closeStatement() {

        StringBuilder message = new StringBuilder("");

        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            callStmt = null;
        }
        return message.toString();
    }

    /**
     * Closes the objects "ResultSet", "CallableStatement" and "Connection", and
     * returns an error message if something does not work as intended. Else
     * returns an empty string.
     */
    public String closeAll() {

        StringBuilder message = new StringBuilder("");

        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            rSet = null;
        }

        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            callStmt = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = null;
        }
        return message.toString();
    }

    /**
     * Prototype method to allow the creation of a statement to query the
     * database in order to return a list of all User registered
     *
     * @param list empty list to fill
     * @return filled list
     * @throws SQLException
     */
    public UserList getUserList(UserList list) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String query = "select * "
                + "FROM "
                + "\"USER\"";
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userName = rs.getString("USERNAME");//Change as in DB
                int charKey = rs.getInt("CHARKEY");
                String name = rs.getString("NAME");
                String password = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                User user = new User(userName, charKey, name, password, email);
                list.getUserList().add(user);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

//        /**
//     * Prototype method to allow the creation of a statement to query the 
//     * database in order to return a list of all Projects
//     * @param list empty list to fill
//     * @return filled list
//     * @throws SQLException 
//     */
//    public ProjectList getProjectList(ProjectList list) throws SQLException {
//        Statement stmt = null;
//        String query = "select * "
//                + "FROM "
//                + "PROJECT";
//        try {
//            stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String name = rs.getString("ProjectName");//Change as in DB
//                String description = rs.getString("Description");
//                String email = rs.getString("EMAIL");
//                Project p = new Project();
//                p.setName(name);
//                p.setDescription(description);
//                //for the rest of the list that comprise the charKeyProject maybe do a procedure or do all here??
//                list.getUserList().add(user);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//        }
//        return list;
//    }
    /**
     * Prototype method to allow the creation of a statement to query the
     * database in order to return a list of all Vehicles from a selected
     * Project
     *
     * @param list empty list to fill
     * @return filled list
     * @throws SQLException
     */
    public VehicleList getVehicleList(VehicleList list, Project p) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String query = "select * "
                + "FROM "
                + "VEHICLE "
                + "Where "
                + "ProjectName = " + p.getName();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                String type = rs.getString("TYPE");
                String motorization = rs.getString("MOTORIZATION");
                String mass = String.valueOf(rs.getDouble("MASS"));
                String load = String.valueOf(rs.getDouble("LOAD"));
                double drag = rs.getDouble("DRAG");
                double rrc = rs.getDouble("RRC");
                double wheelSize = rs.getDouble("WHEELSIZE");
                String fuel = rs.getString("FUEL");
                int tollClass = rs.getInt("TOLLCLASS");
                double frontalArea = rs.getDouble("FRONTALAREA");
                /* Create the rest 2 objects */
                Vehicle vehicle = new Vehicle(name, description, type, tollClass, motorization, fuel, mass, load, drag, frontalArea, rrc, wheelSize, null, null);
                Energy energy = getEnergyByVehicle(vehicle);
                vehicle.setEnergy(energy);
                list.getVehicleList().add(vehicle);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    /**
     * Prototype method to allow the creation of a statement to query the
     * database in order to return the Energy factor of one vehicle
     *
     * @throws SQLException
     */
    public Energy getEnergyByVehicle(Vehicle vehicle) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * "
                + "FROM "
                + "ENERGY "
                + "WHERE "
                + "VEHICLE = " + vehicle.getName();
        Energy energy = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int minRPM = rs.getInt("min_rpm");
                int maxRPM = rs.getInt("max_rpm");
                Double finalDriveRatio = rs.getDouble("FINAL_DRIVE_RATIO");
                double err = rs.getDouble("ENERGY_REGENERATION_RATIO");
                /*Get the Gears*/
                List<Gear> gearList = new LinkedList<>();
                getGearsByVehicle(gearList, vehicle);
                /*get Throttle*/
                List<Throttle> throttleList = new LinkedList<>();
                getThrottleByVehicle(throttleList, vehicle);
                /**/
                energy = new Energy(minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
                energy.setErr(err);
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return energy;
    }

    private void getGearsByVehicle(List<Gear> gearList, Vehicle vehicle) {

    }

    private void getThrottleByVehicle(List<Throttle> throttleList, Vehicle vehicle) {

    }
}
