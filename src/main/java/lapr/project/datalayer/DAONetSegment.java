/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Utilizador
 */
public class DAONetSegment extends DAOManager {

    private RoadSection roadSectionSegment;
    private NetworkAnalysis netAnal;

    /**
     * Name of the function in the database that adds RoadSections
     */
    private static final String ADD_BEST_PATH_PROCEDURE = "{call proc_insert_network_analysis_segment(?,?,?)}";

    /**
     * Name of the function in the database that gets RoadSections
     */
    private static final String GET_BEST_PATH_PROCEDURE = "{call proc_get_network_analysis_segment(?,?)}";

    public DAONetSegment(RoadSection nodeBestPath,NetworkAnalysis netAnal) throws SQLException {
        super(ADD_BEST_PATH_PROCEDURE, GET_BEST_PATH_PROCEDURE);
        this.roadSectionSegment = nodeBestPath;
        this.netAnal=netAnal;
    }

    @Override
    protected void add(CallableStatement cs, DatabaseExchangable data) throws SQLException {
        Segment seg = (Segment) data;
        
        cs.setInt(1, netAnal.getId());
        cs.setString(2, String.valueOf(roadSectionSegment.getId()));
        cs.setString(3, seg.getId());
    }

    @Override
    protected void read(CallableStatement stmt, DatabaseExchangable placeToAdd, Object[] references) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
