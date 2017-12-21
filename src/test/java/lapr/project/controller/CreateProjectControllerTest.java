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
public class CreateProjectControllerTest {

    private CreateProjectController instanceWorking;
    private CreateProjectController instanceFail;

    public CreateProjectControllerTest() {
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
        this.instanceFail = new CreateProjectController(base);

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
        this.instanceWorking = new CreateProjectController(base2);
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
     * Test of createProject method, of class CreateProjectController.
     */
    @Test
    public void testCreateProject() {
        System.out.println("createProject");
        String name = "";
        String description = "";

        boolean expResult = false;
        boolean result = instanceWorking.createProject(name, description);
        assertEquals(expResult, result);

        String name2 = "testNoDesc";
        String description2 = "";

        boolean expResult2 = false;
        boolean result2 = instanceWorking.createProject(name2, description2);
        assertEquals(expResult2, result2);

        String name3 = "";
        String description3 = "testNoName";

        boolean expResult3 = false;
        boolean result3 = instanceWorking.createProject(name3, description3);
        assertEquals(expResult3, result3);

        String name4 = "testp4";
        String description4 = "desc";

        boolean expResult4 = false;
        boolean result4 = instanceWorking.createProject(name4, description4);
        assertEquals(expResult4, result4);

        String name5 = "test";
        String description5 = "desc";

        boolean expResult5 = true;
        boolean result5 = instanceWorking.createProject(name5, description5);
        assertEquals(expResult5, result5);

    }

    /**
     * Test of readInfo method, of class CreateProjectController.
     */
    @Test
    public void testReadInfo() {
        System.out.println("readInfo");

        String fileNetwork = ""; //NOME ERRADO DO FICHEIRO XML DAS NETWORKS
        String fileVehicleList = ""; //NOME ERRADO DO FICHEIRO XML DOS VEHICLES

        boolean expResult = false;
        boolean result = instanceWorking.readInfo(fileNetwork, fileVehicleList);
        assertEquals(expResult, result);

        String fileNetwork2 = "TestSet02_Network.xml"; // NOME CORRETO DO FICHEIRO XML DO ANGELO DAS NETWORKS
        String fileVehicleList2 = ""; //NOME ERRADO DO FICHEIRO XML DOS VEHICLES

        boolean expResult2 = false;
        boolean result2 = instanceWorking.readInfo(fileNetwork2, fileVehicleList2);
        assertEquals(expResult2, result2);

        String fileNetwork3 = ""; //NOME ERRADO DO FICHEIRO XML DAS NETWORKS
        String fileVehicleList3 = "TestSet02_Vehicles.xml"; // NOME CORRETO DO FICHEIRO XML DO ANGELO DOS VEHICLES

        boolean expResult3 = false;
        boolean result3 = instanceWorking.readInfo(fileNetwork3, fileVehicleList3);
        assertEquals(expResult3, result3);

        String fileNetwork4 = "TestSet02_Network.xml"; // NOME CORRETO DO FICHEIRO XML DO ANGELO DAS NETWORKS
        String fileVehicleList4 = "TestSet02_Vehicles.xml"; // NOME CORRETO DO FICHEIRO XML DO ANGELO DOS VEHICLES

        
        String name5 = "test";
        String description5 = "desc";

        instanceWorking.createProject(name5, description5);
        
        boolean expResult4 = true;
        boolean result4 = instanceWorking.readInfo(fileNetwork4, fileVehicleList4);
        assertEquals(expResult4, result4);

    }

    /**
     * Test of addProject method, of class CreateProjectController.
     */
    @Test
    public void testAddProject() {
        System.out.println("addProject");

        boolean expResult = false;
        boolean result = instanceFail.addProject();
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
        this.instanceWorking = new CreateProjectController(base2);

        String name5 = "test";
        String description5 = "desc";

        instanceWorking.createProject(name5, description5);

        boolean expResult2 = true;
        boolean result2 = instanceWorking.addProject();
        assertEquals(expResult2, result2);

    }

}
