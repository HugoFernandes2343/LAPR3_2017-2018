package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lapr.project.model.RoadSection;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.OracleTypes;

public class DAORoadSection extends DAOManager {

    /**
     * Name of the function in the database that adds RoadSections
     */
    private static final String ADD_ROADSECTION_PROCEDURE = "{call proc_insert_road_section(?,?,?,?)}";

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

        cs.setString(1, rds.getDirection());
        cs.setString(2, rds.getBegin());
        cs.setString(3, rds.getEnd());
        cs.setString(4, rds.getRoadId());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> sec = placeToAdd.getDBData();
        String road_id = (String) references[0];
        ResultSet rs = null;

        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        stmt.setString(2, road_id);
        stmt.executeUpdate();
        rs = (ResultSet) stmt.getObject(1);
        
        while(rs.next()){
            String dir = rs.getString(("DIRECTION"));
            String beg = rs.getString(("BEGIN_NODE"));
            String end = rs.getString(("END_NODE"));
            
            RoadSection roadSection = new RoadSection();
            roadSection.setBegin(beg);
            roadSection.setDirection(dir);
            roadSection.setEnd(end);
            roadSection.setRoadId(road_id);
            
            sec.add(roadSection);
        }
    }

}
