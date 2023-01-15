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
public class GraphFindPathTests {
        
    private Graph<String> g;

    @Before
    public void setUp()
    {
        g = GraphFactory.<String>createGraph(); 
    }

    @Test
    public void dummyTest(){
        
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
     * @return true if the path is consistent with the expected values
     */
    private <V> boolean checkPath(Graph<V> g, V from, V to, int pathLength, Iterable<V> path){
        Iterator<V> pathIterator = path.iterator();
        if(pathLength == Integer.MAX_VALUE){
            if(pathIterator.hasNext()) return false;
            return true;
        }
        V curVert = pathIterator.next();
        if(!curVert.equals(from)) return false;
        int length = 0;
        while(pathIterator.hasNext()){
            V nextVert = pathIterator.next();
            if(!g.hasEdge(curVert, nextVert)) return false;
            curVert = nextVert;
            ++length;
        }
        return length == pathLength && curVert.equals(to);
    }

}
