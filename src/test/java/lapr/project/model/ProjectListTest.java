/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author 
 */
public class ProjectListTest {

    private ProjectList instance;

    public ProjectListTest() {
        instance = new ProjectList();
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
     * Test of getActualProject method, of class ProjectList.
     */
    @Test
    public void testGetActualProject() {
        System.out.println("getActualProject");
        Project expResult = null;
        Project result = instance.getActualProject();
        assertEquals(expResult, result);//actualProject should be null at start of application
    }

    /**
     * Test of setActualProject method, of class ProjectList.
     */
    @Test
    public void testSetActualProject() {
        System.out.println("setActualProject");
        Project actualProject = new Project();
        instance.setActualProject(actualProject);
        assertEquals(actualProject, instance.getActualProject());
    }

    /**
     * Test of addProject method, of class ProjectList.
     */
    @Test
    public void testAddProject() {
        System.out.println("addProject");
        Project p = null;
        assertFalse("Thid project shouldn't be added, since it is null", instance.addProject(p));
        p = new Project();
        p.setName("Projeto principal");
        p.setDescription("Projeto inicial para testes");
        assertTrue("This project should be added", instance.addProject(p));
        assertFalse("This project shouldn't be added because it already exists", instance.addProject(p));
    }

    /**
     * Test of getAllNames method, of class ProjectList.
     */
    @Test
    public void testGetAllNames() {
        System.out.println("getAllNames");
        Project p1 = new Project();
        Project p2 = new Project();
        p1.setName("Proj1");
        p2.setName("Proj2");
        instance.addProject(p1);
        instance.addProject(p2);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Proj1");
        expResult.add("Proj2");
        ArrayList<String> result = instance.getAllNames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProject method, of class ProjectList.
     */
    @Test
    public void testGetProject() {
        System.out.println("getProject");
        String name = "Proj1";
        Project p1 = new Project();
        Project p2 = new Project();
        p1.setName("Proj1");
        p2.setName("Proj2");
        instance.addProject(p1);
        instance.addProject(p2);
        Project expResult = p1;
        Project result = instance.getProject(name);
        assertEquals(expResult, result);

    }
}
