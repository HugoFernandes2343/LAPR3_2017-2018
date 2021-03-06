/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class VelocityLimitListTest {

    public VelocityLimitListTest() {
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
     * Test of getVelocityLimitList method, of class VelocityLimitList.
     */
    @Test
    public void testGetVelocityLimitList() {
        System.out.println("getVelocity_limit_list");
        VelocityLimitList instance = new VelocityLimitList();
        Set<VelocityLimit> result = instance.getVelocityLimitList();
        assertTrue("The obtained list should be empty", result.isEmpty());
    }

    /**
     * Test of addVelocityLimit method, of class VelocityLimitList.
     */
    @Test
    public void testAddVelocityLimit() {
        System.out.println("addVelocityLimit");
        VelocityLimit limit = new VelocityLimit("coisa", 250);
        VelocityLimitList instance = new VelocityLimitList();
        instance.addVelocityLimit(limit);
        assertTrue(instance.getVelocityLimitList().contains(limit));

        VelocityLimitList instance2 = new VelocityLimitList();
        instance2.getVelocityLimitList().add(limit);
        instance2.addVelocityLimit(limit);
        assertTrue(instance2.getVelocityLimitList().contains(limit));

    }

    /**
     * Test of getVelocityLimit method, of class VelocityLimitList.
     */
    @Test
    public void testGetVelocityLimit() {
        System.out.println("getVelocityLimit");
        VelocityLimit testLimit1 = new VelocityLimit("test", 120);
        VelocityLimit testLimit2 = new VelocityLimit("road", 125);
        String type = "highway";

        VelocityLimitList instance = new VelocityLimitList();
        instance.addVelocityLimit(testLimit1);
        instance.addVelocityLimit(testLimit2);

        int expResult = 300;
        int result = instance.getVelocityLimit(type);
        assertEquals(expResult, result);

        String type2 = "test";
        int expResult2 = 120;
        int result2 = instance.getVelocityLimit(type2);
        assertEquals(expResult2, result2);
    }

}
