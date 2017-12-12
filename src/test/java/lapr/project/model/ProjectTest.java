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
public class ProjectTest {

    private Project instance;

    public ProjectTest() {
        instance = new Project();
    }

    /**
     * Test of getName method, of class Project.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "n/a";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Project.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Project 1";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getDescription method, of class Project.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "n/a";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Project.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "This is a test Project";
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of hashCode method, of class Project.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should always be the same when its the same object
    }

    /**
     * Test of equals method, of class Project false case.
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
     * Test of equals method, of class Project false case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Project();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Project.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Project: n/a";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
