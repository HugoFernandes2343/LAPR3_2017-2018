/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.AdjacencyMatrixGraph;
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
public class NetworkTest {
    
    public NetworkTest() {
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
     * Test of getId method, of class Network.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Network instance = new Network();
        String expResult = "TestNetwork";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Network.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Network instance = new Network();
        String expResult = "test";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hashCode method, of class Network.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Network instance = new Network();
        Network instanceTest = new Network();
        int expResult = instanceTest.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Network.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Network();
        Network instance = new Network();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Network.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Network instance = new Network();
        String expResult = "Network{id=TestNetwork}";
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setId method, of class Network.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Network instance = new Network();
        instance.setId(id);
        assertEquals(id,instance.getId());
    }

}
