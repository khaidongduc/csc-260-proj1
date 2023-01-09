package edu.union.adt.graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import edu.union.adt.graph.Graph;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).
 *
 * The API is based on one from
 *     http://introcs.cs.princeton.edu/java/home/
 *
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Aaron G. Cass, Khai Dong
 * @version 1.1
 */
public class GraphImplementation<V> implements Graph<V>
{   

    private Map<V, Set<V>> adjVerts;

    /**
     * Create an empty graph.
     */
    public GraphImplementation() 
    {
        this.adjVerts = new HashMap<>();
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices()
    {
        return adjVerts.size();
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges()
    {
        int numEdges = 0;
        for(Map.Entry<V, Set<V>> entry : adjVerts.entrySet()){
            numEdges += entry.getValue().size();
        }
        return numEdges;
    }

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex)
    {
        if(!this.contains(vertex)){
            throw new RuntimeException("vertex is not in the graph");
        }
        return adjVerts.get(vertex).size();
    }

    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(V from, V to)
    {
        this.addVertex(from);
        this.addVertex(to);
        adjVerts.get(from).add(to);
    }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex)
    {
        if(!adjVerts.containsKey(vertex)){
            adjVerts.put(vertex, new HashSet<V>());
        }
    }

    /**
     * @return the an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices()
    {
        return adjVerts.keySet();
    }

    /**
     * Gets the vertices adjacent to a given vertex.  A vertex y is
     * "adjacent to" vertex x if there is an edge (x, y) in the graph.
     * Because edges are directed, if (x, y) is an edge but (y, x) is
     * not an edge, we would say that y is adjacent to x but that x is
     * NOT adjacent to y.
     *
     * @param from the source vertex
     * @return an iterable collection for the set of vertices that are
     * the destinations of edges for which 'from' is the source
     * vertex.  If 'from' is not a vertex in the graph, returns an
     * empty iterator.
     */
    public Iterable<V> adjacentTo(V from)
    {
        return adjVerts.get(from);
    }

    /**
     * Tells whether or not a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex)
    {
        return adjVerts.containsKey(vertex);
    }

    /**
     * Tells whether an edge exists in the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     *
     * @return true iff there is an edge from the source vertex to the
     * destination vertex in the graph.  If either of the given
     * vertices are not vertices in the graph, then there is no edge
     * between them.
     */
    public boolean hasEdge(V from, V to)
    {
        return this.contains(from) // check if from is in the graph
            && this.contains(to) // check if to is in the graph
            && this.adjVerts.get(from).contains(to); // check if the edge exists
    }

    /**
     * Gives a string representation of the graph.  The representation
     * is a series of lines, one for each vertex in the graph.  On
     * each line, the vertex is shown followed by ": " and then
     * followed by a list of the vertices adjacent to that vertex.  In
     * this list of vertices, the vertices are separated by ", ".  For
     * example, for a graph with String vertices "A", "B", and "C", we
     * might have the following string representation:
     *
     * <PRE>
     * A: A, B
     * B:
     * C: A, B
     * </PRE>
     *
     * This representation would indicate that the following edges are
     * in the graph: (A, A), (A, B), (C, A), (C, B) and that B has no
     * adjacent vertices.
     *
     * Note: there are no extraneous spaces in the output.  So, if we
     * replace each space with '*', the above representation would be:
     *
     * <PRE>
     * A:*A,*B
     * B:
     * C:*A,*B
     * </PRE>
     *
     * @return the string representation of the graph
     */
    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();
        Iterator<V> vertIter = this.getVertices().iterator(); 
        while(vertIter.hasNext()){
            V vertex = vertIter.next();
            strBuilder.append(vertex).append(":");
            Iterator<V> adjVertIter = this.adjacentTo(vertex).iterator(); 
            while(adjVertIter.hasNext()){
                strBuilder.append(adjVertIter.next());
                if(adjVertIter.hasNext()) 
                    strBuilder.append(',');
            }
            if(vertIter.hasNext()) 
                strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    /**
    * equal method for Graph
    *
    * @param obj the arbitrary object
    * @return true if this is equal to obj
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GraphImplementation graph = (GraphImplementation) obj;
        return this.toString().equals(graph.toString());
    }

}