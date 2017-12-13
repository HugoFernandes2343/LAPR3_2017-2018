/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Hugo
 */
public class ProjectTest {

    private Project instance;

    public ProjectTest() {
        instance = new Project();
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
     * Test of getName method, of class Project.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "n/a";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Project.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Project 1";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getDescription method, of class Project.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "n/a";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Project.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "This is a test Project";
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of hashCode method, of class Project.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should always be the same when its the same object
    }

    /**
     * Test of equals method, of class Project false case.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = "test";
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Project false case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new Project();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Project.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Project: n/a";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicleList method, of class Project.
     */
    @Test
    public void testSetVehicleList() {
        System.out.println("setVehicleList");
        VehicleList vehicleList = null;
        Project instance = new Project();
        instance.setVehicleList(vehicleList);
        
        assertTrue("If we get the vehicle list it should be null", instance.getVehicleList() == null);
        vehicleList = new VehicleList();
        instance.setVehicleList(vehicleList);
        assertTrue("If we get the vehicle list it should be empty", instance.getVehicleList().getVehicles().isEmpty());
    }

    /**
     * Test of setRoadNetwork method, of class Project.
     */
    @Test
    public void testSetRoadNetwork() {
        System.out.println("setRoadNetwork");
        RoadNetwork roadNetwork = null;
        Project instance = new Project();
        instance.setRoadNetwork(roadNetwork);
        
         assertTrue("If we get the vehicle list it should be null", instance.getRoadNetwork() == null);
        roadNetwork = new RoadNetwork();
        instance.setRoadNetwork(roadNetwork);
        assertTrue("If we get the vehicle list it should be empty", instance.getRoadNetwork().getRoadMap().numEdges() == 0 && instance.getRoadNetwork().getRoadMap().numVertices() == 0);
    }

    /**
     * Test of equals method, of class Project.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Project instance = new Project();
        Project instance1 = new Project();
        
        assertTrue("This two instances should be equal since their names are equal", instance.equals(instance1));
        instance1.setName("Principal Project");
        assertFalse("This two instances shouldn't be equal since their names are different", instance.equals(instance1));
    }

    /**
     * Test of getVehicleList method, of class Project.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        Project instance = new Project();
        VehicleList result = instance.getVehicleList();
        assertTrue("The list returned should be empty", result.getVehicles().isEmpty());
    }

    /**
     * Test of getRoadNetwork method, of class Project.
     */
    @Test
    public void testGetRoadNetwork() {
        System.out.println("getRoadNetwork");
        Project instance = new Project();
        RoadNetwork result = instance.getRoadNetwork();
        assertTrue("The list returned should be empty", result.getRoadMap().numEdges() == 0 && result.getRoadMap().numVertices() == 0);
    }

}
