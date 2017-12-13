/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
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
public class EnergyTest {
    
    public EnergyTest() {
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
     * Test of getMin_rpm method, of class Energy.
     */
    @Test
    public void testGetMin_rpm() {
        System.out.println("getMin_rpm");
        Energy instance = null;
        int expResult = 0;
        int result = instance.getMin_rpm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMax_rpm method, of class Energy.
     */
    @Test
    public void testGetMax_rpm() {
        System.out.println("getMax_rpm");
        Energy instance = null;
        int expResult = 0;
        int result = instance.getMax_rpm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinal_drive_ratio method, of class Energy.
     */
    @Test
    public void testGetFinal_drive_ratio() {
        System.out.println("getFinal_drive_ratio");
        Energy instance = null;
        int expResult = 0;
        int result = instance.getFinal_drive_ratio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGear_List method, of class Energy.
     */
    @Test
    public void testGetGear_List() {
        System.out.println("getGear_List");
        Energy instance = null;
        LinkedList<Gear> expResult = null;
        LinkedList<Gear> result = instance.getGear_List();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThrottle_list method, of class Energy.
     */
    @Test
    public void testGetThrottle_list() {
        System.out.println("getThrottle_list");
        Energy instance = null;
        LinkedList<Throttle> expResult = null;
        LinkedList<Throttle> result = instance.getThrottle_list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Energy.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Energy instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Energy.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Energy instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
