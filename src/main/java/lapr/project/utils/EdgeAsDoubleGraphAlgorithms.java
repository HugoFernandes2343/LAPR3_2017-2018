package lapr.project.utils;


import java.util.LinkedList;

/**
 *
 * @author DEI-ESINF
 */
public class EdgeAsDoubleGraphAlgorithms {

    /**
     * Determine the shortest path to all vertices from a vertex using
     * Dijkstra's algorithm To be called by public short method
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param knownVertices previously discovered vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param minDist minimum distances in the path
     *
     */
    private static <V> void shortestPath(AdjacencyMatrixGraph<V, Double> graph, int sourceIdx, boolean[] knownVertices, int[] verticesIndex, double[] minDist) {
        minDist[sourceIdx] = 0;
        while (sourceIdx != -1) {
            knownVertices[sourceIdx] = true;
            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.privateGet(sourceIdx, i) != null) {
                    if (!knownVertices[i] && minDist[i] > (minDist[sourceIdx] + graph.privateGet(sourceIdx, i))) {
                        minDist[i] = minDist[sourceIdx] + graph.privateGet(sourceIdx, i);
                        verticesIndex[i] = sourceIdx;
                    }
                }
            }
            Double min = Double.MAX_VALUE;
            sourceIdx = -1;
            for (int i = 0; i < graph.numVertices; i++) {
                if (!knownVertices[i] && minDist[i] < min) {
                    min = minDist[i];
                    sourceIdx = i;
                }
            }
        }
    }

    /**
     * Determine the shortest path between two vertices using Dijkstra's
     * algorithm
     *
     * @param graph Graph object
     * @param source Source vertex
     * @param dest Destination vertices
     * @param path Returns the vertices in the path (empty if no path)
     * @return minimum distance, -1 if vertices not in graph or no path
     *
     */
     public static <V> double shortestPath(AdjacencyMatrixGraph<V, Double> graph, V source, V dest, LinkedList<V> path) {
        int srcIndex = graph.toIndex(source);
        int dstIndex = graph.toIndex(dest);

        if (srcIndex == -1) {
            return -1;
        }
        if (dstIndex == -1) {
            return -1;
        }

        path.clear();
        int[] vertIndex = new int[graph.numVertices];
        double[] minDist = new double[graph.numVertices];
        boolean[] knownVertices = new boolean[graph.numVertices];

        for (int i = 0; i < graph.numVertices; i++) {
            minDist[i] = Double.MAX_VALUE;
            vertIndex[i] = -1;
        }
        shortestPath(graph, srcIndex, knownVertices, vertIndex, minDist);
        if (knownVertices[dstIndex] == false) {
            return -1;
        }
        recreatePath(graph, srcIndex, dstIndex, vertIndex, path);
        LinkedList<V> stack = new LinkedList<V>();
        while (!path.isEmpty()) {
            stack.push(path.remove());
        }
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return minDist[dstIndex];
    }

    /**
     * Recreates the minimum path between two vertex, from the result of
     * Dikstra's algorithm
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param destIdx Destination vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param Queue Vertices in the path (empty if no path)
     */
    private static <V> void recreatePath(AdjacencyMatrixGraph<V, Double> graph, int sourceIdx, int destIdx, int[] verticesIndex, LinkedList<V> path) {

        path.add(graph.vertices.get(destIdx));
        if (sourceIdx != destIdx) {
            destIdx = verticesIndex[destIdx];
            recreatePath(graph, sourceIdx, destIdx, verticesIndex, path);
        }
    }

    /**
     * Creates new graph with minimum distances between all pairs uses the
     * Floyd-Warshall algorithm
     *
     * @param <V>
     * @param graph Graph object
     * @return the new graph
     */
    public static <V> AdjacencyMatrixGraph<V, Double> minDistGraph(AdjacencyMatrixGraph<V, Double> graph) {
        AdjacencyMatrixGraph<V, Double> newGraph = (AdjacencyMatrixGraph<V, Double>) graph.clone();

        for (int k = 0; k < newGraph.numVertices; k++) {
            for (int i = 0; i < newGraph.numVertices; i++) {
                if (i != k && newGraph.privateGet(i, k) != null) {
                    for (int j = 0; j < newGraph.numVertices; j++) {
                        if (i != j && j != k && newGraph.privateGet(k, j) != null) {
                            if (newGraph.privateGet(i, j) == null) {
                                newGraph.insertEdge(i, j, newGraph.privateGet(i, k) + newGraph.privateGet(k, j));
                            } else if (newGraph.privateGet(i, j) > newGraph.privateGet(i, k) + newGraph.privateGet(k, j)) {
                                newGraph.privateSet(i, j, newGraph.privateGet(i, k) + newGraph.privateGet(k, j));
                            }
                        }
                    }
                }
            }
        }
        return newGraph;
    }

}
