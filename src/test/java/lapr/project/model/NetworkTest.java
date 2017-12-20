/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.AdjacencyMatrixGraph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class NetworkTest {

    private final Network instance;

    public NetworkTest() {
        instance = new Network();
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
     * Test of getId method, of class Network.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");

        String expResult = "TestNetwork";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Network.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");

        String expResult = "test";
        String result = instance.getDescription();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Network.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        Network instanceTest = new Network();
        int expResult = instanceTest.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Network.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Network();

        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));
        obj = "test";
        assertEquals("Should be false if they are not from the same class", false, instance.equals(obj));
    }

    /**
     * Test of toString method, of class Network.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "Network{id=TestNetwork}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Network.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";

        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of addNewRoadsFromNetwork method, of class Network.
     */
    @Test
    public void testAddNewRoadsFromNetwork() { // LOOK THESE TESTS OVER WITH TEAM
        System.out.println("addNewRoadsFromNetwork");
        Network roadsToAdd = null;

        assertFalse("If we pass a new network null it should be false", instance.addNewRoadsFromNetwork(roadsToAdd));

        roadsToAdd = new Network();
        assertFalse("If we pass a new network is empty it should be false", instance.addNewRoadsFromNetwork(roadsToAdd));

        Road r1 = new Road("A1", "Highway", "Tests");
        Road r2 = new Road("A1", "Highway", "Tests1");
        Road r3 = new Road("A1", "Highway", "Tests2");
        Road r4 = new Road("A1", "Highway", "Tests3");
        Road r5 = new Road("A1", "Highway", "Tests4");
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        RoadSection rs1 = new RoadSection("1", "2", "Tests", "South", new LinkedList<>());
        Network networkAdd = new Network();
        networkAdd.addRoad(r1);
        networkAdd.addNode("1");
        networkAdd.addNode("2");
        networkAdd.addRoadSection(rs1);
        networkAdd.loadMap();
        instance.addRoad(r1);
        instance.addNode("1");
        instance.addNode("2");
        instance.addRoadSection(rs1);
        instance.loadMap();
        assertFalse("Since there is already rs1 it should be false", instance.addNewRoadsFromNetwork(roadsToAdd));

        RoadSection rs2 = new RoadSection("3", "4", "Tests", "South", new LinkedList<>());
        networkAdd.addRoadSection(rs1);
        networkAdd.addNode("3");
        networkAdd.addNode("4");
        networkAdd.addRoad(r2);
        networkAdd.addRoadSection(rs2);
        networkAdd.loadMap();
        assertTrue("The second RoadSection2 should be added", instance.addNewRoadsFromNetwork(networkAdd));
        List<Node> nodes = instance.getRoadMap().endVertices(rs2);
        assertTrue("One of the vertices should be n1", nodes.get(0).equals(n3));
        assertTrue("One of the vertices should be n2", nodes.get(1).equals(n4));
    }

    /**
     * Test of setDescription method, of class Network.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Tests";

        instance.setDescription(description);
        assertTrue("The new description should be 'Tests'", instance.getDescription().equals("Tests"));
    }

    /**
     * Test of addNode method, of class Network.
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        String text = "Tests";

        assertTrue("This node should be added", instance.addNode(text));
        assertFalse("This node shouldn't be added", instance.addNode(text));
    }

    /**
     * Test of addRoad method, of class Network.
     */
    @Test
    public void testAddRoad() {
        System.out.println("addRoad");
        Road road = new Road();
        road.setId("Tests");

        assertTrue("This road should be added", instance.addRoad(road));
        assertFalse("This road shouldn't be added", instance.addRoad(road));
    }

    /**
     * Test of addRoadSection method, of class Network.
     */
    @Test
    public void testAddRoadSection() {
        System.out.println("addRoadSection");
        RoadSection section = new RoadSection();
        section.setBegin("Tests1");
        section.setEnd("Tests2");

        assertTrue("This road section should be added", instance.addRoadSection(section));
        assertFalse("This road section shouldn't be added", instance.addRoadSection(section));
    }

    /**
     * Test of getSection_list method, of class Network.
     */
    @Test
    public void testGetSectionList() {
        System.out.println("getSection_list");

        assertTrue("The list should be empty", instance.getSectionList().isEmpty());
        instance.getSectionList().add(new RoadSection());
        assertFalse("The list shouldn't be empty now", instance.getSectionList().isEmpty());
    }

    /**
     * Test of loadMap method, of class Network.
     */
    @Test
    public void testLoadMap() { // LOOK THESE TESTS OVER WITH TEAM
        System.out.println("loadMap");

        Road r1 = new Road("A1", "Highway", "Tests");
        Road r2 = new Road("A1", "Highway", "Tests1");
        Road r3 = new Road("A1", "Highway", "Tests2");
        Road r4 = new Road("A1", "Highway", "Tests3");
        Road r5 = new Road("A1", "Highway", "Tests4");
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        RoadSection rs1 = new RoadSection("1", "2", "Tests", "South", new LinkedList<>());
        instance.loadMap();
        assertTrue("Since none of the elementes where put in the matrix it should be null", instance.getRoadMap().endVertices(rs1) == null);
        instance.addRoad(r1);
        instance.addRoad(r2);
        instance.addRoad(r3);
        instance.addRoad(r4);
        instance.addRoad(r5);
        instance.addNode("1");
        instance.addNode("2");
        instance.addNode("3");
        instance.addNode("4");
        instance.addNode("5");
        instance.addRoadSection(rs1);
        instance.loadMap();

        List<Node> nodes = instance.getRoadMap().endVertices(rs1);
        assertTrue("One of the vertices should be n1", nodes.get(0).equals(n1));
        assertTrue("One of the vertices should be n2", nodes.get(1).equals(n2));
    }

    /**
     * Test of getRoadMap method, of class Network.
     */
    @Test
    public void testGetRoadMap() {
        System.out.println("getRoadMap");

        AdjacencyMatrixGraph<Node, RoadSection> result = instance.getRoadMap();
        assertTrue("The matrix should be empty", result.numEdges() == 0 && result.numVertices() == 00);

    }

    /**
     * Test of getNodeList method, of class Network.
     */
    @Test
    public void testGetNodeList() {
        System.out.println("getNodeList");

        List<Node> expResult = new LinkedList<>();
        List<Node> result = instance.getNodeList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getNodesByName method, of class Network.
     */
    @Test
    public void testGetNodesByName() {
        System.out.println("getNodesByName");

        List<Node> nodes = new Network().getNodeList();
        List<String> expResult = new LinkedList<>();
        Iterator<Node> itr = nodes.iterator();
        while (itr.hasNext()) {
            expResult.add(itr.next().getId());
        }
        List<String> result = instance.getNodesByName();
        assertEquals(expResult, result);

    }
}
