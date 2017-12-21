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
public class AddRoadsControllerTest {

    private AddRoadsController instanceFail;
    private AddRoadsController instanceWorking;

    public AddRoadsControllerTest() {
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
        this.instanceFail = new AddRoadsController(base);

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
        this.instanceWorking = new AddRoadsController(base2);
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
     * Test of addNewRoads method, of class AddRoadsController.
     */
    @Test
    public void testAddNewRoads() {
        System.out.println("addNewRoads");
        String fileRoadNoetwork = "TestSet02_Network.xml"; // NOME DO FICHEIRO DO ANGELO DAS NETWOEKS AQUI

        boolean expResult = false;
        boolean result = instanceFail.addNewRoads(fileRoadNoetwork);
        assertEquals(expResult, result);
        
        
        boolean expResult2 = true;
        boolean result2 = instanceWorking.addNewRoads(fileRoadNoetwork);
        assertEquals(expResult2, result2);
       
    }

}
