/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author filip
 */
public class TheoreticalMostEnergyEfficientAnalysis extends NetworkAnalysis {

    /**
     * Constant that keeps a description of this type of analysis
     */
    private static final String TYPE = "Theoretical Most Energy Efficient Path";

    /**
     * Atribute that keeps the chosen acceleration for the process of acelerating.
     */
    private double aceleratingAcceleration;
    
    /**
     * Atribute that keeps the chosen acceleration for the process of braking.
     */
    private double brakingAcceleration;
    
    /**
     * Empty constructor of this analysis
     */
    public TheoreticalMostEnergyEfficientAnalysis() {
        super(TYPE);
    }

    public TheoreticalMostEnergyEfficientAnalysis(Node beginNode, Node endNode, Vehicle vehicle, String name) {
        super(beginNode, endNode, vehicle, name, TYPE);
    }

    /**
     * Setter of the acelerating acceleration.
     * @param aceleratingAcceleration the aceleratingAcceleration to set
     */
    public void setAceleratingAcceleration(double aceleratingAcceleration) {
        this.aceleratingAcceleration = aceleratingAcceleration;
    }

    /**
     * Setter of the braking acceleration.
     * @param brakingAcceleration the brakingAcceleration to set
     */
    public void setBrakingAcceleration(double brakingAcceleration) {
        this.brakingAcceleration = brakingAcceleration;
    }

    /**
     * Getter of the acelerating acceleration
     * @return the aceleratingAcceleration
     */
    public double getAceleratingAcceleration() {
        return aceleratingAcceleration;
    }

    /**
     * Getter of the braking acceleration.
     * @return the brakingAcceleration
     */
    public double getBrakingAcceleration() {
        return brakingAcceleration;
    }

    
}
