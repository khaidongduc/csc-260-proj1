package edu.union.adt.graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

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
        if(from != null && to != null)
        {
            this.addVertex(from);
            this.addVertex(to);
            adjVerts.get(from).add(to);
        }
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
        if(vertex != null && !adjVerts.containsKey(vertex)){
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

    /**
     * Tells whether the graph is empty.
     *
     * @return true iff the graph is empty. A graph is empty if it has
     * no vertices and no edges.
     */
    public boolean isEmpty(){
        return this.adjVerts.isEmpty();
    }

    /**
     * Removes and vertex from the graph.  Also removes any edges
     * connecting from the edge or to the edge.
     *
     * <p>Postconditions:
     *
     * <p>If toRemove was in the graph:
     * <ul>
     * <li>numVertices = numVertices' - 1
     * <li>toRemove is no longer a vertex in the graph
     * <li>for all vertices v: toRemove is not in adjacentTo(v)
     * </ul>
     *
     * @param toRemove the vertex to remove.
     */
    public void removeVertex(V toRemove){
        if(this.contains(toRemove)){
            // remove all edges to and from vertex toRemove;
            for(V vertex : this.getVertices()){
                this.removeEdge(vertex, toRemove);
                this.removeEdge(toRemove, vertex);
            }
            this.adjVerts.remove(toRemove);
        }
    }

    /**
     * Removes an edge from the graph.
     *
     * <p>Postcondition: If from and to were in the graph and (from,
     * to) was an edge in the graph, then numEdges = numEdges' - 1
     */
    public void removeEdge(V from, V to){
        if(this.hasEdge(from, to)){
            this.adjVerts.get(from).remove(to);
        }
    }

    /**
     * Tells whether there is a path connecting two given vertices.  A
     * path exists from vertex A to vertex B iff A and B are in the
     * graph and there exists a sequence x_1, x_2, ..., x_n where:
     *
     * <ul>
     * <li>x_1 = A
     * <li>x_n = B
     * <li>for all i from 1 to n-1, (x_i, x_{i+1}) is an edge in the graph.
     * </ul>
     *
     * It therefore follows that, if vertex A is in the graph, there
     * is a path from A to A.
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return true iff there is a path from 'from' to 'to' in the graph.
     */
    public boolean hasPath(V from, V to){
        if(this.contains(from) && this.contains(to)){
            Set<V> visited = breadthFirstSearch(from, to, null, null);
            return visited.contains(to);
        }
        return false;
    }

    /**
     * Gets the length of the shortest path connecting two given
     * vertices.  The length of a path is the number of edges in the
     * path.
     *
     * <ol> 
     * <li>If from = to, shortest path has length 0
     * <li>Otherwise, shortest path length is length of the shortest
     * possible path connecting from to to.  
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return the length of the shortest path from 'from' to 'to' in
     * the graph.  If there is no path, returns Integer.MAX_VALUE
     */
    public int pathLength(V from, V to){
        if(this.contains(from) && this.contains(to)){
            Map<V, Integer> distance = new HashMap<V, Integer>();
            Set<V> visited = breadthFirstSearch(from, to, distance, null);
            if(visited.contains(to)){
                return distance.get(to);
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Returns the vertices along the shortest path connecting two
     * given vertices.  The vertices should be given in the order x_1,
     * x_2, x_3, ..., x_n, where:
     *
     * <ol>
     * <li>x_1 = from
     * <li>x_n = to
     * <li>for all i from 1 to n-1: (x_i, x_{i+1}) is an edge in the graph.
     * </ol>
     * 
     * @param from the source vertex
     * @param to the destination vertex
     * @return an Iterable collection of vertices along the shortest
     * path from 'from' to 'to'.  The Iterable should include the
     * source and destination vertices.
     */
    public Iterable<V> getPath(V from, V to){
        Deque<V> path = new LinkedList();
        if(this.contains(from) && this.contains(to)){
            Map<V, V> prevVertex = new HashMap<V, V>();
            Set<V> visited = breadthFirstSearch(from, to, null, prevVertex);
            if(visited.contains(to)){
                V curVert = to;
                while(curVert != null){
                    path.addFirst(curVert);
                    curVert = prevVertex.get(curVert);
                }
            }
        }
        return path;
    }

    /**
     * a helper method to excecute breadthFirstSearch on this graph
     * 
     * @param source the source vertex of breadthFirstSearch, the source vertex needs to be within the graph
     * @param target the target vertex, if there is no target, pass null. the BFS will stop upon the target vertex is found
     * specifying a target, in most case, produce only partial answer to breathFirstSearch which includes the right answer to target
     * @param distance the map of vertex to the distance between the source vertex and the vertex passed in as a key
     * @param prevVertex the map of vertex to the previous vertex in the path from the source vertex and the vertex passed in as a key.
     * if the key vertex is the source vertex, the value of prevVertex.get(key) == null
     */
    private Set<V> breadthFirstSearch(V source, V target, Map<V, Integer> distance, Map<V, V> prevVertex){
        assert distance == null || (distance != null && distance.isEmpty());
        assert prevVertex == null || (prevVertex != null && prevVertex.isEmpty());
        assert this.contains(source);
        assert target == null || this.contains(target);

        Set<V> visited = new HashSet<V>();
        Queue<V> vertexQueue = new LinkedList();
        vertexQueue.add(source);
        visited.add(source);
        if(distance != null)
            distance.put(source, 0);
        if(prevVertex != null)
            prevVertex.put(source, null);

        while(!vertexQueue.isEmpty()){
            V vert = vertexQueue.poll();
            for(V adjVert : this.adjacentTo(vert)){
                if(!visited.contains(adjVert)){
                    visited.add(adjVert);
                    vertexQueue.add(adjVert);
                    if(distance != null)
                        distance.put(adjVert, distance.get(vert) + 1);
                    if(prevVertex != null)
                        prevVertex.put(adjVert, vert);
                    if(vert.equals(target)) return visited; // if target is found, no longer need to search
                }
            }
        }

        return visited;
    }

}
