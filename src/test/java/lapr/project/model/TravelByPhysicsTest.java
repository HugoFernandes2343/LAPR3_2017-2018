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
        ProjectList get = instance.getProjectList();
        
        assertTrue("The obtained list should be empty", get.getProjectList().isEmpty());
        Project p = new Project();
        instance.getProjectList().addProject(p);
        get = instance.getProjectList();
        assertTrue("The obtained list should have one project", get.getProjectList().size() == 1);
    }

   
        
}
