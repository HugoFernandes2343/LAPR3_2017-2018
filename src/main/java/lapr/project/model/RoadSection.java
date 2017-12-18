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

    public RoadSection(String begin, String end, String road_id, String direction, LinkedList<Segment> segments) {
        this.begin = begin;
        this.end = end;
        this.road_id = road_id;
        this.direction = direction;
        this.segment_list = segments;
    }

    public RoadSection() {
        this.segment_list = new LinkedList<>();
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

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setRoad_id(String road_id) {
        this.road_id = road_id;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setSegment_list(LinkedList<Segment> segment_list) {
        this.segment_list = segment_list;
    }

    public void addSegment(Segment seg) {
        if (!this.segment_list.contains(seg)) {
            this.segment_list.add(seg);
        }
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
        if (obj == null) {
            return false;
        }
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
