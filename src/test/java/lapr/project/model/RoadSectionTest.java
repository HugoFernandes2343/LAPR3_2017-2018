/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class RoadSectionTest {
    
    public RoadSectionTest() {
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
     * Test of getBegin method, of class RoadSection.
     */
    @Test
    public void testGetBegin() {
        System.out.println("getBegin");
        RoadSection instance = null;
        String expResult = "";
        String result = instance.getBegin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnd method, of class RoadSection.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        RoadSection instance = null;
        String expResult = "";
        String result = instance.getEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoad_id method, of class RoadSection.
     */
    @Test
    public void testGetRoad_id() {
        System.out.println("getRoad_id");
        RoadSection instance = null;
        String expResult = "";
        String result = instance.getRoad_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class RoadSection.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        RoadSection instance = null;
        String expResult = "";
        String result = instance.getDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSegment_list method, of class RoadSection.
     */
    @Test
    public void testGetSegment_list() {
        System.out.println("getSegment_list");
        RoadSection instance = null;
        LinkedList<Segment> expResult = null;
        LinkedList<Segment> result = instance.getSegment_list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class RoadSection.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        RoadSection instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class RoadSection.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        RoadSection instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
