/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Hugo
 */
public class VehicleTest {

    private Vehicle instance;

    public VehicleTest() {
        instance = new Vehicle("mazda");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getName method, of class Vehicle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "mazda";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Vehicle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "car";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTollClass method, of class Vehicle.
     */
    @Test
    public void testGetTollClass() {
        System.out.println("getTollClass");
        int expResult = 1;
        int result = instance.getTollClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMotorization method, of class Vehicle.
     */
    @Test
    public void testGetMotorization() {
        System.out.println("getMotorization");
        String expResult = "Combustion";
        String result = instance.getMotorization();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMass method, of class Vehicle.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        double expResult = 300.0;
        double result = instance.getMass();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLoad method, of class Vehicle.
     */
    @Test
    public void testGetLoad() {
        System.out.println("getLoad");
        double expResult = 4.0;
        double result = instance.getLoad();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDragCoefficient method, of class Vehicle.
     */
    @Test
    public void testGetDragCoefficient() {
        System.out.println("getDragCoefficient");
        double expResult = 12.0;
        double result = instance.getDrag();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRollingResistanceCoefficient method, of class Vehicle.
     */
    @Test
    public void testGetRollingResistanceCoefficient() {
        System.out.println("getRollingResistanceCoefficient");
        double expResult = 2.50;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWheelSize method, of class Vehicle.
     */
    @Test
    public void testGetWheelSize() {
        System.out.println("getWheelSize");
        double expResult = 0.30;
        double result = instance.getWheelSize();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getEnergyFunction method, of class Vehicle.
     */
    @Test
    public void testGetEnergyFunction() {
        // TODO review the generated test code and remove the default call to fail.
        System.out.print("The test is not implemented yet.");
    }

    /**
     * Test of getBrakingEnergyRegeneration method, of class Vehicle.
     */
    @Test
    public void testGetBrakingEnergyRegeneration() {
        // TODO review the generated test code and remove the default call to fail.
        System.out.print("The test is not implemented yet.");
    }
    
    

    /**
     * Test of equals method, of class Vehicle, false case.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = 2;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    

    /**
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Vehicle: mazda";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDrag method, of class Vehicle.
     */
    @Test
    public void testGetDrag() {
        System.out.println("getDrag");
        Vehicle instance = null;
        double expResult = 0.0;
        double result = instance.getDrag();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRrc method, of class Vehicle.
     */
    @Test
    public void testGetRrc() {
        System.out.println("getRrc");
        Vehicle instance = null;
        double expResult = 0.0;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Vehicle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Vehicle instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Vehicle.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Vehicle instance = null;
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFuel method, of class Vehicle.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        Vehicle instance = null;
        String expResult = "";
        String result = instance.getFuel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFuel method, of class Vehicle.
     */
    @Test
    public void testSetFuel() {
        System.out.println("setFuel");
        String fuel = "";
        Vehicle instance = null;
        instance.setFuel(fuel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrontal_area method, of class Vehicle.
     */
    @Test
    public void testGetFrontal_area() {
        System.out.println("getFrontal_area");
        Vehicle instance = null;
        double expResult = 0.0;
        double result = instance.getFrontal_area();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnergy method, of class Vehicle.
     */
    @Test
    public void testGetEnergy() {
        System.out.println("getEnergy");
        Vehicle instance = null;
        Energy expResult = null;
        Energy result = instance.getEnergy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVelocity_limit_list method, of class Vehicle.
     */
    @Test
    public void testGetVelocity_limit_list() {
        System.out.println("getVelocity_limit_list");
        Vehicle instance = null;
        VelocityLimitList expResult = null;
        VelocityLimitList result = instance.getVelocity_limit_list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Vehicle instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Vehicle instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
