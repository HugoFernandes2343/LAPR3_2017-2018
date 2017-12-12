/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class RoadTest {

    private Road instance;

    public RoadTest() {
        instance = new Road("A23", "Autoestrada", new LinkedList<>());
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
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Road.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Nova Autoestrada";
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
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
}
