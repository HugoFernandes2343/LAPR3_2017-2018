/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.ProjectList;
import lapr.project.model.TravelByPhysics;
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
public class CopyProjectControllerTest {

    private CopyProjectController instance;

    public CopyProjectControllerTest() {
        Project p1 = new Project();
        p1.setName("testp1");
        Project p2 = new Project();
        p2.setName("testp2");
        Project p3 = new Project();
        p3.setName("testp3");

        TravelByPhysics base = new TravelByPhysics();
        ProjectList pl = base.getProjectList();
        pl.addProject(p3);
        pl.addProject(p2);
        pl.addProject(p1);
        this.instance = new CopyProjectController(base);
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
     * Test of getActiveProjectData method, of class CopyProjectController.
     */
    @Test
    public void testGetActiveProjectData() {
        System.out.println("getActiveProjectData");

        Project p1 = new Project();
        p1.setName("testp1");
        Project p2 = new Project();
        p2.setName("testp2");
        Project p3 = new Project();
        p3.setName("testp3");

        TravelByPhysics base = new TravelByPhysics();
        ProjectList pl = base.getProjectList();
        pl.addProject(p3);
        pl.addProject(p2);
        pl.addProject(p1);
        pl.setActualProject(p1);
        CopyProjectController instanceActiveData = new CopyProjectController(base);

        String[] expResult = new String[2];
        expResult[0] = p1.getName();
        expResult[1] = p1.getDescription();
        String[] result = instanceActiveData.getActiveProjectData();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of copyProject method, of class CopyProjectController.
     */
    @Test
    public void testCopyProject() {
        System.out.println("copyProject");

        Project p1 = new Project();
        p1.setName("testp1");
        Project p2 = new Project();
        p2.setName("testp2");
        Project p3 = new Project();
        p3.setName("testp3");

        TravelByPhysics base = new TravelByPhysics();
        ProjectList pl = base.getProjectList();
        pl.addProject(p3);
        pl.addProject(p2);
        pl.addProject(p1);
        pl.setActualProject(p1);
        CopyProjectController instanceCopy = new CopyProjectController(base);
        instanceCopy.getActiveProjectData();

        boolean expResult = true;
        boolean result = instanceCopy.copyProject();
        assertEquals(expResult, result);
        
        boolean expResult2 = true;
        boolean result2 = instanceCopy.copyProject();
        assertEquals(expResult2, result2);

    }

}
