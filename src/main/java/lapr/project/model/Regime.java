package lapr.project.model;

import java.io.Serializable;

public class Regime implements Serializable {

    private static final long serialVersionUID = 603L;

    private int torque;
    private int rpm_low;
    private int rpm_high;
    private Double SFC;

    /**
     * Empty constructor
     */
    public Regime() {
    }

    /**
     * Full Constructor for the regime object
     *
     * @param torque value of torque to create the
     * @param rpmLow value of the low rpms
     * @param rpmHigh value of the high rpms
     * @param SFC value of the SFC
     */
    public Regime(int torque, int rpmLow, int rpmHigh, Double SFC) {
        this.torque = torque;
        this.rpm_low = rpmLow;
        this.rpm_high = rpmHigh;
        this.SFC = SFC;
    }

    /**
     * @return the torque torque of the regime
     */
    public int getTorque() {
        return torque;
    }

    /**
     * @return the rpm_low low rpm value of the regime
     */
    public int getRpmLow() {
        return rpm_low;
    }

    /**
     * @return the rpm_high high rpm value of the regime
     */
    public int getRpmHigh() {
        return rpm_high;
    }

    /**
     * @return the SFC SFC value of the regime
     */
    public Double getSFC() {
        return SFC;
    }

    /**
     * Set method for the torque variable
     *
     * @param torque value to be set as torque
     */
    public void setTorque(int torque) {
        this.torque = torque;
    }

    /**
     * Set method for the rpm low variable
     *
     * @param rpmLow value to be set as low rpm
     */
    public void setRpmLow(int rpmLow) {
        this.rpm_low = rpmLow;
    }

    /**
     * Set method for the rpm high variable
     *
     * @param rpmHigh value to be set as high rpm
     */
    public void setRpmHigh(int rpmHigh) {
        this.rpm_high = rpmHigh;
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
        hash = 89 * hash + this.torque;
        hash = 89 * hash + this.rpm_low;
        hash = 89 * hash + this.rpm_high;
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
        if (this.torque != other.torque) {
            return false;
        }
        if (this.rpm_low != other.rpm_low) {
            return false;
        }
        if (this.rpm_high != other.rpm_high) {
            return false;
        }
        return this.SFC.equals(other.SFC);

    }

}
