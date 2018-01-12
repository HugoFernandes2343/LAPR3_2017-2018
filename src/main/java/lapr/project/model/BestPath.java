/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author Hugo
 */
public class BestPath extends DatabaseExchangable {

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

    @Override
    public List<DatabaseExchangable> getDBData() {
        List<DatabaseExchangable> temp = new LinkedList<>();
        temp.addAll(this.sectionList);
        return temp;
    }

}
