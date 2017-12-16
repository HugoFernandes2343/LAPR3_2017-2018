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
public class ThrottleTest {

    public ThrottleTest() {
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
     * Test of getId method, of class Throttle.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Regime test = new Regime(10, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        Throttle instance = new Throttle("test_id", listTest);
        String expResult = "test_id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRegime_list method, of class Throttle.
     */
    @Test
    public void testGetRegime_list() {
        System.out.println("getRegime_list");
        Regime test = new Regime(10, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        Throttle instance = new Throttle("test_id", listTest);
        LinkedList<Regime> expResult = listTest;
        LinkedList<Regime> result = instance.getRegime_list();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Throttle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Regime test = new Regime(10, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        Throttle instance = new Throttle("test_id", listTest);
        Throttle instance2 = new Throttle("test_id", new LinkedList<>());
        int expResult = instance2.hashCode();
        int result = instance.hashCode();
        assertEquals("Same id/hashcode", expResult, result);

        instance2 = new Throttle("test_id2", new LinkedList<>());
        expResult = instance2.hashCode();
        result = instance.hashCode();
        boolean comparison = false;
        if(expResult == result){
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
        Regime test = new Regime(10, 20, 30, 40.0);
        LinkedList<Regime> listTest = new LinkedList<>();
        listTest.add(test);
        Throttle instance = new Throttle("test_id", listTest);
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
    }

}
