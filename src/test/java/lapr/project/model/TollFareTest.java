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

    private final TollFare instance;

    public TollFareTest() {
        List<TollClass> listClasses = new LinkedList<>();
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

}
