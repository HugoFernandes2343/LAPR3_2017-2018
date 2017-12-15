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
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Class("1");
        Class instance = new Class("1");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Class.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = new Class("1");
        Class instance = new Class("2");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
