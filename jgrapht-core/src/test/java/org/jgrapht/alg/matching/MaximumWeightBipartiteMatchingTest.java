/*
 * (C) Copyright 2015-2017, by Graeme Ahokas and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package org.jgrapht.alg.matching;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.jgrapht.alg.interfaces.MatchingAlgorithm.*;
import org.jgrapht.graph.*;
import org.junit.*;

public class MaximumWeightBipartiteMatchingTest
{

    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;
    private Set<String> partition1;
    private Set<String> partition2;

    private MaximumWeightBipartiteMatching<String, DefaultWeightedEdge> matcher;

    @Before
    public void setUpGraph()
    {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex("s1");
        graph.addVertex("s2");
        graph.addVertex("s3");
        graph.addVertex("s4");
        graph.addVertex("t1");
        graph.addVertex("t2");
        graph.addVertex("t3");
        graph.addVertex("t4");

        partition1 = new HashSet<>();
        partition1.add("s1");
        partition1.add("s2");
        partition1.add("s3");
        partition1.add("s4");

        partition2 = new HashSet<>();
        partition2.add("t1");
        partition2.add("t2");
        partition2.add("t3");
        partition2.add("t4");
    }

    @Test
    public void maximumWeightBipartiteMatching1()
    {
        DefaultWeightedEdge e1 = graph.addEdge("s1", "t1");
        graph.setEdgeWeight(e1, 1);
        matcher = new MaximumWeightBipartiteMatching<>(graph, partition1, partition2);
        Matching<String, DefaultWeightedEdge> matchings = matcher.getMatching();
        assertEquals(1, matchings.getEdges().size());
        assertTrue(matchings.getEdges().contains(e1));
    }

    @Test
    public void maximumWeightBipartiteMatching2()
    {
        DefaultWeightedEdge e1 = graph.addEdge("s1", "t1");
        graph.setEdgeWeight(e1, 1);
        DefaultWeightedEdge e2 = graph.addEdge("s2", "t1");
        graph.setEdgeWeight(e2, 2);

        matcher = new MaximumWeightBipartiteMatching<>(graph, partition1, partition2);
        Matching<String, DefaultWeightedEdge> matchings = matcher.getMatching();
        assertEquals(1, matchings.getEdges().size());
        assertTrue(matchings.getEdges().contains(e2));
    }

    @Test
    public void maximumWeightBipartiteMatching3()
    {
        DefaultWeightedEdge e1 = graph.addEdge("s1", "t1");
        graph.setEdgeWeight(e1, 2);
        DefaultWeightedEdge e2 = graph.addEdge("s1", "t2");
        graph.setEdgeWeight(e2, 1);
        DefaultWeightedEdge e3 = graph.addEdge("s2", "t1");
        graph.setEdgeWeight(e3, 2);

        matcher = new MaximumWeightBipartiteMatching<>(graph, partition1, partition2);
        Matching<String, DefaultWeightedEdge> matchings = matcher.getMatching();
        assertEquals(2, matchings.getEdges().size());
        assertTrue(matchings.getEdges().contains(e2));
        assertTrue(matchings.getEdges().contains(e3));
    }

    @Test
    public void maximumWeightBipartiteMatching4()
    {
        DefaultWeightedEdge e1 = graph.addEdge("s1", "t1");
        graph.setEdgeWeight(e1, 1);
        DefaultWeightedEdge e2 = graph.addEdge("s1", "t2");
        graph.setEdgeWeight(e2, 1);
        DefaultWeightedEdge e3 = graph.addEdge("s2", "t2");
        graph.setEdgeWeight(e3, 1);

        matcher = new MaximumWeightBipartiteMatching<>(graph, partition1, partition2);
        Matching<String, DefaultWeightedEdge> matchings = matcher.getMatching();
        assertEquals(2, matchings.getEdges().size());
        assertTrue(matchings.getEdges().contains(e1));
        assertTrue(matchings.getEdges().contains(e3));
    }

    @Test
    public void maximumWeightBipartiteMatching5()
    {
        DefaultWeightedEdge e1 = graph.addEdge("s1", "t1");
        graph.setEdgeWeight(e1, 1);
        DefaultWeightedEdge e2 = graph.addEdge("s1", "t2");
        graph.setEdgeWeight(e2, 2);
        DefaultWeightedEdge e3 = graph.addEdge("s2", "t2");
        graph.setEdgeWeight(e3, 2);
        DefaultWeightedEdge e4 = graph.addEdge("s3", "t2");
        graph.setEdgeWeight(e4, 2);
        DefaultWeightedEdge e5 = graph.addEdge("s3", "t3");
        graph.setEdgeWeight(e5, 1);
        DefaultWeightedEdge e6 = graph.addEdge("s4", "t1");
        graph.setEdgeWeight(e6, 1);
        DefaultWeightedEdge e7 = graph.addEdge("s4", "t4");
        graph.setEdgeWeight(e7, 1);

        matcher = new MaximumWeightBipartiteMatching<>(graph, partition1, partition2);
        Matching<String, DefaultWeightedEdge> matchings = matcher.getMatching();
        assertEquals(4, matchings.getEdges().size());
        assertTrue(matchings.getEdges().contains(e1));
        assertTrue(matchings.getEdges().contains(e3));
        assertTrue(matchings.getEdges().contains(e5));
        assertTrue(matchings.getEdges().contains(e7));
    }

}
