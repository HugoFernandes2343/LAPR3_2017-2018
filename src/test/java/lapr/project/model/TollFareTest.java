/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
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
public class TollFareTest {

    public TollFareTest() {
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

    //All tests were deleted due to model update on 15/12/2017

    /**
     * Test of addClass method, of class TollFare.
     */
    @Test
    public void testAddClass() {
        System.out.println("addClass");
        String id = "1";
        Double price = 1.2;
        Class c1 = new Class("1",1.2);
        TollFare instance = new TollFare();
        instance.addClass(id, price);
        assertTrue(instance.getTollFare().contains(c1));
        
    }

    /**
     * Test of getTollFare method, of class TollFare.
     */
    @Test
    public void testGetTollFare() {
        System.out.println("getTollFare");
        TollFare instance = new TollFare();
        List<Class> expResult = new LinkedList<>();
        List<Class> result = instance.getTollFare();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTollFare method, of class TollFare.
     */
    @Test
    public void testSetTollFare() {
        System.out.println("setTollFare");
        List<Class> tollFare = null;
        TollFare instance = new TollFare();
        instance.setTollFare(tollFare);
        assertTrue(instance.getTollFare() == null);
    }

}
