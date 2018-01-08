package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.Regime;
import lapr.project.model.Throttle;
import lapr.project.utils.DatabaseExchangable;

public class DAORegime extends DAOManager {

    /**
     * Name of the function in the database that adds Regimes
     */
    private static final String ADD_REGIME_PROCEDURE = "{call proc_insert_regime(?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets Regimes
     */
    private static final String GET_REGIME_PROCEDURE = "{call proc_getProject(?)}";

    private Throttle t;

    public DAORegime(Throttle t) throws SQLException {
        super(ADD_REGIME_PROCEDURE, GET_REGIME_PROCEDURE);
        this.t = t;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Regime regime = (Regime) data;

        cs.setDouble(1, regime.getTorqueHigh());
        cs.setDouble(2, regime.getTorqueLow());
        cs.setDouble(3, regime.getRpmLow());
        cs.setDouble(4, regime.getRpmHigh());
        cs.setDouble(5, regime.getSfc());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
