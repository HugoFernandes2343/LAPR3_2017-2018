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
public class ThrottleTest {

    private Throttle instanceEmpty;
    private Throttle instance;

    public ThrottleTest() {
        instanceEmpty = new Throttle();

        Regime test = new Regime(10, 5, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        instance = new Throttle("test_id", listTest);
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
     * Test of getPercentage method, of class Throttle.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");

        String expResult = "test_id";
        String result = instance.getPercentage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRegimeList method, of class Throttle.
     */
    @Test
    public void testGetRegimeList() {
        System.out.println("getRegime_list");
        Regime test = new Regime(10, 5, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        LinkedList<Regime> expResult = listTest;
        List<Regime> result = instance.getRegimeList();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Throttle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Regime test = new Regime(10, 5, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        Throttle instance2 = new Throttle("test_id", listTest);
        int expResult = instance2.hashCode();
        int result = instance.hashCode();
        assertEquals("Same id/hashcode", expResult, result);

        instance2 = new Throttle("test_id2", new LinkedList<>());
        expResult = instance2.hashCode();
        result = instance.hashCode();
        boolean comparison = false;
        if (expResult == result) {
            comparison = true;
        }
        assertEquals("Different id/hashcode", false, comparison);
    }

    /**
     * Test of equals method, of class Throttle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Regime test = new Regime(10, 5, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        Throttle instance2 = new Throttle("test_id", listTest);
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals("Objects are equal", expResult, result);

        instance = new Throttle("test_id", listTest);
        instance2 = new Throttle("test_id2", new LinkedList<>());
        expResult = false;
        result = instance.equals(instance2);
        assertEquals("Objects are different", expResult, result);

        Object obj = new Object();
        expResult = false;
        result = instance.equals(obj);
        assertEquals("Not the same Objects", expResult, result);

        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
    }

    /**
     * Test of setPercentage method, of class Throttle.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "test_id";
        instance.setPercentage(id);
        assertEquals(id, instance.getPercentage());
    }

    /**
     * Test of setPercentage method, of class Throttle.
     */
    @Test
    public void testSetPercentage() {
        System.out.println("setPercentage");
        String percentage = "10";
        instance.setPercentage(percentage);
        assertTrue(instance.getPercentage().equals(percentage));
    }

    /**
     * Test of getPercentage method, of class Throttle.
     */
    @Test
    public void testGetPercentage() {
        System.out.println("getPercentage");
        instance.setPercentage("10");
        String expResult = "10";
        String result = instance.getPercentage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDBData method, of class Throttle.
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
