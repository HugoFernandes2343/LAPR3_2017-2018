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
public class SegmentTest {

    private Segment instance;

    public SegmentTest() {
        instance = new Segment(1, 5.0, 10.0, 200.0, 90.0, 2.0, 20.0, 10.0, "PLACEHOLDER");
    }

    /**
     * Test of getSegmentIndex method, of class Segment.
     */
    @Test
    public void testGetSegmentIndex() {
        System.out.println("getSegmentIndex");
        int expResult = 1;
        int result = instance.getSegmentIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInitialHeight method, of class Segment.
     */
    @Test
    public void testGetInitialHeight() {
        System.out.println("getInitialHeight");
        double expResult = 5.0;
        double result = instance.getInitialHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSlope method, of class Segment.
     */
    @Test
    public void testGetSlope() {
        System.out.println("getSlope");
        double expResult = 10.0;
        double result = instance.getSlope();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLength method, of class Segment.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        double expResult = 200.0;
        double result = instance.getLength();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWindDirection method, of class Segment.
     */
    @Test
    public void testGetWindDirection() {
        System.out.println("getWindDirection");
        double expResult = 90.0;
        double result = instance.getWindDirection();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWindSpeed method, of class Segment.
     */
    @Test
    public void testGetWindSpeed() {
        System.out.println("getWindSpeed");
        double expResult = 2.0;
        double result = instance.getWindSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaximumVelocity method, of class Segment.
     */
    @Test
    public void testGetMaximumVelocity() {
        System.out.println("getMaximumVelocity");
        double expResult = 20.0;
        double result = instance.getMaximumVelocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMinimumVelocity method, of class Segment.
     */
    @Test
    public void testGetMinimumVelocity() {
        System.out.println("getMinimumVelocity");
        double expResult = 10.0;
        double result = instance.getMinimumVelocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getToll method, of class Segment.
     */
    @Test
    public void testGetToll() {
        System.out.println("getToll");
        Object expResult = "PLACEHOLDER";
        Object result = instance.getToll();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Segment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should be the same when its the same object
    }

    /**
     * Test of equals method, of class Segment, false case.
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
     * Test of equals method, of class Segment, true case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Segment(1, 5.0, 10.0, 200.0, 90.0, 2.0, 20.0, 10.0, "PLACEHOLDER");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Segment id: 1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
