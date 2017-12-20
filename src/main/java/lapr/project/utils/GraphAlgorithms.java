package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of graph algorithms for a (undirected) graph structure
 * Considering generic vertex V and edge E types
 *
 * Works on AdjancyMatrixGraph objects
 *
 * @author DEI-ESINF
 *
 */
public final class GraphAlgorithms {

    /**
     * Private constructor to hide implicit one.
     */
    private GraphAlgorithms() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Performs depth-first search of the graph starting at vertex. Calls
     * package recursive version of the method.
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if
     * vertex does not exist
     *
     */
    public static <V, E> List<V> depthFirstSearch(AdjacencyMatrixGraph<V, E> graph, V vertex) {
        List<V> resultQueue = new LinkedList<>();
        int index = graph.toIndex(vertex);
        if (index == -1) {
            return resultQueue;
        }

        resultQueue.add(vertex);
        boolean[] knownVertices = new boolean[graph.numVertices];
        GraphAlgorithms.depthFirstSearch(graph, index, knownVertices, resultQueue);
        return resultQueue;
    }

    /**
     * Actual depth-first search of the graph starting at vertex. The method
     * adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param graph Graph object
     * @param index Index of vertex of graph that will be the source of the
     * search
     * @param known previously discovered vertices
     * @param verticesQueue queue of vertices found by search
     *
     */
    static <V, E> void depthFirstSearch(AdjacencyMatrixGraph<V, E> graph, int index, boolean[] knownVertices, List<V> verticesQueue) {
        knownVertices[index] = true;
        for (int i = 0; i < graph.numVertices; i++) {
            if (graph.edgeMatrix[index][i] != null && !knownVertices[i]) {
                verticesQueue.add(graph.vertices.get(i));
                GraphAlgorithms.depthFirstSearch(graph, i, knownVertices, verticesQueue);
            }
        }
    }

    /**
     * Performs breath-first search of the graph starting at vertex. The method
     * adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if
     * vertex does not exist
     *
     */
    public static <V, E> List<V> breadthFirstSearch(AdjacencyMatrixGraph<V, E> graph, V vertex) {
        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();
        if (!graph.checkVertex(vertex)) {
            return qbfs;
        }


        qbfs.add(vertex);
        qaux.add(vertex);

        while (!qaux.isEmpty()) {
            V temp = qaux.peek();
            qaux.removeFirst();

            for (V vertexes : graph.directConnections(temp)) {
                if (!qbfs.contains(vertexes)) {
                    qbfs.add(vertexes);
                    qaux.add(vertexes);
                }
            }
        }
        return qbfs;
    }

    /**
     * All paths between two vertices Calls recursive version of the method.
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param source Source vertex of path
     * @param dest Destination vertex of path
     * @param paths
     * @return false if vertices not in the graph
     *
     */
    public static <V, E> boolean allPaths(AdjacencyMatrixGraph<V, E> graph, V source, V dest, List<List<V>> paths) {
        if (!graph.checkVertex(source) || !graph.checkVertex(dest)) { //vamos verificar a existencia dos vertices
            return false;
        }

        paths.clear();
        boolean[] knownVertices = new boolean[graph.numVertices];//criar o array fixo de indices
        LinkedList<V> auxStack = new LinkedList<>();//criar a stack auxiliar

        GraphAlgorithms.allPaths(graph, graph.vertices.indexOf(source), graph.vertices.indexOf(dest), knownVertices, auxStack, paths);

        return !paths.isEmpty();
    }

    /**
     * Actual paths search The method adds vertices to the current path (stack
     * of vertices) when destination is found, the current path is saved to the
     * list of paths
     *
     * @param graph Graph object
     * @param sourceIdx Index of source vertex
     * @param destIdx Index of destination vertex
     * @param knownVertices previously discovered vertices
     * @param auxStack stack of vertices in the path
     * @param path LinkedList with paths (queues)
     *
     */
    static <V, E> void allPaths(AdjacencyMatrixGraph<V, E> graph, int sourceIdx, int destIdx, boolean[] knownVertices, LinkedList<V> auxStack, List<List<V>> paths) {
        if (sourceIdx == destIdx) {//see if the source its the destination
            auxStack.add(graph.vertices.get(sourceIdx));//add the source to the stack
            paths.add(auxStack);//add the discovered path from the stack to the paths list
        } else {
            knownVertices[sourceIdx] = true;
            auxStack.add(graph.vertices.get(sourceIdx));//add the vertex to the stack

            for (V adjacentVertex : graph.directConnections(graph.vertices.get(sourceIdx))) {//for each vertex that is adjacent to the source

                if (!knownVertices[graph.toIndex(adjacentVertex)]) {//check the array to see if the index has been visited
                    GraphAlgorithms.allPaths(graph, graph.vertices.indexOf(adjacentVertex), destIdx, knownVertices, auxStack, paths);//recursively calls allPaths with the new sourceIdx as the adjacentVertex

                }
            }
        }
        knownVertices[sourceIdx] = false;
        auxStack.remove(); //pop the last vertex means it's the first one that went in, so the first on the list

    }

    /**
     * Transforms a graph into its transitive closure uses the Floyd-Warshall
     * algorithm
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param dummyEdge object to insert in the newly created edges
     * @return the new graph
     */
    public static <V, E> AdjacencyMatrixGraph<V, E> transitiveClosure(AdjacencyMatrixGraph<V, E> graph, E dummyEdge) {
        AdjacencyMatrixGraph<V, E> newMatrix = graph.clone();
        for (int i = 0; i < graph.numVertices; i++) {
            for (int j = 0; j < graph.numVertices; j++) {
                if (i != j && graph.getEdge(graph.vertices.get(j), graph.vertices.get(i)) != null) {
                    for (int k = 0; k < graph.numVertices; k++) {
                        if (i != k && j != k && graph.getEdge(graph.vertices.get(i), graph.vertices.get(k)) != null) {
                            newMatrix.insertEdge(graph.vertices.get(j), graph.vertices.get(k), dummyEdge);
                        }
                    }

                }
            }
        }
        return newMatrix;
    }

}
