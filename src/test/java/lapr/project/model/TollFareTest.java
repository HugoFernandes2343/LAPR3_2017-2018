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
 * @author Hugo
 */
public class TollFareTest {
    
    public TollFareTest() {
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
     * Test of setToll_fare method, of class TollFare.
     */
    @Test
    public void testSetToll_fare() {
        System.out.println("setToll_fare");
        String[][] toll_fare = null;
        TollFare instance = null;
        instance.setToll_fare(toll_fare);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class TollFare.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TollFare instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class TollFare.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        TollFare instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
