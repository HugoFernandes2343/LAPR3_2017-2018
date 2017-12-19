/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author
 */
public class SegmentTest {

    private Segment instance;

    public SegmentTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getId method, of class Segment.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInitHeight method, of class Segment.
     */
    @Test
    public void testGetInit_height() {
        System.out.println("getInit_height");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        double expResult = 1;
        double result = instance.getInitHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFinalHeight method, of class Segment.
     */
    @Test
    public void testGetFinal_height() {
        System.out.println("getFinal_height");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        double expResult = 2;
        double result = instance.getFinalHeight();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getLength method, of class Segment.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_length";
        String result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWindDirection method, of class Segment.
     */
    @Test
    public void testGetWind_direction() {
        System.out.println("getWind_direction");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        double expResult = 3;
        double result = instance.getWindDirection();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWindSpeed method, of class Segment.
     */
    @Test
    public void testGetWind_speed() {
        System.out.println("getWind_speed");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_windSpeed";
        String result = instance.getWindSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxVelocity method, of class Segment.
     */
    @Test
    public void testGetMax_velocity() {
        System.out.println("getMax_velocity");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_maxVelocity";
        String result = instance.getMaxVelocity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinVelocity method, of class Segment.
     */
    @Test
    public void testGetMin_velocity() {
        System.out.println("getMin_velocity");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_minVelocity";
        String result = instance.getMinVelocity();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        Object obj = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals("Equal segments", expResult, result);

        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));

        obj = new Segment("teste_id2", 12, 22, "teste_length2", 32, "teste_windSpeed2", "teste_maxVelocity2", "teste_minVelocity2");
        expResult = false;
        result = instance.equals(obj);
        assertEquals("Diferent segments", expResult, result);

    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        instance = new Segment("teste_id2", 12, 22, "teste_length2", 32, "teste_windSpeed2", "teste_maxVelocity2", "teste_minVelocity2");
        String expResult = String.format("Segment id: %s", instance.getId());
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
