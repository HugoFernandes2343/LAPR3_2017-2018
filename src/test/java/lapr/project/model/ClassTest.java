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
public class ClassTest {

    public ClassTest() {
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
     * Test of getId method, of class Class.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Class instance = new Class("1");
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Class.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Class instanceBase = new Class("1");
        Class instance = new Class("1");
        int expResult = instance.hashCode();
        int result = instanceBase.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Class.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Class("1");
        Class instance = new Class("1");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Class("2");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
    }

    /**
     * Test of getPrice method, of class Class.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Class instance = new Class("1",2.5);
        Double expResult = 2.5;
        Double result = instance.getPrice();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class Class.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "2";
        Class instance = new Class();
        instance.setId(id);
        assertEquals(id,instance.getId());
    }

    /**
     * Test of setPrice method, of class Class.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Double price = 2.5;
        Class instance = new Class();
        instance.setPrice(price);
        assertEquals(price,instance.getPrice());
    }

   
}
