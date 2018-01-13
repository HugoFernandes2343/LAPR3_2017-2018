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
 * @author filip
 */
public class TheoreticalMostEnergyEfficientAlgorithmTest {

    public TheoreticalMostEnergyEfficientAlgorithmTest() {
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
     * Test of runAlgorithm method, of class
     * TheoreticalMostEnergyEfficientAlgorithm.
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

        TheoreticalMostEnergyEfficientAlgorithm algorithm = new TheoreticalMostEnergyEfficientAlgorithm();
        algorithm.setAceleratingAcceleration(0.5);
        algorithm.setBrakingAcceleration(-0.5);
        NetworkAnalysis analysis = algorithm.runAlgorithm(project, begin, end, vehicle, name, load);

        // Test the values of the analysis with one node.
        assertEquals(0.5, analysis.getAceleratingAcceleration(), 0.0);
        assertEquals(22.2, analysis.getAverageVelocity(), 0.1);
        assertEquals(415046.3, analysis.getEnergyConsumption(), 0.1);
        assertEquals(98.44444444444444, analysis.getTravellTime(), 0.1);

        //Add segments and nodes.
        section.addSegment(new Segment("02", 200.0, 150, "6.5 Km", -10.0, "2 m/s", "90 Km/h", "0 Km/h"));
        List<Segment> newSegmentList = new LinkedList<>();
        newSegmentList.add(new Segment("01", 150.0, 250.0, "1.5 Km", 120.0, "1.5 m/s", "90 Km/h", "0 Km/h"));
        Node newEnd = new Node("n3");
        RoadSection rs = new RoadSection("n2", "n3", "E01", "bidirectional", newSegmentList);
        network.addNode("n3");
        network.addRoadSection(rs);
        network.loadMap();
        analysis = algorithm.runAlgorithm(project, begin, newEnd, vehicle, name, load);
        assertEquals(0.5, analysis.getAceleratingAcceleration(), 0.0);
        assertEquals(22.2, analysis.getAverageVelocity(), 0.1);
        assertEquals(7646126.0402569175, analysis.getEnergyConsumption(), 0.1);
        assertEquals(458.4, analysis.getTravellTime(), 0.1);

        //Add segments and nodes and vehicles.
        List<Throttle> eletricThrottles = new LinkedList<>();
        List<Regime> eletricRegimes = new LinkedList<>();
        eletricRegimes.add(new Regime(175, 185, 0, 3499, null));
        eletricRegimes.add(new Regime(166, 175, 3500, 4499, null));
        eletricRegimes.add(new Regime(158, 166, 4500, 5499, null));
        eletricRegimes.add(new Regime(143, 158, 5500, 6499, null));
        eletricRegimes.add(new Regime(129, 143, 6500, 7499, null));
        eletricRegimes.add(new Regime(116, 129, 7500, 8499, null));
        eletricRegimes.add(new Regime(104, 116, 8500, 9499, null));
        eletricRegimes.add(new Regime(81, 104, 9500, 12500, null));
        eletricThrottles.add(new Throttle("25", eletricRegimes));

        List<Regime> eletricRegimes1 = new LinkedList<>();
        eletricRegimes1.add(new Regime(237, 250, 0, 3499, null));
        eletricRegimes1.add(new Regime(224, 237, 3500, 4499, null));
        eletricRegimes1.add(new Regime(213, 224, 4500, 5499, null));
        eletricRegimes1.add(new Regime(192, 213, 5500, 6499, null));
        eletricRegimes1.add(new Regime(174, 192, 6500, 7499, null));
        eletricRegimes1.add(new Regime(156, 174, 7500, 8499, null));
        eletricRegimes1.add(new Regime(140, 156, 8500, 9499, null));
        eletricRegimes1.add(new Regime(112, 140, 9500, 12500, null));
        eletricThrottles.add(new Throttle("50", eletricRegimes1));

        List<Regime> eletricRegimes2 = new LinkedList<>();
        eletricRegimes2.add(new Regime(332, 350, 0, 3499, null));
        eletricRegimes2.add(new Regime(315, 332, 3500, 4499, null));
        eletricRegimes2.add(new Regime(299, 315, 4500, 5499, null));
        eletricRegimes2.add(new Regime(270, 299, 5500, 6499, null));
        eletricRegimes1.add(new Regime(243, 270, 6500, 7499, null));
        eletricRegimes1.add(new Regime(222, 243, 7500, 8499, null));
        eletricRegimes1.add(new Regime(200, 222, 8500, 9499, null));
        eletricRegimes1.add(new Regime(160, 200, 9500, 12500, null));
        eletricThrottles.add(new Throttle("100", eletricRegimes2));

        List<Gear> eletricGears = new LinkedList<>();
        eletricGears.add(new Gear("01", 1));
        Energy e = new Energy(0, 12500, 10.6, eletricGears, eletricThrottles);
        e.setErr(0.8);
        Vehicle v = new Vehicle();
        v.setName("ElectricDummy");
        v.setDescription("Electric Dummy teste vehicle");
        v.setType("car");
        v.setTollClass(1);
        v.setMotorization("electric");
        v.setFuel("electric");
        v.setMass("1400 Kg");
        v.setLoad("420 Kg");
        v.setDrag(0.31);
        v.setFrontalArea(1.8);
        v.setRrc(0.01);
        v.setWheelSize(0.6);
        v.setEnergy(e);

        List<Segment> segmentList = new LinkedList<>();
        segmentList.add(new Segment("01", 250.0, 350.0, "10.5 Km", 40, "2.5 m/s", "90 Km/h", "0 Km/h"));
        segmentList.add(new Segment("02", 350.0, 550.0, "4 Km", -120, "2 m/s", "90 Km/h", "0 Km/h"));
        RoadSection rs1 = new RoadSection("n3", "n4", "E01", "bidirectional", segmentList);
        Node newEnd1 = new Node("n4");
        network.addNode("n4");
        network.addRoadSection(rs1);
        network.loadMap();
        analysis = algorithm.runAlgorithm(project, begin, newEnd1, v, name, load);
        assertEquals(0.5, analysis.getAceleratingAcceleration(), 0.0);
        assertEquals(25.0, analysis.getAverageVelocity(), 0.1);
        assertEquals(3597894.788373608, analysis.getEnergyConsumption(), 0.1);
        assertEquals(973.0, analysis.getTravellTime(), 0.1);

        //Test the same network with a different vehicle
        analysis = algorithm.runAlgorithm(project, begin, newEnd1, vehicle, name, load);
        assertEquals(0.5, analysis.getAceleratingAcceleration(), 0.0);
        assertEquals(22.2, analysis.getAverageVelocity(), 0.1);
        assertEquals(2.017540727868047E7, analysis.getEnergyConsumption(), 0.1);
        assertEquals(1088.7, analysis.getTravellTime(), 0.1);

    }

    /**
     * Test of toString method, of class
     * TheoreticalMostEnergyEfficientAlgorithm.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TheoreticalMostEnergyEfficientAlgorithm instance = new TheoreticalMostEnergyEfficientAlgorithm();
        String expResult = "Algorithm: Theoretical Most Energy Efficient Path N(11)";
        String result = instance.toString();
        assertTrue("The strings should be equal", result.equals(expResult));
    }

}
