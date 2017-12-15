/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Regime implements Serializable{

    private static final long serialVersionUID = 603L;
    
    private int torque;
    private int rpm_low;
    private int rpm_high;
    private int SFC;

    /**
     *
     * @param torque
     * @param rpm_low
     * @param rpm_high
     * @param SFC
     */
    public Regime(int torque, int rpm_low, int rpm_high, int SFC) {
        this.torque = torque;
        this.rpm_low = rpm_low;
        this.rpm_high = rpm_high;
        this.SFC = SFC;
    }

    /**
     * @return the torque
     */
    public int getTorque() {
        return torque;
    }

    /**
     * @return the rpm_low
     */
    public int getRpm_low() {
        return rpm_low;
    }

    /**
     * @return the rpm_high
     */
    public int getRpm_high() {
        return rpm_high;
    }

    /**
     * @return the SFC
     */
    public int getSFC() {
        return SFC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.torque;
        hash = 89 * hash + this.rpm_low;
        hash = 89 * hash + this.rpm_high;
        hash = 89 * hash + this.SFC;
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the regime
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
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
        return this.SFC == other.SFC;

    }

}
