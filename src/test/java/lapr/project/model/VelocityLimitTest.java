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
public class VelocityLimitTest {

    private VelocityLimit instance;
    private final VelocityLimit instanceEmpty;

    public VelocityLimitTest() {
        instance = new VelocityLimit("coisa", 250);
        instanceEmpty = new VelocityLimit();
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
     * Test of getSegmentType method, of class VelocityLimit.
     */
    @Test
    public void testGetSegment_type() {
        System.out.println("getSegment_type");
        String expResult = "coisa";
        String result = instance.getSegmentType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLimit method, of class VelocityLimit.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");

        int expResult = 250;
        int result = instance.getLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class VelocityLimit.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        VelocityLimit instance1 = new VelocityLimit("coisa", 250);
        int expResult = instance1.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        VelocityLimit instance2 = new VelocityLimit("coisasdf", 259);
        expResult = instance2.hashCode();
        result = instance.hashCode();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of equals method, of class VelocityLimit.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new VelocityLimit("coisa", 250);
        assertTrue("Should be equal", obj.equals(instance));

        instance = new VelocityLimit("coisafesd", 2567);
        assertFalse("Shouldn't be equal", obj.equals(instance));

        obj = "test";
        assertEquals("Should be false because obj is a string", false, instance.equals(obj));
        
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
    }

    /**
     * Test of getSegmentType method, of class VelocityLimit.
     */
    @Test
    public void testGetSegmentType() {
        System.out.println("getSegmentType");
        String expResult = "coisa";
        String result = instance.getSegmentType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSegmentType method, of class VelocityLimit.
     */
    @Test
    public void testSetSegmentType() {
        System.out.println("setSegmentType");
        String segmentType = "Cena";
        instance.setSegmentType(segmentType);
        assertEquals(segmentType, instance.getSegmentType());
    }

    /**
     * Test of setLimit method, of class VelocityLimit.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        int limit = 230;

        instance.setLimit(limit);
        assertEquals(limit, instance.getLimit());
    }

}
