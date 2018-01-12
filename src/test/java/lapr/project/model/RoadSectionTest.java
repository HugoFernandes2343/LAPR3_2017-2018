/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
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
public class RoadSectionTest {

    private final RoadSection instance;

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
     * Test of getId method, of class RoadSection.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int result = instance.getId();
        assertTrue(result > 0);
    }

    /**
     * Test of setId method, of class RoadSection.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 2;

        instance.setId(id);
        assertTrue(instance.getId() == id);
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
     * Test of getRoadId method, of class RoadSection.
     */
    @Test
    public void testGetRoad_id() {
        System.out.println("getRoad_id");
        String expResult = "A01";
        String result = instance.getRoadId();
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
     * Test of getSegmentList method, of class RoadSection.
     */
    @Test
    public void testGetSegment_list() {
        System.out.println("getSegment_list");
        List<Segment> expResult = new LinkedList<>();
        List<Segment> result = instance.getSegmentList();
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

    /**
     * Test of getRoadId method, of class RoadSection.
     */
    @Test
    public void testGetRoadId() {
        System.out.println("getRoadId");
        String expResult = "A01";
        String result = instance.getRoadId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getSegmentList method, of class RoadSection.
     */
    @Test
    public void testGetSegmentList() {
        System.out.println("getSegmentList");

        List<Segment> expResult = new LinkedList<>();
        List<Segment> result = instance.getSegmentList();
        assertEquals(expResult, result);

    }

    /**
     * Test of setBegin method, of class RoadSection.
     */
    @Test
    public void testSetBegin() {
        System.out.println("setBegin");
        String begin = "testBegin";

        instance.setBegin(begin);
        assertEquals(begin, instance.getBegin());

    }

    /**
     * Test of setEnd method, of class RoadSection.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        String end = "testEnd";

        instance.setEnd(end);
        assertEquals(end, instance.getEnd());
    }

    /**
     * Test of setRoadId method, of class RoadSection.
     */
    @Test
    public void testSetRoadId() {
        System.out.println("setRoadId");
        String roadId = "testId";

        instance.setRoadId(roadId);
        assertEquals(roadId, instance.getRoadId());
    }

    /**
     * Test of setDirection method, of class RoadSection.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        String direction = "testDirection";

        instance.setDirection(direction);
        assertEquals(direction, instance.getDirection());
    }

    /**
     * Test of setSegmentList method, of class RoadSection.
     */
    @Test
    public void testSetSegmentList() {
        System.out.println("setSegmentList");
        List<Segment> segmentList = new LinkedList<>();

        instance.setSegmentList(segmentList);
        assertTrue(instance.getSegmentList().isEmpty());

    }

    /**
     * Test of addSegment method, of class RoadSection.
     */
    @Test
    public void testAddSegment() {
        System.out.println("addSegment");
        Segment seg = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");

        instance.addSegment(seg);
        assertTrue((instance.getSegmentList().contains(seg)));

        LinkedList<Segment> segment_list = new LinkedList<>();

        RoadSection instance2 = new RoadSection("begin", "end", "A01", "positive", segment_list);
        List<Segment> listSeg = instance2.getSegmentList();
        listSeg.add(seg);
        assertTrue(listSeg.contains(seg));

        instance2.addSegment(seg);
        assertTrue(listSeg.contains(seg));
    }

    /**
     * Test of getDBData method, of class RoadSection.
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
