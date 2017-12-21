/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
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
public class SelectProjectControllerTest {

    private SelectProjectController instance;

    public SelectProjectControllerTest() {
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
        instance = new SelectProjectController(base);
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
     * Test of getProjects method, of class SelectProjectController.
     */
    @Test
    public void testGetProjects() {
        System.out.println("getProjects");
        Project p1 = new Project();
        p1.setName("testp1");
        Project p2 = new Project();
        p2.setName("testp2");
        Project p3 = new Project();
        p3.setName("testp3");

        List<String> expResult = new ArrayList<>();
        expResult.add(p3.getName());
        expResult.add(p2.getName());
        expResult.add(p1.getName());

        List<String> result = instance.getProjects();
        assertEquals(expResult, result);

    }

    /**
     * Test of loadActiveProject method, of class SelectProjectController.
     */
    @Test
    public void testLoadActiveProject() {
        System.out.println("loadActiveProject");
        
        String name = "testp1";        
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
        SelectProjectController instanceLoad = new SelectProjectController(base);
        List<String> projects = instanceLoad.getProjects();
        instanceLoad.loadActiveProject(name);
        assertEquals(pl.getActualProject().getName(),name );
    }

    /**
     * Test of getData method, of class SelectProjectController.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        
        String name = "testp1";
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
        SelectProjectController instanceData = new SelectProjectController(base);
        List<String> projects = instanceData.getProjects();
        instanceData.loadActiveProject(name);
        
        String[] expResult = new String[2];
        expResult[0] = "testp1";
        expResult[1] = "n/a";
        String[] result = instanceData.getData();
        assertArrayEquals(expResult, result);
    }

}
