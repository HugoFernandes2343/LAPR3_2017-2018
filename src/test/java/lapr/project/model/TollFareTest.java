/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
public class TollFareTest {

    private final TollFare instance;

    public TollFareTest() {
        List<TollClass> listClasses = new LinkedList<>();

        TollClass c1 = new TollClass("1", 6.3);
        TollClass c2 = new TollClass("2", 4.3);
        listClasses.add(c1);
        listClasses.add(c2);

        instance = new TollFare(listClasses);
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
        TollClass c1 = new TollClass("1", 1.2);

        instance.addClass(id, price);
        assertTrue(instance.getListClasses().contains(c1));

    }

    /**
     * Test of getTollFare method, of class TollFare.
     */
    @Test
    public void testGetListClasses() {
        System.out.println("getTollFare");

        List<TollClass> expResult = new LinkedList<>();

        TollClass c1 = new TollClass("1", 6.3);
        TollClass c2 = new TollClass("2", 4.3);
        expResult.add(c1);
        expResult.add(c2);

        List<TollClass> result = instance.getListClasses();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTollFare method, of class TollFare.
     */
    @Test
    public void testSetTollFare() {
        System.out.println("setTollFare");
        List<TollClass> tollFare = null;

        instance.setListClasses(tollFare);
        assertTrue(instance.getListClasses() == null);
    }

    /**
     * Test of setListClasses method, of class TollFare.
     */
    @Test
    public void testSetListClasses() {
        System.out.println("setListClasses");

        List<TollClass> listClasses = new LinkedList<>();
        TollClass c1 = new TollClass("1", 3.3);
        TollClass c2 = new TollClass("2", 2.3);
        listClasses.add(c1);
        listClasses.add(c2);

        instance.setListClasses(listClasses);
        assertTrue(instance.getListClasses().equals(listClasses));
    }

    /**
     * Test of getDBData method, of class TollFare.
     */
    @Test
    public void testGetDBData() {
        System.out.println("getDBData");

        List<DatabaseExchangable> expResult = new LinkedList<>();
        expResult.add(instance);

        List<DatabaseExchangable> result = instance.getDBData();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class TollFare.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        List<TollClass> listClasses = new LinkedList<>();

        TollClass c1 = new TollClass("1", 6.3);
        TollClass c2 = new TollClass("2", 4.3);
        listClasses.add(c1);
        listClasses.add(c2);

        int expResult = new TollFare(listClasses).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class TollFare.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        List<TollClass> listClasses = new LinkedList<>();

        TollClass c1 = new TollClass("1", 6.3);
        TollClass c2 = new TollClass("2", 4.3);
        listClasses.add(c1);
        listClasses.add(c2);

        Object obj = new TollFare(listClasses);
        assertEquals("Should be true if they are the same object", true, instance.equals(obj));

        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));

        obj = "test";
        assertEquals("Should be false if they are not from the same class", false, instance.equals(obj));

        obj = new TollFare(listClasses);
        instance.addClass("3", 2.11);
        assertEquals("Should be false if they have not the same Lists", false, instance.equals(obj));
    }

}
