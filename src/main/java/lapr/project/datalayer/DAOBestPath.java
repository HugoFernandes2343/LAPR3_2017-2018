/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.BestPath;
import lapr.project.model.Network;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.RoadSection;
import lapr.project.utils.DatabaseExchangable;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author hugod
 */
public class DAOBestPath extends DAOManager {

    /**
     * Name of the function in the database that adds RoadSections
     */
    private static final String ADD_BEST_PATH_PROCEDURE = "{call proc_insert_best_path(?,?)}";

    /**
     * Name of the function in the database that gets RoadSections
     */
    private static final String GET_BEST_PATH_PROCEDURE = "{call proc_get_best_path(?,?)}";

    private NetworkAnalysis netAnal;

    public DAOBestPath(NetworkAnalysis netAnal) throws SQLException {
        super(ADD_BEST_PATH_PROCEDURE, GET_BEST_PATH_PROCEDURE);
        this.netAnal = netAnal;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        RoadSection roadSection = (RoadSection) data;
        cs.setInt(1, netAnal.getId());
        cs.setString(2, String.valueOf(roadSection.getId()));
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        List<DatabaseExchangable> bpList = placeToAdd.getDBData();
        int naId = (int) references[0];
        Network net = (Network) references[1];
        ResultSet rs = null;

        try {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setInt(2, naId);
            stmt.executeUpdate();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String id = rs.getString("ROAD_SECTION_ID");

                List<RoadSection> allSection = net.getSectionList();
                List<RoadSection> path = new LinkedList<>();
                for (int i = 0; i < allSection.size(); i++) {
                    RoadSection rsec = allSection.get(i);
                    if (rsec.getId() == Integer.parseInt(id)) {
                        path.add(rsec);
                    }
                }

                BestPath bp = new BestPath();
                bp.setSectionList(path);
                bpList.add(bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAORoadSection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}
