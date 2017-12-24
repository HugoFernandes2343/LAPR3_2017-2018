/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.model.Network;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.ProjectList;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleList;
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
public class CreateAnalysisControllerTest {

    private CreateAnalysisController instanceWorking;
    private CreateAnalysisController instanceFail;

    public CreateAnalysisControllerTest() {
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
        this.instanceFail = new CreateAnalysisController(base);

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
        
        Vehicle v1 = new Vehicle("v1");
        Vehicle v2 = new Vehicle("v2");
        Vehicle v3 = new Vehicle("v3");
        Vehicle v4 = new Vehicle("v4");
        
        Set<Vehicle> vset = new HashSet<>();
        vset.add(v1);
        vset.add(v2);
        vset.add(v3);
        vset.add(v4);
        
        VehicleList vl = new VehicleList();
        vl.setVehicleList(vset);
        
        p4.setVehicleList(vl);
        
        Network net = new Network();
        
        List<Node> nl = net.getNodeList();
        
        Node n1 = new Node("test1");
        Node n2 = new Node("test2");
        Node n3 = new Node("test3");
        Node n4 = new Node("test4");
        
        nl.add(n1);
        nl.add(n2);
        nl.add(n3);
        nl.add(n4);
        
        p4.setNetwork(net);
        this.instanceWorking = new CreateAnalysisController(base2);
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
     * Test of getAlgorithmList method, of class CreateAnalysisController.
     */
    @Test
    public void testGetAlgorithmList() {
        System.out.println("getAlgorithmList");
        List<String> expResult = new ArrayList<>();
        expResult.add("Algorithm: Shortest Travell Time (N10)");
        List<String> result = instanceWorking.getAlgorithmList();
        assertEquals(expResult, result);
        
        List<String> expResult2 = new ArrayList<>();
        expResult2.add("Test Fail");
        List<String> result2 = instanceWorking.getAlgorithmList();
        assertFalse(expResult2 == result2);
    }

    /**
     * Test of getVehicleList method, of class CreateAnalysisController.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        
        List<String> result = instanceWorking.getVehicleList();
        assertTrue(result.size()==4);
        assertTrue(result.contains("v1"));
        assertTrue(result.contains("v2"));
        assertTrue(result.contains("v3"));
        assertTrue(result.contains("v4"));
        
    }

    /**
     * Test of getNodeList method, of class CreateAnalysisController.
     */
    @Test
    public void testGetNodeList() {
        System.out.println("getNodeList");
 
        List<String> result = instanceWorking.getNodeList();
        assertTrue(result.size()==4);
        assertTrue(result.contains("test1"));
        assertTrue(result.contains("test2"));
        assertTrue(result.contains("test3"));
        assertTrue(result.contains("test4"));
    }

    /**
     * Test of getAnalysis method, of class CreateAnalysisController.
     */
    @Test
    public void testGetAnalysis() {
        System.out.println("getAnalysis");
        NetworkAnalysis expResult = null;
        NetworkAnalysis result = instanceWorking.getAnalysis();
        assertEquals(expResult, result);
    }

}
