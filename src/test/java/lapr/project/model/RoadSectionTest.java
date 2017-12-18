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
 * @author
 */
public class RoadSectionTest {

    private RoadSection instance;

    public RoadSectionTest() {
        LinkedList<Segment> segment_list = new LinkedList<>();
        instance = new RoadSection("begin", "end", "A01", "positive", segment_list);
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
        String expResult = "begin";
        String result = instance.getBegin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnd method, of class RoadSection.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        String expResult = "end";
        String result = instance.getEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoad_id method, of class RoadSection.
     */
    @Test
    public void testGetRoad_id() {
        System.out.println("getRoad_id");
        String expResult = "A01";
        String result = instance.getRoad_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class RoadSection.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        String expResult = "positive";
        String result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSegment_list method, of class RoadSection.
     */
    @Test
    public void testGetSegment_list() {
        System.out.println("getSegment_list");
        LinkedList<Segment> expResult = new LinkedList<>();
        LinkedList<Segment> result = instance.getSegment_list();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of hashCode method, of class RoadSection.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        LinkedList<Segment> segment_list = new LinkedList<>();
        RoadSection test = new RoadSection("begin", "end", "A01", "positive", segment_list);
        int expResult = test.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should be the same if it is the same RoadSection
    }

    /**
     * Test of equals method, of class RoadSection.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        LinkedList<Segment> segment_list = new LinkedList<>();
        Object obj = new RoadSection("begin", "end", "A01", "positive", segment_list);
        assertEquals("Should be the same if the objects are the same", true, instance.equals(obj));
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
        obj = "test";
        assertEquals("Should not be the same if the objects are from different classes", false, instance.equals(obj));
        obj = new RoadSection("test", "end", "A01", "positive", segment_list);
        assertEquals("Should be the same if the RoadSections begin are different", false, instance.equals(obj));
        obj = new RoadSection("begin", "test", "A01", "positive", segment_list);
        assertEquals("Should be the same if the RoadSections end are different", false, instance.equals(obj));
    }

}
