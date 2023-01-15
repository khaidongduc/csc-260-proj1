package edu.union.adt.graph.tests.dongk;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;

@RunWith(JUnit4.class)
public class GraphFindPathTests {
        
    private Graph<String> g;

    @Before
    public void setUp()
    {
        g = GraphFactory.<String>createGraph(); 
    }

    @Test
    public void simpleGraph(){
        g.addEdge(new String("1"), new String("2"));
        g.addEdge(new String("2"), new String("3"));
        

        {
            String msg = "There is a path of length 2 from vertex 1 to vertex 3";
            assertTrue(msg, g.hasPath(new String("1"), new String("3")));
            assertEquals(msg, 2, g.pathLength(new String("1"), new String("3")));
            checkPath(g,
                    new String("1"), new String("3"), 
                    2,
                    g.getPath(new String("1"), new String("3")));
        }
        
        {
            String msg = "There is no path from vertex 3 to vertex 1";
            assertFalse(msg, g.hasPath(new String("3"), new String("1")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("3"), new String("1")));
            checkPath(g,
                    new String("3"), new String("1"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("3"), new String("1")));
        }

        {
            // find path from a vertex that does exist in the graph to itself
            // should exist a path of length 0
            String msg = "There is a path of length 0 from vertex 2 to vertex 2";
            assertTrue(msg, g.hasPath(new String("2"), new String("2")));
            assertEquals(msg, 0, g.pathLength(new String("2"), new String("2")));
            checkPath(g,
                    new String("2"), new String("2"), 
                    0,
                    g.getPath(new String("2"), new String("2")));
        }

        {
            // find path from a vertex that does not exist in the graph to itself
            // should not exist a path
            String msg = "There is no path from vertex 4 to vertex 4 (4 is not in the graph)";
            assertFalse(msg, g.contains(new String("4")));
            assertFalse(msg, g.hasPath(new String("4"), new String("4")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("4"), new String("4")));
            checkPath(g,
                    new String("4"), new String("4"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("4"), new String("4")));
        }

        {
            // find path from a vertex that does exist in the graph to one that does not exist in the graph
            // should not exist a path

            String msg = "There is no path from vertex 1 to vertex 4 (4 is not in the graph)";
            assertFalse(msg, g.contains(new String("4")));
            assertFalse(msg, g.hasPath(new String("1"), new String("4")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("1"), new String("4")));
            checkPath(g,
                    new String("1"), new String("4"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("1"), new String("4")));
        }

        {
            // find path from a vertex that does not exist in the graph to on that does exist in the graph
            // should not exist a path
            String msg = "There is no path from vertex 4 to vertex 1 (4 is not in the graph)";
            assertFalse(msg, g.hasPath(new String("4"), new String("1")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("4"), new String("1")));
            checkPath(g,
                    new String("4"), new String("1"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("4"), new String("1")));
        }   

    }

    @Test
    public void simpleLoop(){
        g.addEdge(new String("1"), new String("2"));
        g.addEdge(new String("2"), new String("3"));
        g.addEdge(new String("3"), new String("4"));
        g.addEdge(new String("4"), new String("1"));

        {
            String msg = "There is a path of length 0 from vertex 1 to vertex 1";
            assertTrue(msg, g.hasPath(new String("1"), new String("1")));
            assertEquals(msg, 0, g.pathLength(new String("1"), new String("1")));
            checkPath(g,
                    new String("1"), new String("1"), 
                    0,
                    g.getPath(new String("1"), new String("1")));
        }

        {
            String msg = "There is a path of length 3 from vertex 1 to vertex 4";
            assertTrue(msg, g.hasPath(new String("1"), new String("4")));
            assertEquals(msg, 3, g.pathLength(new String("1"), new String("4")));
            checkPath(g,
                    new String("1"), new String("4"), 
                    3,
                    g.getPath(new String("1"), new String("4")));
        }
    }

