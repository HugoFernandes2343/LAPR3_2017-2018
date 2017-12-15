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

    /**
     * Test of setToll_fare method, of class TollFare.
     */
    @Test
    public void testSetToll_fare() {
        System.out.println("setToll_fare");
        String[][] toll_fare = new String[1][1];
        TollFare instance = new TollFare(toll_fare);
        instance.setToll_fare(toll_fare);
    }

    /**
     * Test of hashCode method, of class TollFare.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        String[][] toll_fare = new String[1][1];
        TollFare instance = new TollFare(toll_fare);
        TollFare instance2 = new TollFare(toll_fare);
        int expResult = instance.hashCode();
        int result = instance2.hashCode();
        
        assertEquals("Same id/hashcode", expResult, result);

        instance2 = new TollFare(new String[2][2]);
        expResult = instance.hashCode();
        result = instance2.hashCode();
        boolean comparison = false;
        if(expResult == result){
        comparison = true;
        }        
        assertEquals("Different id/hashcode", false, comparison);
    }

    /**
     * Test of equals method, of class TollFare.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        String[][] toll_fare = new String[1][1];
        TollFare instance = new TollFare(toll_fare);
        TollFare instance2 = new TollFare(toll_fare);
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals("Objects are equal", expResult, result);

        instance2 = new TollFare(new String[2][2]);
        expResult = false;
        result = instance.equals(instance2);
        assertEquals("Objects are different", expResult, result);

        Object obj = new Object();
        expResult = false;
        result = instance.equals(instance2);
        assertEquals("Not the same Objects", expResult, result);
    }

}
