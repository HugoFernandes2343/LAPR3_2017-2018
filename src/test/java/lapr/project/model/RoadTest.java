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
public class RoadTest {

    private Road instance;

    public RoadTest() {
        instance = new Road("A23", "Autoestrada", "A01");
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
        String name = "A24";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getTypology method, of class Road.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        String expResult = "Autoestrada";
        String result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypology method, of class Road.
     */
    @Test
    public void testSetTypology() {
        System.out.println("setTypology");
        String typology = "Estrada Citadina";
        instance.setTypology(typology);
        assertEquals(typology, instance.getTypology());
    }

    /**
     * Test of equals method, of class Road.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Road("A23", "Autoestrada", "A01");
        assertEquals("Should be the same object", true, instance.equals(obj));
        obj = "test";
        assertEquals("Should be false if the objects are not from the same class", false, instance.equals(obj));
        obj = new Road("A23", "Autoestrada", "A02");
        assertEquals("Should be false if the roads have not the same id", false, instance.equals(obj));
    }

    /**
     * Test of hashCode method, of class Road.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Road test = new Road("A23", "Autoestrada", "A01");;
        int expResult = test.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//if the objects ate the same, hashCode should be the same
    }

    /**
     * Test of toString method, of class Road.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Road name: A23, id: A01";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Road.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = "A01";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Road.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "A02";
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getToll_fare method, of class Road.
     */
    @Test
    public void testGetToll_fare() {
        System.out.println("getToll_fare");
        TollFare expResult = null;
        TollFare result = instance.getToll_fare();
        assertEquals(expResult, result);
    }

    /**
     * Test of setToll_fare method, of class Road.
     */
    @Test
    public void testSetToll_fare() {
        System.out.println("setToll_fare");
        TollFare toll_fare = null;
        instance.setToll_fare(toll_fare);
        assertEquals(toll_fare, instance.getToll_fare());
    }

}
