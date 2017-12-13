/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.AdjacencyMatrixGraph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class NetworkTest {
    
    public NetworkTest() {
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

    /**
     * Test of getRoadMap method, of class Network.
     */
    @Test
    public void testGetRoadMap() {
        System.out.println("getRoadMap");
        Network instance = new Network();
        AdjacencyMatrixGraph<Node, Road> expResult = null;
        AdjacencyMatrixGraph<Node, Road> result = instance.getRoadMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
