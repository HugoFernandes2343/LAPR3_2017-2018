/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Energy;
import lapr.project.model.Gear;
import lapr.project.model.MostEfficientPathInEnergySavingModeAnalysis;
import lapr.project.model.Network;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.Throttle;
import lapr.project.model.TollFare;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleList;
import lapr.project.model.VelocityLimit;
import lapr.project.model.VelocityLimitList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class MostEfficientPathInEnergySavingModeAlgorithmTest {
    
    public MostEfficientPathInEnergySavingModeAlgorithmTest() {
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
     * Test of runAlgorithm method, of class MostEfficientPathInEnergySavingModeAlgorithm.
     */
    @Test
    public void testRunAlgorithm() {        
        System.out.println("runAlgorithm");
        VelocityLimitList velocityLimitList = new VelocityLimitList();
        VehicleList vehicles = new VehicleList();
        List<Segment> segments = new LinkedList<>();
        segments.add(new Segment("01", 100.0, 200.0, "1.2 Km", 20.0, "5 m/s", "90 Km/h", "0 Km/h"));
        
        
        List<Throttle> throttles = new LinkedList<>();
        List<Regime> regimes = new LinkedList<>();
        regimes.add(new Regime(125, 115, 900, 1499, 500.0));
        regimes.add(new Regime(120, 125, 1500, 2499, 450.0));
        regimes.add(new Regime(105, 120, 2500, 3499, 520.0));
        regimes.add(new Regime(90, 105, 3500, 3499, 550.0));
        regimes.add(new Regime(80, 90, 4500, 5500, 650.0));
        throttles.add(new Throttle("25", regimes));
        
        
        List<Regime> regimes1 = new LinkedList<>();
        regimes1.add(new Regime(195, 185, 900, 1499, 380.0));
        regimes1.add(new Regime(190, 195, 1500, 2499, 350.0));
        regimes1.add(new Regime(180, 190, 2500, 3999, 360.0));
        regimes1.add(new Regime(150, 180, 3500, 4499, 4000.0));
        regimes1.add(new Regime(135, 150, 4500, 5500, 520.0));
        throttles.add(new Throttle("50", regimes1));
        
        
        List<Regime> regimes2 = new LinkedList<>();
        regimes2.add(new Regime(325, 305, 900, 1499, 380.0));
        regimes2.add(new Regime(315, 125, 1500, 2499, 350.0));
        regimes2.add(new Regime(290, 315, 2500, 3999, 360.0));
        regimes2.add(new Regime(220, 290, 3500, 4499, 400.0));
        regimes2.add(new Regime(80, 90, 4500, 5500, 520.0));
        throttles.add(new Throttle("100", regimes2));
        
        
        List<Gear> gears = new LinkedList<>();
        gears.add(new Gear("01", 4.5));
        gears.add(new Gear("02", 3.5));
        gears.add(new Gear("03", 2.7));
        gears.add(new Gear("04", 1.6));
        gears.add(new Gear("05", 1.2));
        gears.add(new Gear("06", 0.9));
        
        
        Energy energy = new Energy(900, 5500, 4.0, gears, throttles);
        velocityLimitList.addVelocityLimit(new VelocityLimit("Highway", 110));
        velocityLimitList.addVelocityLimit(new VelocityLimit("Road", 80));
        Vehicle vehicle = new Vehicle("PickUp", "test", "car", 2, "combustion", "diesel", "2400 Kg", "1200 kg", 0.39, 2.4, 0.015, 0.8, energy, velocityLimitList);
        vehicles.addVehicle(vehicle);
        Project project = new Project();
        Node begin = new Node("n0");
        Node end = new Node("n2");
        Road road = new Road("E01", "regular road", "E01", new TollFare());
        RoadSection section = new RoadSection("n0", "n2", "E01", "bidirection", segments);
        Network network = new Network();
        network.addRoad(road);
        network.addNode("n0");
        network.addNode("n2");
        network.addRoadSection(section);
        network.loadMap();
        project.setVehicleList(vehicles);
        project.setNetwork(network);
        String name = "TestAnalysis";
        double load = 20.0;
        
        MostEfficientPathInEnergySavingModeAlgorithm instance = new MostEfficientPathInEnergySavingModeAlgorithm();
        NetworkAnalysis expResult = new MostEfficientPathInEnergySavingModeAnalysis(begin, end, vehicle, name);
        instance.setAceleratingAcceleration(0.5);
        instance.setBrakingAcceleration(-0.5);
        
        
        NetworkAnalysis result = instance.runAlgorithm(project, begin, end, vehicle, name, load);
        //assertEquals(expResult, result);
        
        System.out.println(result.getAverageVelocity());
        System.out.println(result.getEnergyConsumption());
        System.out.println(result.getTravellTime());
    }


    /**
     * Test of setAceleratingAcceleration method, of class MostEfficientPathInEnergySavingModeAlgorithm.
     */
    @Test
    public void testSetAceleratingAcceleration() {
        System.out.println("setAceleratingAcceleration");
        double aceleratingAcceleration = 0.0;
        MostEfficientPathInEnergySavingModeAlgorithm instance = new MostEfficientPathInEnergySavingModeAlgorithm();
        instance.setAceleratingAcceleration(aceleratingAcceleration);
    }

    /**
     * Test of setBrakingAcceleration method, of class MostEfficientPathInEnergySavingModeAlgorithm.
     */
    @Test
    public void testSetBrakingAcceleration() {
        System.out.println("setBrakingAcceleration");
        double brakingAcceleration = 0.0;
        MostEfficientPathInEnergySavingModeAlgorithm instance = new MostEfficientPathInEnergySavingModeAlgorithm();
        instance.setBrakingAcceleration(brakingAcceleration);
    }

    /**
     * Test of getAceleratingAcceleration method, of class MostEfficientPathInEnergySavingModeAlgorithm.
     */
    @Test
    public void testGetAceleratingAcceleration() {
        System.out.println("getAceleratingAcceleration");
        MostEfficientPathInEnergySavingModeAlgorithm instance = new MostEfficientPathInEnergySavingModeAlgorithm();
        double expResult = 0.0;
        double result = instance.getAceleratingAcceleration();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getBrakingAcceleration method, of class MostEfficientPathInEnergySavingModeAlgorithm.
     */
    @Test
    public void testGetBrakingAcceleration() {
        System.out.println("getBrakingAcceleration");
        MostEfficientPathInEnergySavingModeAlgorithm instance = new MostEfficientPathInEnergySavingModeAlgorithm();
        double expResult = 0.0;
        double result = instance.getBrakingAcceleration();
        assertEquals(expResult, result, 0.0);
    }
    
}
