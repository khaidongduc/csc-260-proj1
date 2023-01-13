package edu.union.adt.graph.tests.dongk;

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
public class GraphRemoveVertexAndEdgeTests {

    private Graph<String> g;

    @Before
    public void setUp()
    { 
        g = GraphFactory.<String>createGraph();
        g.addEdge(new String("1"), new String("2"));
        g.addEdge(new String("1"), new String("3"));
        g.addEdge(new String("3"), new String("2"));
        g.addEdge(new String("1"), new String("4"));
        g.addEdge(new String("4"), new String("1"));
        g.addEdge(new String("2"), new String("5"));
        g.addEdge(new String("5"), new String("1"));
        g.addVertex(new String("6"));
    }

    @Test
    public void removeIsolatedVertex(){
        g.removeVertex(new String("6"));

        assertEquals("Number of vertex reduces by 1", g.numVertices(), 5);

        assertTrue("Graph should still contains vertex 1", g.contains(new String("1")));
        assertTrue("Graph should still contains vertex 2", g.contains(new String("2")));
        assertTrue("Graph should still contains vertex 3", g.contains(new String("3")));
        assertTrue("Graph should still contains vertex 4", g.contains(new String("4")));
        assertTrue("Graph should still contains vertex 5", g.contains(new String("5")));
        assertFalse("Graph no longer contain vertex 6", g.contains(new String("6")));

        assertTrue("Graph should still contains edge 1 2", g.hasEdge(new String("1"), new String("2")));
        assertTrue("Graph should still contains edge 1 3", g.hasEdge(new String("1"), new String("3")));
        assertTrue("Graph should still contains edge 3 2", g.hasEdge(new String("3"), new String("2")));
        assertTrue("Graph should still contains edge 1 4", g.hasEdge(new String("1"), new String("4")));
        assertTrue("Graph should still contains edge 4 1", g.hasEdge(new String("4"), new String("1")));
        assertTrue("Graph should still contains edge 2 5", g.hasEdge(new String("2"), new String("5")));
        assertTrue("Graph should still contains edge 5 1", g.hasEdge(new String("5"), new String("1")));
    }

    @Test
    public void removeNonIsolatedVertex(){
        g.removeVertex(new String("1"));

        assertFalse("Graph no longer contain vertex 1", g.contains(new String("1")));
        assertTrue("Graph should still contains vertex 2", g.contains(new String("2")));
        assertTrue("Graph should still contains vertex 3", g.contains(new String("3")));
        assertTrue("Graph should still contains vertex 4", g.contains(new String("4")));
        assertTrue("Graph should still contains vertex 5", g.contains(new String("5")));
        assertTrue("Graph should still contains vertex 6", g.contains(new String("6")));

        assertFalse("Graph no longer contain edge 1 2", g.hasEdge(new String("1"), new String("2")));
        assertFalse("Graph no longer contain edge 1 3", g.hasEdge(new String("1"), new String("3")));
        assertTrue("Graph should still contains edge 3 2", g.hasEdge(new String("3"), new String("2")));
        assertFalse("Graph no longer contain edge 1 4", g.hasEdge(new String("1"), new String("4")));
        assertFalse("Graph no longer contain edge 4 1", g.hasEdge(new String("4"), new String("1")));
        assertTrue("Graph should still contains edge 2 5", g.hasEdge(new String("2"), new String("5")));
        assertFalse("Graph no longer contain edge 5 1", g.hasEdge(new String("5"), new String("1")));

        assertEquals("Number of vertex reduces by 1", g.numVertices(), 5);
        assertEquals("Number of vertices reduces by 5", g.numEdges(), 2);
    }

}
