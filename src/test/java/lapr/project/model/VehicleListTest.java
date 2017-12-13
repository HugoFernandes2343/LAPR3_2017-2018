/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Set;
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

       @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        System.out.print("The test is not implemented yet.");
    }

    /**
     * Test of getVehicle_list method, of class VehicleList.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        VehicleList instance = new VehicleList();
        Set<Vehicle> expResult = null;
        Set<Vehicle> result = instance.getVehicleList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
