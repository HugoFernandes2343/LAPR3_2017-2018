package lapr.project.model;

public class MostEfficientPathInEnergySavingModeAnalysis extends NetworkAnalysis {
    
    /**
     * Constant that keeps a description of this type of analysis
     */
    private static final String TYPE = "Most Efficient Path In Energy Saving Mode";

    /**
     * Atribute that keeps the chosen acceleration for the process of acelerating.
     */
    private double aceleratingAcceleration;
    
    /**
     * Atribute that keeps the chosen acceleration for the process of braking.
     */
    private double brakingAcceleration;
    
    /**
     * Constructor of MostEfficientPathInEnergySavingModeAnalysis
     * @param beginNode
     * @param endNode
     * @param vehicle 
     * @param name 
     */
    public MostEfficientPathInEnergySavingModeAnalysis(Node beginNode, Node endNode, Vehicle vehicle, String name) {
        super(beginNode, endNode, vehicle, name, TYPE);
    }

    public MostEfficientPathInEnergySavingModeAnalysis() {
        super(TYPE);
    }
    
    /**
     * @return Acceleration
     */
    public double getAceleratingAcceleration() {
        return aceleratingAcceleration;
    }
    
    /**
     * @return braking accelaration
     */
    public double getBrakingAcceleration() {
        return brakingAcceleration;
    }
    
    /**
     * Setter of aceleratingAcceleration 
     * @param aceleratingAcceleration -  car acceleration
     */
    public void setAceleratingAcceleration(double aceleratingAcceleration) {
        this.aceleratingAcceleration = aceleratingAcceleration;
    }
    
    /**
     * Setter of Car Braking Acceleration
     * @param brakingAcceleration - Car Braking Acceleration
     */
    public void setBrakingAcceleration(double brakingAcceleration) {
        this.brakingAcceleration = brakingAcceleration;
    }

}
