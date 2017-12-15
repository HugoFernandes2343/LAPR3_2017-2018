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
public class NodeTest {
    
   public NodeTest() {
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
        Node instance = new Node();
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
        Node instance = new Node();
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
        Node instance = new Node();
        instance.setId("test1");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Node();
        Node instance = new Node();
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
        Node instance = new Node();
        instance.setId("test");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
       
    }
    
}