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
public class RegimeTest {
    
    public RegimeTest() {
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
     * Test of getTorque method, of class Regime.
     */
    @Test
    public void testGetTorque() {
        System.out.println("getTorque");
        Regime instance = null;
        int expResult = 0;
        int result = instance.getTorque();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRpm_low method, of class Regime.
     */
    @Test
    public void testGetRpm_low() {
        System.out.println("getRpm_low");
        Regime instance = null;
        int expResult = 0;
        int result = instance.getRpm_low();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRpm_high method, of class Regime.
     */
    @Test
    public void testGetRpm_high() {
        System.out.println("getRpm_high");
        Regime instance = null;
        int expResult = 0;
        int result = instance.getRpm_high();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSFC method, of class Regime.
     */
    @Test
    public void testGetSFC() {
        System.out.println("getSFC");
        Regime instance = null;
        int expResult = 0;
        int result = instance.getSFC();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Regime.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Regime instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Regime.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Regime instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
