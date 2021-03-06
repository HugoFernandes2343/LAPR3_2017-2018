package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAOSegment extends DAOManager {

    /**
     * Name of the function in the database that adds Road Segments
     */
    private static final String ADD_SEGMENT_PROCEDURE = "{call proc_insert_segment(?,?,?,?,?,?,?,?,?)}";
    /**
     * Name of the function in the database that gets Road Segments
     */
    private static final String GET_SEGMENT_PROCEDURE = "{call proc_get_segment(?,?)}";
    private static int i;
    private RoadSection rs;

    public DAOSegment(RoadSection rs) throws SQLException {
        super(ADD_SEGMENT_PROCEDURE, GET_SEGMENT_PROCEDURE);
        this.rs = rs;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Segment seg = (Segment) data;

        if (seg.getId() != null && rs.getId()>=0) {
            cs.setString(1, seg.getId());
            cs.setFloat(2, (float) seg.getInitHeight());
            cs.setFloat(3, (float) seg.getFinalHeight());
            cs.setString(4, seg.getLength());
            cs.setFloat(5, (float) seg.getWindDirection());
            cs.setString(6, seg.getWindSpeed());
            cs.setString(7, seg.getMaxVelocity());
            cs.setString(8, seg.getMinVelocity());
            cs.setInt(9, rs.getId());
        } else {
            cs.setString(1, "TEMP");
            cs.setFloat(2, (float) seg.getInitHeight());
            cs.setFloat(3, (float) seg.getFinalHeight());
            cs.setString(4, seg.getLength());
            cs.setFloat(5, (float) seg.getWindDirection());
            cs.setString(6, seg.getWindSpeed());
            cs.setString(7, seg.getMaxVelocity());
            cs.setString(8, seg.getMinVelocity());
            cs.setInt(9, i);
            i++;
        }

    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> sList = placeToAdd.getDBData();
        String rsID = (String) references[0];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, rsID);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String segID = rs.getString(("ID"));
                double initHeight = rs.getDouble("INIT_HEIGTH");
                double finHeight = rs.getDouble("FINAL_HEIGTH");
                String length = rs.getString("LENGTH");
                double windDirect = rs.getDouble("WIND_DIRECTION");
                String windSpeed = rs.getString("WIND_SPEED");
                String maxVelocity = rs.getString("MAX_VELOCITY");
                String minVelocity = rs.getString("MIN_VELOCITY");

                Segment seg = new Segment(segID, initHeight, finHeight, length, windDirect, windSpeed, maxVelocity, minVelocity);
                sList.add(seg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSegment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
