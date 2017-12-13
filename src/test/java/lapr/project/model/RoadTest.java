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
 * @author Hugo
 */
public class RoadTest {

    private Road instance;

    public RoadTest() {
        instance = new Road("A23", "Autoestrada", "A01");
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
     * Test of getName method, of class Road.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "A23";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Road.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "A25";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getDescription method, of class Road.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "Autoestrada";
        String result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Road.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Nova Autoestrada";
        instance.setTypology(description);
        assertEquals(description, instance.getTypology());
    }

    /**
     * Test of hashCode method, of class Road.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should be the same when its the same object
    }

    /**
     * Test of equals method, of class Road, false case.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = "test";
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Road, true case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Road("A23", "Autoestrada", new LinkedList<>());
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Road.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Road: A23";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypology method, of class Road.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        Road instance = null;
        String expResult = "";
        String result = instance.getTypology();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTypology method, of class Road.
     */
    @Test
    public void testSetTypology() {
        System.out.println("setTypology");
        String typology = "";
        Road instance = null;
        instance.setTypology(typology);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Road.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Road instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Road.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Road instance = null;
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Road.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Road instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToll_fare method, of class Road.
     */
    @Test
    public void testGetToll_fare() {
        System.out.println("getToll_fare");
        Road instance = null;
        TollFare expResult = null;
        TollFare result = instance.getToll_fare();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToll_fare method, of class Road.
     */
    @Test
    public void testSetToll_fare() {
        System.out.println("setToll_fare");
        TollFare toll_fare = null;
        Road instance = null;
        instance.setToll_fare(toll_fare);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
