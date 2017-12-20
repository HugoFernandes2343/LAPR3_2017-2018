package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
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
     * Creates an instance of the DataHandler recieving the URL,username and password
     *
     * @param jdbcUrl database URL.
     * @param username User's name.
     * @param password User's password.
     */
    public DataHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        connection = null;
        callStmt = null;
        rSet = null;
    }

    /**
     * Creates/Opens a connection to the database
     */
    public void openConnection() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        connection = ds.getConnection(username, password);
    }

    /**
     * Closes the objects "ResultSet", "CallableStatement" and "Connection", and
     * returns an error message if something does not work as intended. 
     * Else returns an empty string.
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
     * Exemplo de invocação de uma "stored function".
     *
     * Devolve o registo do marinheiro especificado existente na tabela 
     * "Sailors".
     *
     * @param sid o identificador do marinheiro.
     * @return o registo do sid especificado ou null, se esse registo não
     * existir.
     */
    public ResultSet getSailor(int sid) throws SQLException {

        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getSailor(sid NUMBER) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
         */
        callStmt = connection.prepareCall("{ ? = call getSailor(?) }");

        /** Regista o tipo de dados SQL para interpretar o resultado obtido.*/
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);

        /** Especifica o parâmetro de entrada da função "getSailor".*/
        callStmt.setInt(2, sid);

        /** Executa a invocação da função "getSailor".*/
        callStmt.execute();

        /** Guarda o cursor retornado num objeto "ResultSet".*/
        rSet = (ResultSet) callStmt.getObject(1);

        return rSet;
    }

    /**
     * Exemplo de invocação de uma "stored function".
     *
     * Devolve o conjunto de registos dos barcos da cor especificada existentes
     * na tabela "boats".
     *
     * @param color a cor dos barcos.
     * @return os registos dos barcos com a cor especificada.
     */
    public ResultSet getBoats(String color) throws SQLException {

        /* 
         * Objeto "callStmt" para invocar a função "boatsColor" armazenada na BD.
         *
         * FUNCTION boatsColor(color VARCHAR) RETURN pkgSailors.ref_cursor
         * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR
         * END pkgSailors
         */
        callStmt = connection.prepareCall("{ ? = call boatsColor(?) }");

        callStmt.registerOutParameter(1, OracleTypes.CURSOR);

        callStmt.setString(2, color);

        callStmt.execute();

        rSet = (ResultSet) callStmt.getObject(1);

        return rSet;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     *
     * Adiciona o marinheiro especificado à tabela "Sailors".
     *
     * @param sid o identificador do marinheiro.
     * @param sname o nome do marinheiro.
     * @param rating o "rating" do marinheiro.
     * @param age a idade do marinheiro.
     */
    public void addSailor(int sid, String sname, int rating, int age)
            throws SQLException {

        /* 
         *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
         *  na BD.
         *
         *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
         *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR
         *  END pkgSailors
         */
        callStmt = connection.prepareCall("{ call addSailor(?,?,?,?) }");

        callStmt.setInt(1, sid);
        callStmt.setString(2, sname);
        callStmt.setInt(3, rating);
        callStmt.setInt(4, age);

        callStmt.execute();

    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     *
     * Remove o marinheiro especificado da tabela "Sailors".
     *
     * @param sid o identificador do marinheiro a remover.
     */
    public void removeSailor(int sid) throws SQLException {

        /* 
         *  Objeto "callStmt" para invocar o procedimento "removeSailor" 
         *  armazenado na BD.
         *
         *  PROCEDURE removeSailor(sid NUMBER)
         *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR
         *  END pkgSailors
         */
        callStmt = connection.prepareCall("{ call removeSailor(?) }");

        callStmt.setInt(1, sid);

        callStmt.execute();

    }

}
