/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Arrays;

/**
 *
 * @author Hugo
 */
class TollFare {
    private static final long serialVersionUID = 101L;
    
    private String[][] toll_fare;
    
    public TollFare(String[][] toll_fare) {
        this.toll_fare = toll_fare;
    }

    /**
     * @param toll_fare the toll_fare to set
     */
    public void setToll_fare(String[][] toll_fare) {
        this.toll_fare = toll_fare;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Arrays.deepHashCode(this.toll_fare);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the tollFare
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TollFare other = (TollFare) obj;

        return !Arrays.deepEquals(this.toll_fare, other.toll_fare);
    }
    
}