    @Test
    public void complexGraph(){
        g.addEdge(new String("2"), new String("1"));
        g.addEdge(new String("2"), new String("3"));
        g.addEdge(new String("3"), new String("7"));
        g.addEdge(new String("7"), new String("2"));
        g.addEdge(new String("4"), new String("5"));
        g.addEdge(new String("1"), new String("5"));
        g.addEdge(new String("5"), new String("4"));
        g.addEdge(new String("6"), new String("7"));
        g.addEdge(new String("2"), new String("7"));
        g.addEdge(new String("4"), new String("1"));
        g.addEdge(new String("1"), new String("4"));
        g.addEdge(new String("5"), new String("1"));
        g.addEdge(new String("4"), new String("6"));
        g.addEdge(new String("1"), new String("8"));
        g.addEdge(new String("8"), new String("3"));
        g.addEdge(new String("3"), new String("8"));
        g.addEdge(new String("7"), new String("3"));
        g.addEdge(new String("6"), new String("5"));
        g.addEdge(new String("2"), new String("6"));
        g.addEdge(new String("9"), new String("8"));
        g.addEdge(new String("9"), new String("2"));
        

        {
            String msg = "There is a path of length 4 from vertex 1 to vertex 2";
            assertTrue(msg, g.hasPath(new String("1"), new String("2")));
            assertEquals(msg, 4, g.pathLength(new String("1"), new String("2")));
            checkPath(g,
                    new String("1"), new String("2"), 
                    4,
                    g.getPath(new String("1"), new String("2")));
        }

        {
            String msg = "There is a path of length 1 from vertex 2 to vertex 1";
            assertTrue(msg, g.hasPath(new String("2"), new String("1")));
            assertEquals(msg, 1, g.pathLength(new String("2"), new String("1")));
            checkPath(g,
                    new String("2"), new String("1"), 
                    1,
                    g.getPath(new String("2"), new String("1")));
        }

        {
            String msg = "There is a path of length 2 from vertex 5 to vertex 6";
            assertTrue(msg, g.hasPath(new String("5"), new String("6")));
            assertEquals(msg, 2, g.pathLength(new String("5"), new String("6")));
            checkPath(g,
                    new String("5"), new String("6"), 
                    2,
                    g.getPath(new String("5"), new String("6")));
        }

        {
            String msg = "There is a path of length 1 from vertex 6 to vertex 5";
            assertTrue(msg, g.hasPath(new String("6"), new String("5")));
            assertEquals(msg, 1, g.pathLength(new String("6"), new String("5")));
            checkPath(g,
                    new String("6"), new String("5"), 
                    1,
                    g.getPath(new String("6"), new String("5")));
        }


        {
            String msg = "There is a path of length 1 from vertex 6 to vertex 5";
            assertTrue(msg, g.hasPath(new String("6"), new String("5")));
            assertEquals(msg, 1, g.pathLength(new String("6"), new String("5")));
            checkPath(g,
                    new String("6"), new String("5"), 
                    1,
                    g.getPath(new String("6"), new String("5")));
        }

        {
            String msg = "There is a path of length 2 from vertex 9 to vertex 6";
            assertTrue(msg, g.hasPath(new String("9"), new String("6")));
            assertEquals(msg, 2, g.pathLength(new String("9"), new String("6")));
            checkPath(g,
                    new String("9"), new String("6"), 
                    2,
                    g.getPath(new String("9"), new String("6")));
        }

        {
            String msg = "There is no path from vertex 6 to vertex 9";
            assertFalse(msg, g.hasPath(new String("6"), new String("9")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("6"), new String("9")));
            checkPath(g,
                    new String("6"), new String("9"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("6"), new String("9")));
        }

    }

    @Test
    public void dynamicGraph(){
        g.addVertex(new String("1"));
        g.addEdge(new String("2"), new String("3"));

        {
            String msg = "Initially, there is no path from vertex 1 to vertex 3";
            assertFalse(msg, g.hasPath(new String("1"), new String("3")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("1"), new String("3")));
            checkPath(g,
                    new String("1"), new String("3"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("1"), new String("3")));
        }

        {
            String msg = "No path from vertex 3 to vertex 1";
            assertFalse(msg, g.hasPath(new String("3"), new String("1")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("3"), new String("1")));
            checkPath(g,
                    new String("3"), new String("1"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("3"), new String("1")));
        }

        g.addEdge(new String("1"), new String("2"));
        
        {
            String msg = "After adding edge 1 2, there is a path of length 2 from vertex 1 to vertex 3";
            assertTrue(msg, g.hasPath(new String("1"), new String("3")));
            assertEquals(msg, 2, g.pathLength(new String("1"), new String("3")));
            checkPath(g,
                    new String("1"), new String("3"), 
                    2,
                    g.getPath(new String("1"), new String("3")));
        }

        {
            String msg = "No path from vertex 3 to vertex 1";
            assertFalse(msg, g.hasPath(new String("3"), new String("1")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("3"), new String("1")));
            checkPath(g,
                    new String("3"), new String("1"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("3"), new String("1")));
        }

        g.removeEdge(new String("2"), new String("3"));
        
        {
            String msg = "After removing edge 2 3, there is no path from vertex 1 to vertex 3";
            assertFalse(msg, g.hasPath(new String("1"), new String("3")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("1"), new String("3")));
            checkPath(g,
                    new String("1"), new String("3"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("1"), new String("3")));
        }

        {
            String msg = "No path from vertex 3 to vertex 1";
            assertFalse(msg, g.hasPath(new String("3"), new String("1")));
            assertEquals(msg, Integer.MAX_VALUE, g.pathLength(new String("3"), new String("1")));
            checkPath(g,
                    new String("3"), new String("1"), 
                    Integer.MAX_VALUE,
                    g.getPath(new String("3"), new String("1")));
        }

    }

