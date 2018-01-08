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
 * @author hugod
 */
public class TollClassTest {
    
    private final TollClass instance;
    private final TollClass instanceEmpty;
    private final TollClass instance2;

    public TollClassTest() {
        instance = new TollClass("1", 2.5);
        instanceEmpty = new TollClass();
        instance2 = new TollClass("1");
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
     * Test of getId method, of class TollClass.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");

        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class TollClass.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TollClass instanceBase = new TollClass("1");

        int expResult = instance.hashCode();
        int result = instanceBase.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TollClass.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new TollClass("1");

        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = new TollClass("2");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        obj = "test";
        assertEquals("Should be false because obj is a string", false, instance.equals(obj));

        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
    }

    /**
     * Test of getPrice method, of class TollClass.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");

        Double expResult = 2.5;
        Double result = instance.getPrice();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class TollClass.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "2";

        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of setPrice method, of class TollClass.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Double price = 2.5;

        instance.setPrice(price);
        assertEquals(price, instance.getPrice());
    }

    /**
     * Test of getDBData method, of class TollClass.
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
