/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
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
public class GearTest {

    private Gear instance;
    private Gear instanceEmpty;

    public GearTest() {
        instance = new Gear("test", 2.1);
        instanceEmpty = new Gear();
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
     * Test of getId method, of class Gear.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = "test";
        String result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRatio method, of class Gear.
     */
    @Test
    public void testGetRatio() {
        System.out.println("getRatio");

        double expResult = 2.1;
        double result = instance.getRatio();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of hashCode method, of class Gear.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Gear.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Gear("test", 2.1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Gear.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = new Gear("test1", 2.2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
        obj = "test";
        assertEquals("Should be false because obj is string", false, instance.equals(obj));

    }

    /**
     * Test of setId method, of class Gear.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "2";
        instance.setId(id);
        assertEquals(id, instance.getId());

    }

    /**
     * Test of setRatio method, of class Gear.
     */
    @Test
    public void testSetRatio() {
        System.out.println("setRatio");
        double ratio = 2.2;
        instance.setRatio(ratio);
        assertEquals(ratio, instance.getRatio(), 0.0);
    }

    /**
     * Test of getDBData method, of class Gear.
     */
    @Test
    public void testGetDBData() {
        System.out.println("getDBData");
        Set<DatabaseExchangable> expResult = new HashSet<>();
        expResult.add(instance);
        Set<DatabaseExchangable> result = instance.getDBData();
        assertEquals(expResult, result);
    }
}
