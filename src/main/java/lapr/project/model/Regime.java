package lapr.project.model;

import java.io.Serializable;

public class Regime implements Serializable {

    private static final long serialVersionUID = 603L;

    private int torqueHigh;
    private int torqueLow;
    private int rpmLow;
    private int rpmHigh;
    private Double SFC;

    /**
     * Empty constructor
     */
    public Regime() {
    }

    /**
     * Full Constructor for the regime object
     *
     * @param torque value of torqueHigh to create the
     * @param rpmLow value of the low rpms
     * @param rpmHigh value of the high rpms
     * @param SFC value of the SFC
     */
    public Regime(int torqueHigh, int torqueLow, int rpmLow, int rpmHigh, Double SFC) {
        this.torqueHigh = torqueHigh;
        this.torqueLow=torqueLow;
        this.rpmLow = rpmLow;
        this.rpmHigh = rpmHigh;
        this.SFC = SFC;
    }

    /**
     * @return the torqueHigh torqueHigh of the regime
     */
    public int getTorqueHigh() {
        return torqueHigh;
    }

    /**
     * @return the rpmLow low rpm value of the regime
     */
    public int getRpmLow() {
        return rpmLow;
    }

    /**
     * @return the rpmHigh high rpm value of the regime
     */
    public int getRpmHigh() {
        return rpmHigh;
    }

    /**
     * @return the SFC SFC value of the regime
     */
    public Double getSFC() {
        return SFC;
    }

    /**
     * Set method for the torqueHigh variable
     *
     * @param torqueHigh value to be set as torqueHigh
     */
    public void setTorqueHigh(int torqueHigh) {
        this.torqueHigh = torqueHigh;
    }
    
    /**
     * Set method for the torqueLow variable
     *
     * @param torqueLow value to be set as torqueLow
     */
    public void setTorqueLow(int torqueLow) {
        this.torqueLow = torqueLow;
    }
    
    

    /**
     * Set method for the rpm low variable
     *
     * @param rpmLow value to be set as low rpm
     */
    public void setRpmLow(int rpmLow) {
        this.rpmLow = rpmLow;
    }

    /**
     * Set method for the rpm high variable
     *
     * @param rpmHigh value to be set as high rpm
     */
    public void setRpmHigh(int rpmHigh) {
        this.rpmHigh = rpmHigh;
    }

    /**
     * Set method for the Sfc variable
     *
     * @param SFC value to be set as sfc
     */
    public void setSFC(Double SFC) {
        this.SFC = SFC;
    }

    /**
     * HashCode method for the regime type objects
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.torqueHigh;
        hash = 89 * hash + this.rpmLow;
        hash = 89 * hash + this.rpmHigh;
        return hash;
    }

    /**
     * Equals method for the regime type objects
     *
     * @param obj the object to compare to the regime
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Regime other = (Regime) obj;
        if (this.torqueHigh != other.torqueHigh) {
            return false;
        }
        if (this.rpmLow != other.rpmLow) {
            return false;
        }
        if (this.rpmHigh != other.rpmHigh) {
            return false;
        }
        return this.SFC.equals(other.SFC);

    }

}
