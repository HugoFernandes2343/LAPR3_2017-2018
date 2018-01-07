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
public class TheoreticalMostEnergyEfficientAnalysisTest {

    private TheoreticalMostEnergyEfficientAnalysis instance1;

    private TheoreticalMostEnergyEfficientAnalysis instance2;

    public TheoreticalMostEnergyEfficientAnalysisTest() {
        this.instance1 = new TheoreticalMostEnergyEfficientAnalysis();

        Node begin = new Node();
        Node end = new Node();
        Vehicle v = new Vehicle();
        String name = "test";
        this.instance2 = new TheoreticalMostEnergyEfficientAnalysis(begin, end, v, name);
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
     * Test of setAceleratingAcceleration method, of class
     * TheoreticalMostEnergyEfficientAnalysis.
     */
    @Test
    public void testSetAceleratingAcceleration() {
        System.out.println("setAceleratingAcceleration");
        double aceleratingAcceleration = 56.0;
        instance1.setAceleratingAcceleration(aceleratingAcceleration);
        assertTrue(instance1.getAceleratingAcceleration() == aceleratingAcceleration);
    }

    /**
     * Test of setBrakingAcceleration method, of class
     * TheoreticalMostEnergyEfficientAnalysis.
     */
    @Test
    public void testSetBrakingAcceleration() {
        System.out.println("setBrakingAcceleration");
        double brakingAcceleration = 12.0;
        instance1.setBrakingAcceleration(brakingAcceleration);
        assertTrue(instance1.getBrakingAcceleration() == brakingAcceleration);
    }

    /**
     * Test of getAceleratingAcceleration method, of class
     * TheoreticalMostEnergyEfficientAnalysis.
     */
    @Test
    public void testGetAceleratingAcceleration() {
        System.out.println("getAceleratingAcceleration");
        instance1.setAceleratingAcceleration(7);
        double expResult = 7.0;
        double result = instance1.getAceleratingAcceleration();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getBrakingAcceleration method, of class
     * TheoreticalMostEnergyEfficientAnalysis.
     */
    @Test
    public void testGetBrakingAcceleration() {
        System.out.println("getBrakingAcceleration");
        instance1.setBrakingAcceleration(8);
        double expResult = 8.0;
        double result = instance1.getBrakingAcceleration();
        assertEquals(expResult, result, 0.0);

    }

}
