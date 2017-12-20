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
public class VehicleListTest {

    public VehicleListTest() {
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
     * Test of getVehicle_list method, of class VehicleList.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        VehicleList instance = new VehicleList();
        Set<Vehicle> result = instance.getVehicleList();

        assertTrue("The obtained list should be empty", result.isEmpty());
    }

    /**
     * Test of setVehicleList method, of class VehicleList.
     */
    @Test
    public void testSetVehicleList() {
        System.out.println("setVehicleList");
        VehicleList instance = new VehicleList();
        instance.setVehicleList(null);

        assertTrue("The new vehicle list should be null", instance.getVehicleList() == null);
    }

    /**
     * Test of addVehicle method, of class VehicleList.
     */
    @Test
    public void testAddVehicle() {
        System.out.println("addVehicle");
        Energy e = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        VelocityLimitList vl = new VelocityLimitList();
        Vehicle car = new Vehicle("mazda44", "c7ar", "ca2r", 7, "Colmbustion", "Gyas", "300.3", "4.5", 18.0, 17, 2.40, 0.50, e, vl);
        VehicleList instance = new VehicleList();
        instance.addVehicle(car);
        assertTrue("The vehicle shoudl exist in the list", instance.getVehicleList().contains(car));

        VehicleList instance2 = new VehicleList();
        instance2.getVehicleList().add(car);
        assertTrue("The vehicle shoudl exist in the list", instance2.getVehicleList().contains(car));
        instance2.addVehicle(car);
        assertTrue("The vehicle should still exist in the list", instance2.getVehicleList().contains(car));

    }

    /**
     * Test of the method verifyAndAddVehicles from the class VehicleList
     */
    @Test
    public void testVerifyAndAddVehicles() {
        System.out.println("VerifyAndAddVehicles");
        Energy e = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        VelocityLimitList vl = new VelocityLimitList();
        Vehicle car1 = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);
        Vehicle car2 = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl); // should not be added cause its equal to car1
        Vehicle car3 = new Vehicle("mazda", "car2", "car2", 2, "Eletric", "Gasoline", "305.0", "4.3", 12.2, 11, 2.55, 0.36, e, vl); // should have name mazda1 when added
        Vehicle car4 = new Vehicle("Subaru", "car3", "car3", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);
        Vehicle car5 = new Vehicle("Subaru1", "car4", "car4", 1, "Eletric", "Gasoline", "302.0", "2.0", 12.2, 12, 2.52, 0.20, e, vl);
        Vehicle car6 = new Vehicle("Subaru", "car5", "car5", 3, "hybrid", "Biofuel", "303.0", "3.0", 12.3, 13, 2.53, 0.33, e, vl); // should have name subaru2 when added
        Vehicle car7 = new Vehicle("test", "car4", "car4", 1, "Eletric", "Gasoline", "302.0", "2.0", 12.2, 12, 2.52, 0.20, e, vl);
        Set<Vehicle> newVehicles = new HashSet<>();

        newVehicles.add(car1);
        newVehicles.add(car2);
        newVehicles.add(car3);
        newVehicles.add(car4);
        newVehicles.add(car5);
        newVehicles.add(car6);
        newVehicles.add(car7);
        VehicleList instance = new VehicleList();
        instance.getVehicleList().add(car7);
        assertTrue("Should have added five new vehivles", instance.verifyAndAddVehicles(newVehicles) == 5);

        List<String> names = instance.getAllVehicleNames();

        assertEquals("Should have 5 vehicles", 6, names.size());
        assertTrue(names.contains("mazda"));
        assertTrue(names.contains("mazda1"));
        assertTrue(names.contains("Subaru"));
        assertTrue(names.contains("Subaru1"));
        assertTrue(names.contains("Subaru2"));
        assertTrue(names.contains("test"));
    }
}
