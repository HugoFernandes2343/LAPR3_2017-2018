/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author
 */
public class RoadSection implements Serializable {
    
    private static final long serialVersionUID = 600L;

    private String begin;
    private String end;
    private String road_id;
    private String direction;
    private LinkedList<Segment> segment_list;

    public RoadSection(String beginningJunction, String endingJunction, String road_id, String direction, LinkedList<Segment> segments) {
        this.begin = beginningJunction;
        this.end = endingJunction;
        this.road_id = road_id;
        this.direction = direction;
        this.segment_list = segments;
    }

    /**
     * @return the begin
     */
    public String getBegin() {
        return begin;
    }

    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @return the road_id
     */
    public String getRoad_id() {
        return road_id;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @return the segment_list
     */
    public LinkedList<Segment> getSegment_list() {
        return segment_list;
    }

    /**
     *
     * @return the integer representation of the object RoadSection
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.begin);
        hash = 83 * hash + Objects.hashCode(this.end);
        return hash;
    }

    /**
     *
     * @param obj the object to compare to the RoadSection
     * @return the result of the comparisons made. True if the objects are the
     * same, otherwise, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        RoadSection other = (RoadSection) obj;
        if (!this.begin.equals(other.begin)) {
            return false;
        }
        return this.end.equals(other.end);
    }
}
