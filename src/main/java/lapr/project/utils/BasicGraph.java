package lapr.project.utils;

import java.util.List;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public interface BasicGraph<V, E> {

    /**
     * Returns the number of vertices in the graph
     *
     * @return number of vertices of the graph
   *
     */
    int numVertices();

    /**
     * Returns the number of edges in the graph
     *
     * @return number of edges of the graph 
   *
     */
    int numEdges();

    /**
     * Returns the actual vertices of the graph
     *
     * @return an iterable collection of vertices
   *
     */
    Iterable<V> vertices();

    /**
     * Returns the actual edges of the graph
     *
     * @return an iterable collection of all edges
   *
     */
    Iterable<E> edges();

    /**
     * Returns the number of edges leaving vertex This is the same result
     * returned by inDegree
     *
     * @param vertex
     * @return number of edges leaving vertex v, -1 if vertex does not exist in
     * the graph
     */
    int outDegree(V vertex);

    /**
     * Returns the number of edges reaching vertex This is the same result
     * returned by outDegree
     *
     * @param vertex
     * @return number of edges reaching vertex v, -1 if vertex does not exist in
     * the graph
     */
    int inDegree(V vertex);

    /**
     * Returns an iterable collection of edges for which vertex is the origin.
     * This is the same result as returned by incomingEdges.
     *
     * @param vertex
     * @return collection of edges leaving vertex, null if vertex does not exist
     * in the graph
     */
    Iterable<E> outgoingEdges(V vertex);

    /**
     * Returns an iterable collection of edges for which vertex v is the
     * destination. This is the same result as returned by incomingEdges.
     *
     * @param vertex
     * @return collection of edges reaching vertex, null if vertex does not
     * exist in the graph
     */
    Iterable<E> incomingEdges(V vertex);

    /**
     * Returns the edge between two vertices
     *
     * @param va
     * @param vb
     * @return the edge or null if source and dest are not adjacent or do not
     * exist in the graph.
     */
    E getEdge(V va, V vb);

    /**
     * Returns the vertices of edge as an array of length two.
     *
     * @param edge
     * @return array of two vertices or null if edge does not exist in the
     * graph.
     */
    List<V> endVertices(E edge);

    /**
     * Inserts a new vertex with the given element.
     *
     * @param newVertex
     * @return false if vertex exists
     */
    boolean insertVertex(V newVertex);

    /**
     * Inserts a new edge between two vertices
     *
     * @param va
     * @param vb
     * @param newEdge
     * @return false if either vertices are not in the graph or an edge already
     * exists between the two.
     */
    boolean insertEdge(V va, V vb, E newEdge);

    /**
     * Removes a vertex and all its incoming/outgoing edges from the graph.
     *
     * @param vertex
     * @return false if vertex does not exist in the graph
     */
    boolean removeVertex(V vertex);

    /**
     * Removes the edge between two vertices
     *
     * @param va
     * @param vb
     * @return the edge or null if vertices are not in the graph or not
     * connected
     */
    E removeEdge(V va, V vb);

}
