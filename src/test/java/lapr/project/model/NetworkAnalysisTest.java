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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hugod
 */
public class NetworkAnalysisTest {

    private  NetworkAnalysis instance;
    private  NetworkAnalysis instanceType;

    public NetworkAnalysisTest() {
        Node beginNode = new Node("testBegin");
        Node endNode = new Node("testEnd");

        Energy e = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        VelocityLimitList vl = new VelocityLimitList();
        Vehicle vehicle = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);

        instanceType = new NetworkAnalysis("testType") {
        };
        instance = new NetworkAnalysis(beginNode, endNode, vehicle, "testAnalysis", "testType") {
        };
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
     * Test of getId method, of class NetworkAnalysis.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int result = instance.getId();
        assertTrue(result >= 0);

    }

    /**
     * Test of getName method, of class NetworkAnalysis.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "testAnalysis";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeginNode method, of class NetworkAnalysis.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        instance.setName("test");
        assertTrue(instance.getName().equalsIgnoreCase("test"));
    }    
    
    /**
     * Test of getBestPath method, of class NetworkAnalysis.
     */
    @Test
    public void testGetBestPath() {
        System.out.println("getBestPath");

        List<RoadSection> result = instance.getBestPath();
        assertTrue(result.isEmpty());

    }

    /**
     * Test of setBestPath method, of class NetworkAnalysis.
     */
    @Test
    public void testSetBestPath() {
        System.out.println("setBestPath");
        List<RoadSection> bestPath = new LinkedList<>();
        List<Segment> segment_list = new LinkedList<>();
        RoadSection rs1 = new RoadSection("begin", "end", "A01", "positive", segment_list);
        bestPath.add(rs1);
        instance.setBestPath(bestPath);
        assertTrue(!instance.getBestPath().isEmpty());
    }

    /**
     * Test of getVelocityPerSegment method, of class NetworkAnalysis.
     */
    @Test
    public void testGetVelocityPerSegment() {
        System.out.println("getVelocityPerSegment");
        List<Double> result = instance.getVelocityPerSegment();
        assertTrue(result.isEmpty());
    }

    /**
     * Test of setVelocityPerSegment method, of class NetworkAnalysis.
     */
    @Test
    public void testSetVelocityPerSegment() {
        System.out.println("setVelocityPerSegment");
        List<Double> velocityPerSegment = new LinkedList<>();
        velocityPerSegment.add(1.1);
        velocityPerSegment.add(1.2);
        velocityPerSegment.add(1.3);
        velocityPerSegment.add(1.4);
        velocityPerSegment.add(1.5);
        instance.setVelocityPerSegment(velocityPerSegment);
        assertFalse(instance.getVelocityPerSegment().isEmpty());
    }

    /**
     * Test of getVehicle method, of class NetworkAnalysis.
     */
    @Test
    public void testGetVehicle() {
        System.out.println("getVehicle");
        Energy e = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        VelocityLimitList vl = new VelocityLimitList();
        Vehicle expResult = new Vehicle("mazda", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of setVehicle method, of class NetworkAnalysis.
     */
    @Test
    public void testSetVehicle() {
        System.out.println("setVehicle");
        Energy e = new Energy(5, 10, 25.0, new LinkedList<>(), new LinkedList<>());
        VelocityLimitList vl = new VelocityLimitList();
        Vehicle vehicle = new Vehicle("TestSet", "car", "car", 1, "Combustion", "Gas", "300.0", "4.0", 12.0, 10, 2.50, 0.30, e, vl);
        instance.setVehicle(vehicle);
        assertEquals(instance.getVehicle().getName(), "TestSet");
    }

    /**
     * Test of getBeginNode method, of class NetworkAnalysis.
     */
    @Test
    public void testGetBeginNode() {
        System.out.println("getBeginNode");
        Node expResult = new Node("testBegin");
        Node result = instance.getBeginNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeginNode method, of class NetworkAnalysis.
     */
    @Test
    public void testSetBeginNode() {
        System.out.println("setBeginNode");
        Node beginNode = new Node("testSetNode");
        instance.setBeginNode(beginNode);
        assertTrue(instance.getBeginNode().getId().equalsIgnoreCase("testSetNode"));
    }

    /**
     * Test of getEndNode method, of class NetworkAnalysis.
     */
    @Test
    public void testGetEndNode() {
        System.out.println("getEndNode");
        Node expResult = new Node("testEnd");
        Node result = instance.getEndNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndNode method, of class NetworkAnalysis.
     */
    @Test
    public void testSetEndNode() {
        System.out.println("setEndNode");
        Node endNode = new Node("testSetNode");
        instance.setEndNode(endNode);
        assertTrue(instance.getEndNode().getId().equalsIgnoreCase("testSetNode"));
    }

    /**
     * Test of getType method, of class NetworkAnalysis.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "testType";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class NetworkAnalysis.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "testSetType";
        instance.setType(type);
        assertTrue(instance.getType().equalsIgnoreCase(type));
    }

    /**
     * Test of getTravellTime method, of class NetworkAnalysis.
     */
    @Test
    public void testGetTravellTime() {
        System.out.println("getTravellTime");
        double expResult = 0.0;
        double result = instance.getTravellTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTravellTime method, of class NetworkAnalysis.
     */
    @Test
    public void testSetTravellTime() {
        System.out.println("setTravellTime");
        double travellTime = 2.0;
        instance.setTravellTime(travellTime);
        assertEquals(travellTime, instance.getTravellTime(), 0.0);
    }

    /**
     * Test of getEnergyConsumption method, of class NetworkAnalysis.
     */
    @Test
    public void testGetEnergyConsumption() {
        System.out.println("getEnergyConsumption");
        double expResult = 0.0;
        double result = instance.getEnergyConsumption();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setEnergyConsumption method, of class NetworkAnalysis.
     */
    @Test
    public void testSetEnergyConsumption() {
        System.out.println("setEnergyConsumption");
        double energyConsumption = 2.0;
        instance.setEnergyConsumption(energyConsumption);
        assertEquals(energyConsumption, instance.getEnergyConsumption(), 0.0);
    }

    /**
     * Test of getAverageVelocity method, of class NetworkAnalysis.
     */
    @Test
    public void testGetAverageVelocity() {
        System.out.println("getAverageVelocity");
        double expResult = 0.0;
        double result = instance.getAverageVelocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAverageVelocity method, of class NetworkAnalysis.
     */
    @Test
    public void testSetAverageVelocity() {
        System.out.println("setAverageVelocity");
        double averageVelocity = 0.0;
        instance.setAverageVelocity(averageVelocity);
        assertEquals(averageVelocity, instance.getAverageVelocity(), 0.0);
    }

    /**
     * Test of getDistance method, of class NetworkAnalysis.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        double expResult = 0.0;
        double result = instance.getDistance();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDistance method, of class NetworkAnalysis.
     */
    @Test
    public void testSetDistance() {
        System.out.println("setDistance");
        double distance = 2.0;
        instance.setDistance(distance);
        assertEquals(distance, instance.getDistance(), 0.0);
    }

    /**
     * Test of getTollCost method, of class NetworkAnalysis.
     */
    @Test
    public void testGetTollCost() {
        System.out.println("getTollCost");
        double expResult = 0.0;
        double result = instance.getTollCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTollCost method, of class NetworkAnalysis.
     */
    @Test
    public void testSetTollCost() {
        System.out.println("setTollCost");
        double tollCost = 2.0;
        instance.setTollCost(tollCost);
        assertEquals(tollCost, instance.getTollCost(), 0.0);
    }

    public class NetworkAnalysisImpl extends NetworkAnalysis {

        public NetworkAnalysisImpl() {
            super("");
        }
    }

    /**
     * Test of getForcePerSegment method, of class NetworkAnalysis.
     */
    @Test
    public void testGetForcePerSegment() {
        System.out.println("getForcePerSegment");
        List<Double> result = instance.getForcePerSegment();
        assertTrue(result.isEmpty());

    }

    /**
     * Test of setForcePerSegment method, of class NetworkAnalysis.
     */
    @Test
    public void testSetForcePerSegment() {
        System.out.println("setForcePerSegment");
        List<Double> forcePerSegment = new LinkedList<>();
        Double d1 = 2.1;
        Double d2 = 2.2;
        Double d3 = 2.3;
        Double d4 = 2.4;
        Double d5 = 2.5;
        forcePerSegment.add(d1);
        forcePerSegment.add(d2);
        forcePerSegment.add(d3);
        forcePerSegment.add(d4);
        forcePerSegment.add(d5);

        instance.setForcePerSegment(forcePerSegment);

        List<Double> result = instance.getForcePerSegment();
        assertEquals(forcePerSegment, result);

    }

    /**
     * Test of getPower method, of class NetworkAnalysis.
     */
    @Test
    public void testGetPower() {
        System.out.println("getPower");
        instance.setLoad(2.0);
        double expResult = 2.0;
        double result = instance.getLoad();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setPower method, of class NetworkAnalysis.
     */
    @Test
    public void testSetPower() {
        System.out.println("setPower");
        double power = 5.0;
        instance.setLoad(power);
        assertTrue(instance.getLoad() == power);
    }

    /**
     * Test of getLoad method, of class NetworkAnalysis.
     */
    @Test
    public void testGetLoad() {
        System.out.println("getLoad");
        instance.setLoad(3);
        double expResult = 3.0;
        double result = instance.getLoad();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setLoad method, of class NetworkAnalysis.
     */
    @Test
    public void testSetLoad() {
        System.out.println("setLoad");
        double load = 2.0;

        instance.setLoad(load);
        assertTrue(instance.getLoad() == load);

    }

    /**
     * Test of getFuelMass method, of class NetworkAnalysis.
     */
    @Test
    public void testGetFuelMass() {
        System.out.println("getFuelMass");
        instance.setFuelMass(3);
        double expResult = 3.0;
        double result = instance.getFuelMass();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setFuelMass method, of class NetworkAnalysis.
     */
    @Test
    public void testSetFuelMass() {
        System.out.println("setFuelMass");
        double fuelMass = 4.0;
        instance.setFuelMass(fuelMass);
        assertTrue(instance.getFuelMass() == fuelMass);
    }

    /**
     * Test of getFuelVolume method, of class NetworkAnalysis.
     */
    @Test
    public void testGetFuelVolume() {
        System.out.println("getFuelVolume");
        instance.setFuelVolume(7);
        double expResult = 7.0;
        double result = instance.getFuelVolume();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setFuelVolume method, of class NetworkAnalysis.
     */
    @Test
    public void testSetFuelVolume() {
        System.out.println("setFuelVolume");
        double fuelVolume = 6.0;
        instance.setFuelVolume(fuelVolume);
        assertTrue(instance.getFuelVolume() == fuelVolume);
    }

    /**
     * Test of getAceleratingAcceleration method, of class NetworkAnalysis.
     */
    @Test
    public void testGetAceleratingAcceleration() {
        System.out.println("getAceleratingAcceleration");
        double expResult = 22.2;
        instance.setAceleratingAcceleration(22.2);
        double result = instance.getAceleratingAcceleration();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getBrakingAcceleration method, of class NetworkAnalysis.
     */
    @Test
    public void testGetBrakingAcceleration() {
        System.out.println("getBrakingAcceleration");
        double expResult = 22.2;
        instance.setBrakingAcceleration(22.2);
        double result = instance.getBrakingAcceleration();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAceleratingAcceleration method, of class NetworkAnalysis.
     */
    @Test
    public void testSetAceleratingAcceleration() {
        System.out.println("setAceleratingAcceleration");
        double aceleratingAcceleration = 5.0;
        instance.setAceleratingAcceleration(aceleratingAcceleration);
        assertTrue(instance.getAceleratingAcceleration()== aceleratingAcceleration);
    }

    /**
     * Test of setBrakingAcceleration method, of class NetworkAnalysis.
     */
    @Test
    public void testSetBrakingAcceleration() {
        System.out.println("setBrakingAcceleration");
        double brakingAcceleration = 5.0;
        instance.setBrakingAcceleration(brakingAcceleration);
        assertTrue(instance.getBrakingAcceleration() == brakingAcceleration);
    }

    /**
     * Test of setId method, of class NetworkAnalysis.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 3;
        instance.setId(id);
        assertTrue(instance.getId()== id);
    }
}
