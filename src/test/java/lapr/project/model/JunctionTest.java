/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class JunctionTest {
    
    private Junction instance;
    
    public JunctionTest() {
        instance = new Junction();
    }

    /**
     * Test of getToll method, of class Junction.
     */
    @Test
    public void testGetToll() {
        System.out.println("getToll");
        Object expResult = null;
        Object result = instance.getToll();
        assertEquals(expResult, result);
    }

    /**
     * Test of setToll method, of class Junction.
     */
    @Test
    public void testSetToll() {
        System.out.println("setToll");
        Object toll = "PLACEHOLDER";
        instance.setToll(toll);
        assertEquals(toll, instance.getToll());
    }

    /**
     * Test of equals method, of class Junction true case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Junction obj = new Junction();
        obj.setToll("PLACEHOLDER");
        instance.setToll("PLACEHOLDER");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Junction false case.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = "Test";
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Junction.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        instance.setToll("PLACEHOLDER");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should always be the same for the same object
    }
    
}
