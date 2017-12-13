/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Set;
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
     * Test of getVelocity_limit_list method, of class VelocityLimitList.
     */
    @Test
    public void testGetVelocity_limit_list() {
        System.out.println("getVelocity_limit_list");
        VelocityLimitList instance = new VelocityLimitList();
        Set<VelocityLimit> expResult = null;
        Set<VelocityLimit> result = instance.getVelocity_limit_list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class VelocityLimitList.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        VelocityLimitList instance = new VelocityLimitList();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class VelocityLimitList.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        VelocityLimitList instance = new VelocityLimitList();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
