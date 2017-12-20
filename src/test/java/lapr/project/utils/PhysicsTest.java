/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

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
public class PhysicsTest {

    public PhysicsTest() {
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
     * Test of convertKmPerHourToMeterPerSec method, of class Physics.
     */
    @Test
    public void testConvertKmPerHourToMeterPerSec() {
        System.out.println("convertKmPerHourToMeterPerSec");
        double velocity = 90.0;
        double expResult = 25.0;
        double result = Physics.convertKmPerHourToMeterPerSec(velocity);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of convertKmToMeter method, of class Physics.
     */
    @Test
    public void testConvertKmToMeter() {
        System.out.println("convertKmToMeter");
        double distance = 5.0;
        double expResult = 5000.0;
        double result = Physics.convertKmToMeter(distance);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTime method, of class Physics.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        double velocity = 25.0;
        double distance = 1000.0;
        double expResult = 40.0;
        double result = Physics.getTime(velocity, distance);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getVelocity method, of class Physics.
     */
    @Test
    public void testGetVelocity() {
        System.out.println("getVelocity");
        double time = 40.0;
        double distance = 1000.0;
        double expResult = 25.0;
        double result = Physics.getVelocity(time, distance);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getEnginePower method, of class Physics.
     */
    @Test
    public void testGetEnginePower() {
        System.out.println("getEnginePower");
        double torque = 80.0;
        double rpm = 5500.0;
        double expResult = 46076.69225;
        double result = Physics.getEnginePower(torque, rpm);
        assertEquals(expResult, result, 0.0001);
    }

}
