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
    public void simpleFindPath(){
        g.addEdge(new String("1"), new String("2"));
        g.addEdge(new String("2"), new String("3"));
        

        {
            String msg = "There is a path of length 2 from vertex 1 to vertex 3";
            assertTrue(msg, g.hasPath(new String("1"), new String("3")));
            assertEquals(msg, g.pathLength(new String("1"), new String("3")), 2);
            checkPath(g,
                    new String("1"), new String("3"), 
                    2,
                    g.getPath(new String("1"), new String("3")));
        }
        
        {
            String msg = "There is no path from vertex 3 to vertex 1";
            assertFalse(msg, g.hasPath(new String("3"), new String("1")));
            assertEquals(msg, g.pathLength(new String("3"), new String("1")), Integer.MAX_VALUE);
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
            assertEquals(msg, g.pathLength(new String("2"), new String("2")), 0);
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
            assertEquals(msg, g.pathLength(new String("4"), new String("4")), Integer.MAX_VALUE);
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
            assertEquals(msg, g.pathLength(new String("1"), new String("4")), Integer.MAX_VALUE);
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
            assertEquals(msg, g.pathLength(new String("4"), new String("1")), Integer.MAX_VALUE);
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
            assertEquals(msg, g.pathLength(new String("1"), new String("1")), 0);
            checkPath(g,
                    new String("1"), new String("1"), 
                    0,
                    g.getPath(new String("1"), new String("1")));
        }

        {
            String msg = "There is a path of length 3 from vertex 1 to vertex 4";
            assertTrue(msg, g.hasPath(new String("1"), new String("4")));
            assertEquals(msg, g.pathLength(new String("1"), new String("4")), 3);
            checkPath(g,
                    new String("1"), new String("4"), 
                    3,
                    g.getPath(new String("1"), new String("4")));
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
    private <V> void checkPath(Graph<V> g, V from, V to, int pathLength, Iterable<V> path){
        Iterator<V> pathIterator = path.iterator();
        if(pathLength == Integer.MAX_VALUE){
            assertFalse("path should be empty", pathIterator.hasNext());
        } else {
            V curVert = pathIterator.next();
            assertEquals("starting vertex is not correct", curVert, from);
            int length = 0;
            while(pathIterator.hasNext()){
                V nextVert = pathIterator.next();
                assertTrue(String.format("Edge %s %s does not exist", curVert, nextVert), g.hasEdge(curVert, nextVert));
                curVert = nextVert;
                ++length;
            }
            assertEquals("path leng is not consistent", length, pathLength);
            assertTrue("final vertex is not to", curVert.equals(to));
        }
    }


}
