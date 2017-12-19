/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
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
    private List<Segment> segment_list = new LinkedList<>();

    /**
     * Full constructor of the RoadSection object
     * @param begin id of the begining node 
     * @param end id of the end node
     * @param roadId id of the road to which the section belongs
     * @param direction direction that the section has
     * @param segments segments that the section posesses
     */
    public RoadSection(String begin, String end, String roadId, String direction, List<Segment> segments) {
        this.begin = begin;
        this.end = end;
        this.road_id = roadId;
        this.direction = direction;
        this.segment_list = segments;
    }

    /**
     * Empty constructor of the RoadSection object
     */
    public RoadSection() {
        this.segment_list = new LinkedList<>();
    }

    /** 
     * @return the begin id of the beginning node
     */
    public String getBegin() {
        return begin;
    }

    /**
     * @return the end id of the ending node 
     */
    public String getEnd() {
        return end;
    }

    /**
     * @return the road_id id of the road that contains the section
     */
    public String getRoadId() {
        return road_id;
    }

    /**
     * @return the direction direction of the trafic in this section
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @return the segment_list list of segments that are contained in the section
     */
    public List<Segment> getSegmentList() {
        return segment_list;
    }

    /**
     *Set method of the beginning node id
     * 
     * @param begin id to be set as the begin node id
     */
    public void setBegin(String begin) {
        this.begin = begin;
    }

    /**
     *Set method of the ending node id
     * 
     * @param end id to best as the end node id
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     *Set method of the road id
     * 
     * @param roadId id to be set as the road id 
     */
    public void setRoadId(String roadId) {
        this.road_id = roadId;
    }

    /**
     *Set method of the direction
     * 
     * @param direction direction of trafic to be set as direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     *Set method of the segment list
     * 
     * @param segmentList list to be set as list of segments on this section
     */
    public void setSegmentList(List<Segment> segmentList) {
        this.segment_list = segmentList;
    }

    /**
     * Method to add a segment to the segment list 
     * 
     * @param seg segment to be added
     */
    public void addSegment(Segment seg) {
        if (!this.segment_list.contains(seg)) {
            this.segment_list.add(seg);
        }
    }

    /**
     * hashCode method for the roadSection objects
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
     * Equals method for the roadSection objects
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
