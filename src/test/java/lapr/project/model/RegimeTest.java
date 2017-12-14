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

    private Regime instance;

    public RegimeTest() {
        instance = new Regime(20, 30, 40, 50);
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
        int expResult = 20;
        int result = instance.getTorque();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRpm_low method, of class Regime.
     */
    @Test
    public void testGetRpm_low() {
        System.out.println("getRpm_low");
        int expResult = 30;
        int result = instance.getRpm_low();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRpm_high method, of class Regime.
     */
    @Test
    public void testGetRpm_high() {
        System.out.println("getRpm_high");
        int expResult = 40;
        int result = instance.getRpm_high();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSFC method, of class Regime.
     */
    @Test
    public void testGetSFC() {
        System.out.println("getSFC");
        int expResult = 50;
        int result = instance.getSFC();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Regime.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Regime test = new Regime(20, 30, 40, 50);;
        int expResult = test.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//result of hashCode should be the same if it is the same object
    }

    /**
     * Test of equals method, of class Regime, true case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Regime(20, 30, 40, 50);
        assertEquals("Should be true if they are the same object",true, instance.equals(obj));
        obj = "test";
        assertEquals("Should be false if they are not from the same class",false, instance.equals(obj));
        obj = new Regime(2, 30, 40, 50);
        assertEquals("Should be false if they have not the same torque",false, instance.equals(obj));
        obj = new Regime(20, 3, 40, 50);
        assertEquals("Should be false if they have not the same rpm_low",false, instance.equals(obj));
        obj = new Regime(20, 30, 4, 50);
        assertEquals("Should be false if they have not the same rpm_high",false, instance.equals(obj));
        obj = new Regime(20, 30, 40, 5);
        assertEquals("Should be false if they have not the same SFC",false, instance.equals(obj));
    }
}
