/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;
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
    private final Vehicle instance2;
    private final Vehicle instance3;
    private final Energy e;
    private final VelocityLimitList vl;

    /**
     * String name, String description, String type, int tollClass, String
     * motorization, String fuel, String mass, String load, double drag, double
     * frontal_area, double rrc, double wheelSize
     */
    public VehicleTest() {
        this.e = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        this.vl = new VelocityLimitList();
        instance = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);
        instance2 = new Vehicle("subaru");
        instance3 = new Vehicle();
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
     * Test of getFrontalArea method, of class Vehicle.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        double expResult = 10;
        double result = instance.getFrontalArea();
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

        assertEquals(5, e.getMinRpm());
        assertEquals(10, e.getMaxRpm());
        Double value = 25.0;
        assertEquals(value, e.getFinalDriveRatio());
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
     * Test of getVelocityLimitList method, of class Vehicle.
     */
    @Test
    public void testGetVelocityLimitList() {
        System.out.println("getVelocityLimitList");
        VelocityLimitList result = instance.getVelocityLimitList();
        assertTrue("The velocity limit list should be empty", result.getVelocityLimitList().isEmpty());
    }

    /**
     * Test of setName method, of class Vehicle.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "mazda1";

        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of setType method, of class Vehicle.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "truck";

        instance.setType(type);
        assertEquals(type, instance.getType());
    }

    /**
     * Test of setTollClass method, of class Vehicle.
     */
    @Test
    public void testSetTollClass() {
        System.out.println("setTollClass");
        int tollClass = 0;

        instance.setTollClass(tollClass);
        assertEquals(tollClass, instance.getTollClass());
    }

    /**
     * Test of setMotorization method, of class Vehicle.
     */
    @Test
    public void testSetMotorization() {
        System.out.println("setMotorization");
        String motorization = "Eletric";

        instance.setMotorization(motorization);
        assertEquals(motorization, instance.getMotorization());
    }

    /**
     * Test of setMass method, of class Vehicle.
     */
    @Test
    public void testSetMass() {
        System.out.println("setMass");
        String mass = "301";

        instance.setMass(mass);
        assertEquals(mass, instance.getMass());
    }

    /**
     * Test of setLoad method, of class Vehicle.
     */
    @Test
    public void testSetLoad() {
        System.out.println("setLoad");
        String load = "201";

        instance.setLoad(load);
        assertEquals(load, instance.getLoad());
    }

    /**
     * Test of setDrag method, of class Vehicle.
     */
    @Test
    public void testSetDrag() {
        System.out.println("setDrag");
        double drag = 3.0;

        instance.setDrag(drag);
        double expResult = instance.getDrag();
        assertEquals(drag, expResult, 0.0);
    }

    /**
     * Test of setFrontalArea method, of class Vehicle.
     */
    @Test
    public void testSetFrontalArea() {
        System.out.println("setFrontalArea");
        double frontal_area = 72.0;

        instance.setFrontalArea(frontal_area);
        double expResult = instance.getFrontalArea();
        assertEquals(frontal_area, expResult, 0.0);
    }

    /**
     * Test of setRrc method, of class Vehicle.
     */
    @Test
    public void testSetRrc() {
        System.out.println("setRrc");
        double rrc = 0.5;

        instance.setRrc(rrc);
        double expResult = instance.getRrc();
        assertEquals(rrc, expResult, 0.0);
    }

    /**
     * Test of setWheelSize method, of class Vehicle.
     */
    @Test
    public void testSetWheelSize() {
        System.out.println("setWheelSize");
        double wheelSize = 18.0;

        instance.setWheelSize(wheelSize);
        double expResult = instance.getWheelSize();
        assertEquals(wheelSize, expResult, 0.0);
    }

    /**
     * Test of setEnergy method, of class Vehicle.
     */
    @Test
    public void testSetEnergy() {
        System.out.println("setEnergy");
    

        instance.setEnergy(e);
        assertEquals(instance.getEnergy(), e);
    }

    /**
     * Test of setVelocityLimitList method, of class Vehicle.
     */
    @Test
    public void testSetVelocityLimitList() {
        System.out.println("setVelocityLimitList");
        VelocityLimitList velocityLimitList = null;

        instance.setVelocityLimitList(velocityLimitList);
        assertTrue(instance.getVelocityLimitList() == null);
    }

    /**
     * Test of getDrag method, of class Vehicle.
     */
    @Test
    public void testGetDrag() {
        System.out.println("getDrag");

        double expResult = 12.0;
        double result = instance.getDrag();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getRrc method, of class Vehicle.
     */
    @Test
    public void testGetRrc() {
        System.out.println("getRrc");

        double expResult = 2.5;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getEnergy method, of class Vehicle.
     */
    @Test
    public void testGetEnergy() {
        System.out.println("getEnergy");

        Energy expResult = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        Energy result = instance.getEnergy();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertTrue("The vehicles should be equal", instance.equals(obj));

        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));

        obj = "test";
        assertEquals("Should be false if they are not from the same class", false, instance.equals(obj));

        obj = new Vehicle("mazda1", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car1", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car2", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 2, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Eletric", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gasoline", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "301.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "5.0", 12.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 11.0, 10, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 12, 2.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 3.50, 0.30,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

        obj = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.50,e,vl);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));
    }

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Vehicle instanceTest = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        int expResult = instanceTest.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        Vehicle obj1 = new Vehicle("mazda22", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30,e,vl);
        expResult = obj1.hashCode();
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test of getVelocityLimit method, of class Vehicle
     */
    @Test
    public void testGetVelocityLimit(){
        VelocityLimit testLimit1 = new VelocityLimit("test", 120);
        VelocityLimit testLimit2 = new VelocityLimit("road", 125);
        vl.addVelocityLimit(testLimit1);
        vl.addVelocityLimit(testLimit2);
        Vehicle instanceTest = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);
        int result = instanceTest.getVelocityLimit("test");
        int expResult = 120;
        assertEquals(result,expResult);
    }

    /**
     * Test of getDBData method, of class Vehicle.
     */
    @Test
    public void testGetDBData() {
        System.out.println("getDBData");
        List<DatabaseExchangable> expResult = new LinkedList<>();
        expResult.add(instance);
        List<DatabaseExchangable> result = instance.getDBData();
        assertEquals(expResult, result);    
    }
}
