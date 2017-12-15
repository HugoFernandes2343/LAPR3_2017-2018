/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author 
 */
public class VehicleTest {

    private final Vehicle instance;

    public VehicleTest() {
        Energy e = new Energy(5, 10, 25, new LinkedList<>(), new LinkedList<>());
        VelocityLimitList velocity_limit_list = new VelocityLimitList();
        instance = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, velocity_limit_list);
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
     * Test of getDescription method, of class Vehicle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "car";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Vehicle.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "dsfg";
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
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
     * Test of getFuel method, of class Vehicle.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        String expResult = "Gas";
        String result = instance.getFuel();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of setFuel method, of class Vehicle.
     */
    @Test
    public void testSetFuel() {
        System.out.println("setFuel");
        String fuel = "Gass";
        instance.setFuel(fuel);
        
    }
    
    /**
     * Test of getMass method, of class Vehicle.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        String expResult = "300.0";
        String result = instance.getMass();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoad method, of class Vehicle.
     */
    @Test
    public void testGetLoad() {
        System.out.println("getLoad");
        String expResult = "4.0";
        String result = instance.getLoad();
        assertEquals(expResult, result);
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
     * Test of getFrontal_area method, of class Vehicle.
     */
    @Test
    public void testGetFrontal_area() {
        System.out.println("getFrontal_area");
        double expResult = 10;
        double result = instance.getFrontal_area();
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
        Energy e = instance.getEnergy();
        assertEquals(5, e.getMinRpm());
        assertEquals(10, e.getMaxRpm());
        assertEquals(25, e.getFinalDriveRatio());
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
     * Test of getVelocity_limit_list method, of class Vehicle.
     */
    @Test
    public void testGetVelocity_limit_list() {
        System.out.println("getVelocity_limit_list");
        VelocityLimitList result = instance.getVelocity_limit_list();
        assertTrue("The velocity limit list should be empty", result.getVelocity_limit_list().isEmpty());
    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Vehicle obj =new Vehicle("mazda");
        assertTrue("The vehicles should be equal", instance.equals(obj));
        
        Vehicle obj1 = new Vehicle("ndlkms");
        assertFalse("The vehicles shouldn't be equal", instance.equals(obj1));
    }

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Vehicle instance2 = new Vehicle("mazda");
        int expResult = instance2.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
        Vehicle obj1 = new Vehicle("ndlkms");
        expResult = obj1.hashCode();
        assertNotEquals(expResult, result);
    }

}
