/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Set;
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
public class VelocityLimitListTest {
    
    public VelocityLimitListTest() {
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
     * Test of getVelocityLimitList method, of class VelocityLimitList.
     */
    @Test
    public void testGetVelocity_limit_list() {
        System.out.println("getVelocity_limit_list");
        VelocityLimitList instance = new VelocityLimitList();
        Set<VelocityLimit> result = instance.getVelocityLimitList();
        assertTrue("The obtained list should be empty", result.isEmpty());
    }
    
}