    @Test
    public void dynamicGraph2(){
        g.addEdge(new String("1"), new String("2"));
        g.addEdge(new String("2"), new String("3"));
        g.addEdge(new String("3"), new String("4"));
        g.addEdge(new String("4"), new String("5"));
        g.addEdge(new String("5"), new String("6"));
        g.addEdge(new String("1"), new String("7"));
        g.addEdge(new String("7"), new String("8"));
        g.addEdge(new String("8"), new String("6"));
        

        {
            String msg = "Initially, there is a path of size 3 from vertex 1 to vertex 6";
            assertTrue(msg, g.hasPath(new String("1"), new String("6")));
            assertEquals(msg, 3, g.pathLength(new String("1"), new String("6")));
            checkPath(g,
                    new String("1"), new String("6"), 
                    3,
                    g.getPath(new String("1"), new String("6")));
        }

        g.removeVertex(new String("8"));

        {
            String msg = "After removing vertex 8, there is a path of size 5 from vertex 1 to vertex 6";
            assertTrue(msg, g.hasPath(new String("1"), new String("6")));
            assertEquals(msg, 5, g.pathLength(new String("1"), new String("6")));
            checkPath(g,
                    new String("1"), new String("6"), 
                    5,
                    g.getPath(new String("1"), new String("6")));
        }

        g.addEdge(new String("7"), new String("9"));
        g.addEdge(new String("9"), new String("6"));
    
        {
            String msg = "there is a path of size 3 from vertex 1 to vertex 6";
            assertTrue(msg, g.hasPath(new String("1"), new String("6")));
            assertEquals(msg, 3, g.pathLength(new String("1"), new String("6")));
            checkPath(g,
                    new String("1"), new String("6"), 
                    3,
                    g.getPath(new String("1"), new String("6")));
        }

    }


    /**
     * helper function to verify a path in a graph
     * can pass pathLength = Integer.MAX_VALUE into the function to signify that there does not exist such path
     * 
     * if it is expected there is such path
     * the function will check if the edges used the path exist and 
     * if the length, starting vertex and ending vertex of the path are consistent with the expected one
     * 
     * if it is not expected that there is a path
     * the function will check if the path is empty.
     * 
     * 
     * @param g the graph
     * @param from the source vertex of the path
     * @param to the destination vertex of the path
     * @param pathLength the expected length of such path in a graph
     * @param path the Iterable<V> object containing the vertices of the graph starting from vertex from 
     * and ending at vertex to
     */
    private <V> void checkPath(Graph<V> g, V from, V to, int expectedPathLength, Iterable<V> path){
        Iterator<V> pathIterator = path.iterator();
        if(expectedPathLength == Integer.MAX_VALUE){
            assertFalse("path should be empty", pathIterator.hasNext());
        } else {
            V curVert = pathIterator.next();
            assertEquals("starting vertex is not correct", from, curVert);
            int length = 0;
            while(pathIterator.hasNext()){
                V nextVert = pathIterator.next();
                assertTrue(String.format("Edge %s %s does not exist", curVert, nextVert), g.hasEdge(curVert, nextVert));
                curVert = nextVert;
                ++length;
            }
            assertEquals("path leng is not consistent", expectedPathLength, length);
            assertEquals("final vertex is not to", to, curVert);
        }
    }


}
