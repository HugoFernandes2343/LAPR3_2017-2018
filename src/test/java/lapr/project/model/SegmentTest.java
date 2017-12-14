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
 * @author Hugo
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
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInit_height method, of class Segment.
     */
    @Test
    public void testGetInit_height() {
        System.out.println("getInit_height");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        double expResult = 1;
        double result = instance.getInit_height();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFinal_height method, of class Segment.
     */
    @Test
    public void testGetFinal_height() {
        System.out.println("getFinal_height");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        double expResult = 2;
        double result = instance.getFinal_height();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getLength method, of class Segment.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_length";
        String result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWind_direction method, of class Segment.
     */
    @Test
    public void testGetWind_direction() {
        System.out.println("getWind_direction");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        double expResult = 3;
        double result = instance.getWind_direction();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWind_speed method, of class Segment.
     */
    @Test
    public void testGetWind_speed() {
        System.out.println("getWind_speed");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_windSpeed";
        String result = instance.getWind_speed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMax_velocity method, of class Segment.
     */
    @Test
    public void testGetMax_velocity() {
        System.out.println("getMax_velocity");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_maxVelocity";
        String result = instance.getMax_velocity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMin_velocity method, of class Segment.
     */
    @Test
    public void testGetMin_velocity() {
        System.out.println("getMin_velocity");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        String expResult = "teste_minVelocity";
        String result = instance.getMin_velocity();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Segment instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        Segment instance2 = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals("Equal segments", expResult, result);

        instance2 = new Segment("teste_id2", 12, 22, "teste_length2", 32, "teste_windSpeed2", "teste_maxVelocity2", "teste_minVelocity2");
        expResult = false;
        result = instance.equals(instance2);
        assertEquals("Diferent segments", expResult, result);

    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Segment instance = new Segment("teste_id2", 12, 22, "teste_length2", 32, "teste_windSpeed2", "teste_maxVelocity2", "teste_minVelocity2");;
        String expResult = String.format("Segment id: %d", instance.getId());;
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
