/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author
 */
public class TravelByPhysicsTest {

    private final TravelByPhysics instance;

    public TravelByPhysicsTest() {
        instance = new TravelByPhysics();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getProjectList method, of class TravelByPhysics.
     */
    @Test
    public void testGetProjectList() {
        System.out.println("getProjectList");

        ProjectList result = instance.getProjectList();
        List<String> shouldBeEmpty = result.getAllNames();
        assertTrue("Since the list was just created there should be no names ergo its empty", shouldBeEmpty.isEmpty());

    }

    /**
     * Test of getAlgorithmsList method, of class TravelByPhysics.
     */
    @Test
    public void testGetAlgorithmsList() {
        System.out.println("getAlgorithmsList");

        List<Algorithm> expResult = new ArrayList<>();
        List<Algorithm> result = instance.getAlgorithmsList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAlgorithmsByName method, of class TravelByPhysics.
     */
    @Test
    public void testGetAlgorithmsByName() {
        System.out.println("getAlgorithmsByName");
        List<Algorithm> algList = instance.getAlgorithmsList();
        Algorithm a1 = new Algorithm();
        Algorithm a2 = new Algorithm();
        Algorithm a3 = new Algorithm();
        Algorithm a4 = new Algorithm();
        algList.add(a1);
        algList.add(a2);
        algList.add(a3);
        algList.add(a4);
        
        List<String> expResult = new ArrayList<>();
        expResult.add(a1.getName());
        expResult.add(a2.getName());
        expResult.add(a3.getName());
        expResult.add(a4.getName());

        List<String> result = instance.getAlgorithmsByName();
        assertEquals(expResult, result);
    }

}
