package lapr.project.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author DEI-ESINF
 */
public class GraphAlgorithmsTest {

    AdjacencyMatrixGraph<String, String> completeMap = new AdjacencyMatrixGraph<>();
    AdjacencyMatrixGraph<String, String> incompleteMap;

    public GraphAlgorithmsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto", "Aveiro", "A1");
        completeMap.insertEdge("Porto", "Braga", "A3");
        completeMap.insertEdge("Porto", "Vila Real", "A4");
        completeMap.insertEdge("Viseu", "Guarda", "A25");
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23");
        completeMap.insertEdge("Aveiro", "Coimbra", "A1");
        completeMap.insertEdge("Coimbra", "Lisboa", "A1");
        completeMap.insertEdge("Coimbra", "Leiria", "A34");
        completeMap.insertEdge("Aveiro", "Leiria", "A17");
        completeMap.insertEdge("Leiria", "Lisboa", "A8");

        incompleteMap = completeMap.clone();

        completeMap.insertEdge("Aveiro", "Viseu", "A25");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23");
        completeMap.insertEdge("Lisboa", "Faro", "A2");

    }

    @Test
    public void testDFS() {
        System.out.println("Test of DFS");

        List<String> path;
        path = GraphAlgorithms.depthFirstSearch(completeMap, "LX");
        assertTrue("Should return an empty list if vertex does not exist", path.isEmpty());

        path = GraphAlgorithms.depthFirstSearch(incompleteMap, "Faro");

        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();

        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.depthFirstSearch(completeMap, "Porto");

        assertTrue("Should give all vertices ", path.size() == 11);

        it = path.iterator();

        assertTrue("First in visit should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Vila Real", it.next().compareTo("Vila Real") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);

        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.depthFirstSearch(incompleteMap, "Viseu");

        assertTrue("Should give 3 vertices", path.size() == 3);

        it = path.iterator();

        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
    }

    @Test
    public void testBFS() {
        System.out.println("Test of BFS");

        List<String> path;
        path = GraphAlgorithms.breadthFirstSearch(completeMap, "LX");
        assertTrue("Should return an empty list if vertex does not exist", path.isEmpty());

        path = GraphAlgorithms.breadthFirstSearch(incompleteMap, "Faro");

        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();

        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.breadthFirstSearch(completeMap, "Porto");

        assertTrue("Should give all vertices ", path.size() == 11);

        it = path.iterator();

        assertTrue("First in visit should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Vila Real", it.next().compareTo("Vila Real") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);

        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

        path = GraphAlgorithms.breadthFirstSearch(incompleteMap, "Viseu");

        assertTrue("Should give 3 vertices", path.size() == 3);

        it = path.iterator();

        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

    }

    @Test
    public void testAllPaths() {

        System.out.println("Test of all paths");

        List<List<String>> paths = new LinkedList<>();

        assertFalse("Should be false if vertex does not exist",
                GraphAlgorithms.allPaths(completeMap, "Porto", "LX", paths));

        assertTrue("There should be paths between Porto and Lisboa in the map",
                GraphAlgorithms.allPaths(incompleteMap, "Porto", "Lisboa", paths));

        assertTrue("Should give 4 paths", paths.size() == 4);

        Iterator<List<String>> it = paths.iterator();

        // First path should be Porto, Aveiro, Coimbra, Leiria, Lisboa
        // Second path should be Porto, Aveiro, Coimbra, Lisboa
        // Third path should be Porto, Aveiro, Leiria, Coimbra, Lisboa
        // Fourth path shuold be Porto, Aveiro, Leiria, Lisboa
        String[][] pathsString = {{"Porto", "Aveiro", "Coimbra", "Leiria", "Lisboa"},
        {"Porto", "Aveiro", "Coimbra", "Lisboa"},
        {"Porto", "Aveiro", "Leiria", "Coimbra", "Lisboa"},
        {"Porto", "Aveiro", "Leiria", "Lisboa"}};

        for (int i = 0; i < 4; i++) {
            List<String> path = it.next();
            Iterator<String> cities = path.iterator();
            for (int j = 0; j < path.size(); j++) {
                assertTrue("City should be" + pathsString[i][j], cities.next().compareTo(pathsString[i][j]) == 0);
            }
        }

        GraphAlgorithms.allPaths(incompleteMap, "Porto", "Faro", paths);

        assertTrue("There should not be paths between Porto and Faro in the incomplete map", paths.size() == 0);
    }

    @Test
    public void testTransitiveClosure() {
        System.out.println("Test of transitive closure");

        AdjacencyMatrixGraph<String, String> instance = new AdjacencyMatrixGraph<>();
        for (int i = 1; i <= 5; i++) {
            instance.insertVertex("Vert " + i);
        }

        instance.insertEdge("Vert 1", "Vert 5", "Edge 1");
        instance.insertEdge("Vert 5", "Vert 4", "Edge 2");
        instance.insertEdge("Vert 3", "Vert 4", "Edge 3");
        instance.insertEdge("Vert 3", "Vert 2", "Edge 4");
        instance.insertEdge("Vert 2", "Vert 1", "Edge 5");

        AdjacencyMatrixGraph<String, String> test = GraphAlgorithms.transitiveClosure(instance, "Dummy");

        Iterator<String> direct = test.directConnections("Vert 2").iterator();
        
        assertTrue("The first vertes is \"Vert 1\"", direct.next().equals("Vert 1"));
        assertTrue("The second vertes is \"Vert 3\"", direct.next().equals("Vert 3"));
        assertTrue("The third vertes is \"Vert 4\"", direct.next().equals("Vert 4"));
        assertTrue("The fourth vertes is \"Vert 5\"", direct.next().equals("Vert 5"));
    }

}
