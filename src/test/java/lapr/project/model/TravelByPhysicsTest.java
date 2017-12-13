/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author Hugo
 */
public class TravelByPhysicsTest {
    
    public TravelByPhysicsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getProjectList method, of class TravelByPhysics.
     */
    @Test
    public void testGetProjectList() {
        System.out.println("getProjectList");
        TravelByPhysics instance = new TravelByPhysics();
        ProjectList get = instance.getProject_list();
        
        assertTrue("The obtained list should be empty", get.getProject_list().isEmpty());
        Project p = new Project();
        instance.getProject_list().addProject(p);
        get = instance.getProject_list();
        assertTrue("The obtained list should have one project", get.getProject_list().size() == 1);
    }

    /**
     * Test of getProject_list method, of class TravelByPhysics.
     */
    @Test
    public void testGetProject_list() {
        System.out.println("getProject_list");
        TravelByPhysics instance = new TravelByPhysics();
        ProjectList expResult = null;
        ProjectList result = instance.getProject_list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
        
}
