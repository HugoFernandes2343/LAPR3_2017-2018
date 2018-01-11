/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class BestPath {

    private List<RoadSection> sectionList;

    public BestPath() {
        this.sectionList = new LinkedList<>();
    }

    public BestPath(List<RoadSection> sectionList) {
        this.sectionList = sectionList;
    }

    /**
     * @return the sectionList
     */
    public List<RoadSection> getSectionList() {
        return sectionList;
    }

    /**
     * @param sectionList the sectionList to set
     */
    public void setSectionList(List<RoadSection> sectionList) {
        this.sectionList = sectionList;
    }

}
