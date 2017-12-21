/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugod
 */
public class ShortestTravellTimeAnalysisTest {

    private ShortestTravellTimeAnalysis instance;
    private ShortestTravellTimeAnalysis instanceEmpty;

    public ShortestTravellTimeAnalysisTest() {
        Node beginNode = new Node();
        Node endNode = new Node();
        Vehicle vehicle = new Vehicle();
        String name = "test";
        instance = new ShortestTravellTimeAnalysis(beginNode, endNode, vehicle, name);
        instanceEmpty = new ShortestTravellTimeAnalysis();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
