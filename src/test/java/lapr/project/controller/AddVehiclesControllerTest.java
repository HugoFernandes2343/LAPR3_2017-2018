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
public class AddVehiclesControllerTest {
      
    private AddVehiclesController instanceFail;
    private AddVehiclesController instanceWorking;

    public AddVehiclesControllerTest() {
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
        pl.setActualProject(null);
        this.instanceFail = new AddVehiclesController(base);

        Project p4 = new Project();
        p4.setName("testp4");
        Project p5 = new Project();
        p5.setName("testp5");
        Project p6 = new Project();
        p6.setName("testp6");

        TravelByPhysics base2 = new TravelByPhysics();
        ProjectList pl2 = base2.getProjectList();
        pl2.addProject(p4);
        pl2.addProject(p5);
        pl2.addProject(p6);
        pl2.setActualProject(p4);
        this.instanceWorking = new AddVehiclesController(base2);
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
     * Test of getActiveProjectData method, of class AddVehiclesController.
     */
    @Test
    public void testGetActiveProjectData() {
        System.out.println("getActiveProjectData");
       
        boolean expResult = false;
        boolean result = instanceFail.getActiveProjectData();
        assertEquals(expResult, result);
        
        boolean expResult2 = true;
        boolean result2 = instanceWorking.getActiveProjectData();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of addVehicles method, of class AddVehiclesController.
     */
    @Test
    public void testAddVehicles() {
        System.out.println("addVehicles");
        String filename = "TestSet02_Vehicles.xml"; //NOME DO FICHEIRO DO ANGELO DOS VEICULOS
      
        boolean expResult = false;
        boolean result = instanceFail.addVehicles(filename);
        assertEquals(expResult, result);
        
        Project p4 = new Project();
        p4.setName("testp4");
        Project p5 = new Project();
        p5.setName("testp5");
        Project p6 = new Project();
        p6.setName("testp6");

        TravelByPhysics base2 = new TravelByPhysics();
        ProjectList pl2 = base2.getProjectList();
        pl2.addProject(p4);
        pl2.addProject(p5);
        pl2.addProject(p6);
        pl2.setActualProject(p4);
        this.instanceWorking = new AddVehiclesController(base2);
        instanceWorking.getActiveProjectData();
        boolean expResult2 = true;
        boolean result2 = instanceWorking.addVehicles(filename);
        assertEquals(expResult2, result2);
       
    }
    
}
