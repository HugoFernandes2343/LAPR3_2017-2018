/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class ProjectListTest {

    private ProjectList instance;

    public ProjectListTest() {
        instance = new ProjectList();
    }

    /**
     * Test of getProjects method, of class ProjectList.
     */
    @Test
    public void testGetProjects() {
        System.out.println("getProjects");
        Set<Project> expResult = new HashSet<>();
        Set<Project> result = instance.getProjects();
        assertEquals(expResult, result);//sets should be empty at the start of application
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
}
