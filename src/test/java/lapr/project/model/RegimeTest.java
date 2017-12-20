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
 * @author
 */
public class RegimeTest {

    private final Regime instance;
    private final Regime instanceEmpty;

    public RegimeTest() {
        instance = new Regime(20,10, 30, 40, 50.0);
        instanceEmpty = new Regime();
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
    public void testGetTorqueHigh() {
        System.out.println("getTorque");
        int expResult = 20;
        int result = instance.getTorqueHigh();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRpmLow method, of class Regime.
     */
    @Test
    public void testGetRpm_low() {
        System.out.println("getRpmLow");
        int expResult = 30;
        int result = instance.getRpmLow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRpmHigh method, of class Regime.
     */
    @Test
    public void testGetRpm_high() {
        System.out.println("getRpmHigh");
        int expResult = 40;
        int result = instance.getRpmHigh();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSFC method, of class Regime.
     */
    @Test
    public void testGetSFC() {
        System.out.println("getSFC");
        Double expResult = 50.0;
        Double result = instance.getSFC();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Regime.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Regime test = new Regime(20, 10, 30, 40, 50.0);
        int expResult = test.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//result of hashCode should be the same if it is the same object
    }

    /**
     * Test of equals method, of class Regime, true case.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Regime(20, 10, 30, 40, 50.0);
        assertEquals("Should be true if they are the same object", true, instance.equals(obj));
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
        obj = "test";
        assertEquals("Should be false if they are not from the same class", false, instance.equals(obj));
        obj = new Regime(2, 1, 30, 40, 50.0);
        assertEquals("Should be false if they have not the same torque", false, instance.equals(obj));
        obj = new Regime(20, 10, 3, 40, 50.0);
        assertEquals("Should be false if they have not the same rpm_low", false, instance.equals(obj));
        obj = new Regime(20, 10, 30, 4, 50.0);
        assertEquals("Should be false if they have not the same rpm_high", false, instance.equals(obj));
        obj = new Regime(20, 10, 30, 40, 5.0);
        assertEquals("Should be false if they have not the same SFC", false, instance.equals(obj));
    }

    /**
     * Test of getRpmLow method, of class Regime.
     */
    @Test
    public void testGetRpmLow() {
        System.out.println("getRpmLow");
        int expResult = 30;
        int result = instance.getRpmLow();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getRpmHigh method, of class Regime.
     */
    @Test
    public void testGetRpmHigh() {
        System.out.println("getRpmHigh");
        int expResult = 40;
        int result = instance.getRpmHigh();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTorque method, of class Regime.
     */
    @Test
    public void testSetTorque() {
        System.out.println("setTorque");
        int torque = 10;
        instance.setTorqueHigh(torque);
        assertEquals(torque,instance.getTorqueHigh());
       
    }

    /**
     * Test of setRpmLow method, of class Regime.
     */
    @Test
    public void testSetRpmLow() {
        System.out.println("setRpmLow");
        int rpmLow = 25;
        instance.setRpmLow(rpmLow);
        assertEquals(rpmLow,instance.getRpmLow());
        
    }

    /**
     * Test of setRpmHigh method, of class Regime.
     */
    @Test
    public void testSetRpmHigh() {
        System.out.println("setRpmHigh");
        int rpmHigh = 80;
        instance.setRpmHigh(rpmHigh);
        assertEquals(rpmHigh,instance.getRpmHigh());
    }

    /**
     * Test of setSFC method, of class Regime.
     */
    @Test
    public void testSetSFC() {
        System.out.println("setSFC");
        Double SFC = 3.3;
        instance.setSFC(SFC);
       assertEquals(SFC,instance.getSFC(),0.0);
    }
}
