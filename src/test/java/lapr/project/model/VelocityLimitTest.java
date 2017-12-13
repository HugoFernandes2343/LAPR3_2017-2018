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
public class VelocityLimitTest {
    
    public VelocityLimitTest() {
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
     * Test of getSegment_type method, of class VelocityLimit.
     */
    @Test
    public void testGetSegment_type() {
        System.out.println("getSegment_type");
        VelocityLimit instance = null;
        String expResult = "";
        String result = instance.getSegment_type();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLimit method, of class VelocityLimit.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        VelocityLimit instance = null;
        int expResult = 0;
        int result = instance.getLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class VelocityLimit.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        VelocityLimit instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class VelocityLimit.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        VelocityLimit instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
