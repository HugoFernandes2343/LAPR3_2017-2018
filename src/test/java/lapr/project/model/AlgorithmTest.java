/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class AlgorithmTest {
    private final Algorithm instance;
    
    public AlgorithmTest() {
        instance = new Algorithm();
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
     * Test of getName method, of class Algorithm.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Algorithm";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setName method, of class Algorithm.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "AlgorithmTest";
        instance.setName(name);
         assertEquals(name, instance.getName());
    }
    
}
