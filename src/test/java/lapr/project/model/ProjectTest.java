/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;
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
        instance.setVehicleList(vehicleList);
        assertTrue("If we get the vehicle list it should be null", instance.getVehicleList() == null);
        vehicleList = new VehicleList();
        instance.setVehicleList(vehicleList);
        assertTrue("If we get the vehicle list it should be empty", instance.getVehicleList().getVehicleList().isEmpty());
    }

    /**
     * Test of equals method, of class Project, false case not the same project.
     */
    @Test
    public void testEqualsFalseCaseName() {
        System.out.println("equals");
        Project test = new Project();
        test.setName("test");
        boolean expResult = false;
        boolean result = instance.equals(test);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Project false case not the same class.
     */
    @Test
    public void testEqualsFalseCaseClass() {
        System.out.println("equals");
        Object obj = "test";
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Project false case not the same class.
     */
    @Test
    public void testEqualsFalseCaseNull() {
        System.out.println("equals");
        Object obj = null;
        boolean result = instance.equals(obj);

        assertEquals("Should be false because obj is null", false, result);

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
     * Test of getVehicleList method, of class Project.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        VehicleList result = instance.getVehicleList();
        assertTrue("The list returned should be empty", result.getVehicleList().isEmpty());
    }

    /**
     * Test of getRoadNetwork method, of class Project.
     */
    @Test
    public void testGetNetwork() {
        System.out.println("getRoadNetwork");
        Network result = instance.getNetwork();
        Network expresult = new Network();
        assertEquals(result, expresult);
    }

    /**
     * Test of setNetwork method, of class Project.
     */
    @Test
    public void testSetNetwork() {
        System.out.println("setNetwork");
        Network network = null;
        instance.setNetwork(network);
        assertTrue("If we get the network it should be null", instance.getNetwork() == null);
        network = new Network();
        instance.setNetwork(network);

        assertEquals(network, instance.getNetwork());
    }

    /**
     * Test of getNetworkAnalysis method, of class Project.
     */
    @Test
    public void testGetNetworkAnalysis() {
        System.out.println("getNetworkAnalysis");
        List<NetworkAnalysis> result = instance.getNetworkAnalysis();
        assertTrue(result.isEmpty());
     
    }

    /**
     * Test of setNetworkAnalysis method, of class Project.
     */
    @Test
    public void testSetNetworkAnalysis() {
        System.out.println("setNetworkAnalysis");
        LinkedList<NetworkAnalysis> netList = new LinkedList<>();
        NetworkAnalysis net = new NetworkAnalysis("test") {};
        netList.add(net);
        instance.setNetworkAnalysis(netList);
        assertTrue(net.getType().equalsIgnoreCase(instance.getNetworkAnalysis().get(0).getType()));
    }

    /**
     * Test of getDBData method, of class Project.
     */
    @Test
    public void testGetDBData() {
        System.out.println("getDBData");
        List<DatabaseExchangable> expResult = new LinkedList<>();
        expResult.add(instance);
        List<DatabaseExchangable> result = instance.getDBData();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of addNetworkAnalysis method, of class Project.
     */
    @Test
    public void testAddNetworkAnalysis() {
        System.out.println("addNetworkAnalysis");
        NetworkAnalysis net = new NetworkAnalysis("testType");
        instance.addNetworkAnalysis(net);
        assertTrue(instance.getNetworkAnalysis().get(0).getType().equalsIgnoreCase("testType"));
        
    }

    /**
     * Test of getNetAnalByName method, of class Project.
     */
    @Test
    public void testGetNetAnalByName() {
        System.out.println("getNetAnalByName");
        NetworkAnalysis netAnal = new NetworkAnalysis("testType");
        netAnal.setName("test");
        instance.getNetworkAnalysis().add(netAnal);
        NetworkAnalysis expResult = netAnal;
        NetworkAnalysis result = instance.getNetAnalByName("test");
        assertEquals(expResult, result);
        expResult = null;
        result = instance.getNetAnalByName("testfail");
        assertEquals(expResult,result);
    }
}
