/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Hugo
 */
public class ShortestTravellTimeAnalysis extends NetworkAnalysis {

    private static final String TYPE = "Shortest Travel Time";

    public ShortestTravellTimeAnalysis(Node beginNode, Node endNode, Vehicle vehicle, String name) {
        super(beginNode, endNode, vehicle, name, TYPE);
    }

    public ShortestTravellTimeAnalysis(){
        super(TYPE);
    }
    
}
