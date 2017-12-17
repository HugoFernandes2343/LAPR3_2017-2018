/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import lapr.project.model.Network;
import lapr.project.model.Project;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.utils.HTMLExporter;
import org.junit.BeforeClass;
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
        segTest.setFinal_height(100);
        segTest.setInit_height(20);
        segTest.setMax_velocity("60");
        segTest.setMin_velocity("20");
        segTest.setWind_direction(3);
        segTest.setWind_speed("30");

        RoadSection sectionListTest = new RoadSection("BeginTest", "EndTest", "TestID", "NORTH", new LinkedList<>());
        sectionListTest.addSegment(segTest);
        netTest.addRoadSection(sectionListTest);
        test.setNetwork(netTest);
    }

    public SaveToHTMLTest() {
        setUp();
        save = new SaveToHTML();
        save.setProject(test);
    }

    @Test
    public void testStartHTML() throws IOException {
        HTMLExporter exp = new HTMLExporter();
        save.startHTML(exp);
        save.output(exp, new File("htmlTest.html"));
    }

}
