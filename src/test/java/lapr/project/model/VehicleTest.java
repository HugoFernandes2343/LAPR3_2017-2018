/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class VehicleTest {

    private Vehicle instance;

    public VehicleTest() {
        instance = new Vehicle("mazda", "car", 1, "Combustion", 300.0, 4.0, 12.0, 2.50, 0.30);
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
        double result = instance.getDragCoefficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRollingResistanceCoefficient method, of class Vehicle.
     */
    @Test
    public void testGetRollingResistanceCoefficient() {
        System.out.println("getRollingResistanceCoefficient");
        double expResult = 2.50;
        double result = instance.getRollingResistanceCoefficient();
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBrakingEnergyRegeneration method, of class Vehicle.
     */
    @Test
    public void testGetBrakingEnergyRegeneration() {
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
     * Test of equals method, of class Vehicle, true case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Vehicle("mazda", "car", 1, "Combustion", 300.0, 4.0, 12.0, 2.50, 0.30);
        boolean expResult = true;
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

}
