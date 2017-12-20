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

    private final Segment instance;
    private final Segment instanceEmpty;

    public SegmentTest() {
        instance = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        instanceEmpty = new Segment();
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
        String expResult = "teste_length";
        String result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWindDirection method, of class Segment.
     */
    @Test
    public void testGetWindDirection() {
        System.out.println("getWind_direction");   
        double expResult = 3;
        double result = instance.getWindDirection();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWindSpeed method, of class Segment.
     */
    @Test
    public void testGetWindSpeed() {
        System.out.println("getWind_speed");    
        String expResult = "teste_windSpeed";
        String result = instance.getWindSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxVelocity method, of class Segment.
     */
    @Test
    public void testGetMaxVelocity() {
        System.out.println("getMax_velocity");       
        String expResult = "teste_maxVelocity";
        String result = instance.getMaxVelocity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinVelocity method, of class Segment.
     */
    @Test
    public void testGetMinVelocity() {
        System.out.println("getMin_velocity");
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
        Object obj = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals("Equal segments", expResult, result);

        obj = "test";
        assertEquals("Should be false because obj is a string", false, instance.equals(obj));
        
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
        String expResult = String.format("Segment id: %s", instance.getId());
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInitHeight method, of class Segment.
     */
    @Test
    public void testGetInitHeight() {
        System.out.println("getInitHeight");      
        double expResult = 1;
        double result = instance.getInitHeight();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getFinalHeight method, of class Segment.
     */
    @Test
    public void testGetFinalHeight() {
        System.out.println("getFinalHeight");      
        double expResult = 2;
        double result = instance.getFinalHeight();
        assertEquals(expResult, result, 0.0);

    }

   

    /**
     * Test of setId method, of class Segment.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";   
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of setInitHeight method, of class Segment.
     */
    @Test
    public void testSetInitHeight() {
        System.out.println("setInitHeight");
        double initHeight = 3;     
        instance.setInitHeight(initHeight);
        assertEquals(initHeight, instance.getInitHeight(), 0.0);
    }

    /**
     * Test of setFinalHeight method, of class Segment.
     */
    @Test
    public void testSetFinalHeight() {
        System.out.println("setFinalHeight");
        double finalHeight = 4;     
        instance.setFinalHeight(finalHeight);
        assertEquals(finalHeight, instance.getFinalHeight(), 0.0);
    }

    /**
     * Test of setLength method, of class Segment.
     */
    @Test
    public void testSetLength() {
        System.out.println("setLength");
        String length = "";     
        instance.setLength(length);
        assertEquals(length, instance.getLength());
    }

    /**
     * Test of setWindDirection method, of class Segment.
     */
    @Test
    public void testSetWindDirection() {
        System.out.println("setWindDirection");
        double windDirection = 7.2;      
        instance.setWindDirection(windDirection);
        assertEquals(windDirection, instance.getWindDirection(), 0.0);
    }

    /**
     * Test of setWindSpeed method, of class Segment.
     */
    @Test
    public void testSetWindSpeed() {
        System.out.println("setWindSpeed");
        String windSpeed = "test";  
        instance.setWindSpeed(windSpeed);
        assertEquals(windSpeed, instance.getWindSpeed());
    }

    /**
     * Test of setMaxVelocity method, of class Segment.
     */
    @Test
    public void testSetMaxVelocity() {
        System.out.println("setMaxVelocity");
        String maxVelocity = "";      
        instance.setMaxVelocity(maxVelocity);
        assertEquals(maxVelocity, instance.getMaxVelocity());
    }

    /**
     * Test of setMinVelocity method, of class Segment.
     */
    @Test
    public void testSetMinVelocity() {
        System.out.println("setMinVelocity");
        String minVelocity = "";
        instance.setMinVelocity(minVelocity);
        assertEquals(minVelocity, instance.getMinVelocity());
    }

    /**
     * Test of hashCode method, of class Segment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = new Segment("teste_id", 1, 2, "teste_length", 3, "teste_windSpeed", "teste_maxVelocity", "teste_minVelocity").hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

}
