/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
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

    /**
     * Test of getVehicleRelativeVelocity method, of class Physics.
     */
    @Test
    public void testGetVehicleRelativeVelocity() {
        System.out.println("getVehicleRelativeVelocity");
        double vehicleVelocity = 25.0;
        double windVelocity = 2.0;
        double angle = 0.0;
        double expResult = 23.0;
        double result = Physics.getVehicleRelativeVelocity(vehicleVelocity, windVelocity, angle);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getForceAppliedToVehicleOnFlatSurface method, of class Physics.
     */
    @Test
    public void testGetForceAppliedToVehicleOnFlatSurface() {
        System.out.println("getForceAppliedToVehicleOnFlatSurface");
        double torque = 230.0;
        double fDrive = 3.6;
        double gearRatio = 0.75;
        double r = 0.30;
        double rrc = 0.010;
        double m = 2020.0;
        double drag = 0.320;
        double area = 1.9;
        double vr = 29.089;
        double expResult = 1556.926;
        double result = Physics.getForceAppliedToVehicleOnFlatSurface(torque, fDrive, gearRatio, r, rrc, m, drag, area, vr);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getAverage method, of class Physics.
     */
    @Test
    public void testGetAverage() {
        System.out.println("getAverage");
        List<Double> list = new LinkedList<>();
        double expResult = 0.0;
        double result = Physics.getAverage(list);
        assertEquals(expResult, result, 0.0);
        expResult = 4.0;
        list.add(3.0);
        list.add(5.0);
        result = Physics.getAverage(list);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getKineticEnergy method, of class Physics.
     */
    @Test
    public void testGetKineticEnergy() {
        System.out.println("getKineticEnergy");
        double m = 50.0;
        double v = 20.0;
        double expResult = 10000.0;
        double result = Physics.getKineticEnergy(m, v);
        assertEquals(expResult, result, 0.0);
    }

}