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
public class EditProjectControllerTest {

    private EditProjectController instance;

    public EditProjectControllerTest() {
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
        instance = new EditProjectController(base);
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
     * Test of readyEdit method, of class EditProjectController.
     */
    @Test
    public void testReadyEdit() {
        System.out.println("readyEdit");

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
        EditProjectController instanceLoad = new EditProjectController(base);

        instanceLoad.readyEdit();
        assertEquals(pl.getActualProject().getName(), p1.getName());
    }

    /**
     * Test of alterProject method, of class EditProjectController.
     */
    @Test
    public void testAlterProject() {
        System.out.println("alterProject");

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
        EditProjectController instanceAlter = new EditProjectController(base);

        instanceAlter.readyEdit();

        String name = "";
        String description = "";

        boolean expResult = false;
        boolean result = instanceAlter.alterProject(name, description);
        assertEquals(expResult, result);

        String name2 = "testNoDesc";
        String description2 = "";

        boolean expResult2 = false;
        boolean result2 = instanceAlter.alterProject(name2, description2);
        assertEquals(expResult2, result2);

        String name3 = "";
        String description3 = "testNoName";

        boolean expResult3 = false;
        boolean result3 = instanceAlter.alterProject(name3, description3);
        assertEquals(expResult3, result3);

        String name4 = "test";
        String description4 = "desc";

        boolean expResult4 = true;
        boolean result4 = instanceAlter.alterProject(name4, description4);
        assertEquals(expResult4, result4);

    }

    /**
     * Test of saveChanges method, of class EditProjectController.
     */
    @Test
    public void testSaveChanges() {
        System.out.println("saveChanges");

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
        EditProjectController instanceSave = new EditProjectController(base);

        instanceSave.readyEdit();

        String name = "test";
        String description = "desc";

        instanceSave.alterProject(name, description);

        boolean expResult = true;
        boolean result = instanceSave.saveChanges();
        assertEquals(expResult, result);
        
        String name2 = "testp1";
        String description2 = "n/a";

        instanceSave.alterProject(name2, description2);

        boolean expResult2 = false;
        boolean result2 = instanceSave.saveChanges();
        assertEquals(expResult2, result2);
        
    }

}
