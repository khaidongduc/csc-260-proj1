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
public class GraphIsEmptyTests {
    
    private Graph<String> g;
    
    @Before
    public void setUp()
    { 
        g = GraphFactory.<String>createGraph();
    }

    @Test
    public void simpleIsEmpty()
    {
        // the graph should be empty after initialization
        {
            assertTrue(g.isEmpty());
        }
        // after adding a vertex, the graph should not be empty anymore
        {
            g.addVertex(new String("01"));
            g.addVertex(new String("02"));
            g.addEdge(new String("01"), new String("02"));
            assertFalse(g.isEmpty());
        }
        // after remove a non existant vertex, the graph should still not empty
        {
            g.removeVertex(new String("03"));
            assertFalse(g.isEmpty());
        }
        // remove all verteces, the graph should be empty
        {
            g.removeVertex(new String("01"));
            g.removeVertex(new String("02"));
            assertTrue(g.isEmpty());
        }
    }
}
