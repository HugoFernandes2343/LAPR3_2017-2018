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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class NodeTest {

    private final Node instance;
    private final Node instanceEmpty;

    public NodeTest() {
        instance = new Node("test");
        instanceEmpty = new Node();
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
     * Test of getId method, of class Node.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        instance.setId("test");
        String expResult = "test";
        String result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Node.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "test";
        instance.setId(id);
        String expResult = id;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = new Node();
        instance.setId("test1");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = "test";
        assertEquals("Should be false because obj is a string", false, instance.equals(obj));
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));

    }

    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Node();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Node.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        instance.setId("test");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDBData method, of class Node.
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
