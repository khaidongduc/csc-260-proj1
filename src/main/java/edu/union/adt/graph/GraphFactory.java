package edu.union.adt.graph;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphImplementation;

public class GraphFactory {
    public static <V> Graph<V> createGraph(){
        return new GraphImplementation<V>();
    }
}
