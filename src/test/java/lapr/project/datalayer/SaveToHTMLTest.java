/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import lapr.project.model.Network;
import lapr.project.model.Project;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.ShortestTravellTimeAnalysis;
import lapr.project.utils.HTMLExporter;
import org.junit.Test;

/**
 *
 * @author Utilizador
 */
public class SaveToHTMLTest {

    /**
     * File location
     */
    private File fileLocation;

    private Project test;

    private SaveToHTML save;

    private void setUp() {
        test = new Project();
        test.setName("ProjectName");
        test.setDescription("ProjectDescription");

        Network netTest = new Network();
        netTest.setId("1");
        netTest.setDescription("NetWorkDescritpion");

        Segment segTest = new Segment();
        segTest.setId("1");
        segTest.setLength("100");
        segTest.setFinalHeight(100);
        segTest.setInitHeight(20);
        segTest.setMaxVelocity("60");
        segTest.setMinVelocity("20");
        segTest.setWindDirection(3);
        segTest.setWindSpeed("30");

        RoadSection sectionListTest = new RoadSection("BeginTest", "EndTest", "TestID", "NORTH", new LinkedList<>());
        sectionListTest.addSegment(segTest);
        netTest.addRoadSection(sectionListTest);
        test.setNetwork(netTest);
        
        ShortestTravellTimeAnalysis analysis = new ShortestTravellTimeAnalysis();
        test.addNetworkAnalysis(analysis);
        save = new SaveToHTML();
        save.setProject(test);
    }

    public SaveToHTMLTest() {
        setUp();
    }

//    @Test
//    public void testStartHTML() throws IOException {
//        HTMLExporter exp = new HTMLExporter();
//        save.startHTML(exp.getHTML());
//        save.output(exp, new File("htmlTest.html"));
//    }

}
