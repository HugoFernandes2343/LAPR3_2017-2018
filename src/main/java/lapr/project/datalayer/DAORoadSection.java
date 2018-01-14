package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.RoadSection;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAORoadSection extends DAOManager {

    /**
     * Gets the hihgest If from the DB
     */
    private static final String HIGHEST_ROADSECTION_ID_IN_DB = "{call proc_get_number_road_section(?)}";

    /**
     * Name of the function in the database that adds RoadSections
     */
    private static final String ADD_ROADSECTION_PROCEDURE = "{call proc_insert_road_section(?,?,?,?,?)}";

    /**
     * Name of the function in the database that gets RoadSections
     */
    private static final String GET_ROADSECTION_PROCEDURE = "{call proc_get_road_section(?,?)}";

    public DAORoadSection() throws SQLException {
        super(ADD_ROADSECTION_PROCEDURE, GET_ROADSECTION_PROCEDURE);
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        RoadSection rds = (RoadSection) data;

        cs.setInt(1, rds.getId());
        cs.setString(2, rds.getDirection());
        cs.setString(3, rds.getBegin());
        cs.setString(4, rds.getEnd());
        cs.setString(5, rds.getRoadId());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> sec = placeToAdd.getDBData();
        String roadId = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, roadId);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String id = rs.getString("ID");
                String dir = rs.getString("DIRECTION");
                String beg = rs.getString("BEGIN_NODE");
                String end = rs.getString("END_NODE");

                RoadSection roadSection = new RoadSection();
                roadSection.setId(Integer.parseInt(id));
                roadSection.setBegin(beg);
                roadSection.setDirection(dir);
                roadSection.setEnd(end);
                roadSection.setRoadId(roadId);

                sec.add(roadSection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORoadSection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }

    public int getHighestIDinRoadSection(Connection con) throws SQLException {
        int index = 0;
        ResultSet rs = null;
        try (CallableStatement cs = con.prepareCall(HIGHEST_ROADSECTION_ID_IN_DB)) {
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                index = rs.getInt("nr_max");
            }
            return index;
        } catch (NullPointerException ex) {
            Logger.getLogger(DAORoadSection.class.getName()).log(Level.SEVERE, "No Data in DB", ex);
            index = 0;
            return index;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
