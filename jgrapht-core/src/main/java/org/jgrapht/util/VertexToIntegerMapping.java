/*
 * (C) Copyright 2018-2018, by Alexandru Valeanu and Contributors.
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
package org.jgrapht.util;

import java.util.*;

/**
 * Helper class for building a one-to-one mapping for a collection of vertices to the integer range $[0, n)$
 * where $n$ is the number of vertices in the collection.
 *
 * <p>
 *     This class computes the mapping only once, on instantiation. It does not support
 *     live updates.
 * </p>
 *
 * @author Alexandru Valeanu
 *
 * @param <V> the graph vertex type
 */
public class VertexToIntegerMapping<V> {

    private final Map<V, Integer> vertexMap;
    private final List<V> indexList;

    /**
     * Create a new mapping from a set of vertices.
     *
     * @param vertices the input set of vertices
     * @throws NullPointerException if {@code vertices} is {@code null}
     */
    public VertexToIntegerMapping(Set<V> vertices){
        Objects.requireNonNull(vertices, "the input collection of vertices cannot be null");

        vertexMap = new HashMap<>(vertices.size());
        indexList = new ArrayList<>(vertices.size());

        for (V v : vertices) {
            vertexMap.put(v, vertexMap.size());
            indexList.add(v);
        }
    }

    /**
     * Create a new mapping from a list of vertices. The input list will be used as
     * the {@code indexList} so it must not be modified.
     *
     * @param vertices the input list of vertices
     * @throws NullPointerException if {@code vertices} is {@code null}
     * @throws IllegalArgumentException if the vertices are not distinct
     */
    public VertexToIntegerMapping(List<V> vertices){
        Objects.requireNonNull(vertices, "the input collection of vertices cannot be null");

        vertexMap = new HashMap<>(vertices.size());
        indexList = vertices;

        for (int i = 0; i < vertices.size(); i++) {
            V v = vertices.get(i);

            if (!vertexMap.containsKey(v)){
                vertexMap.put(v, i);
            }
            else{
                throw new IllegalArgumentException("vertices are not distinct");
            }
        }
    }

    /**
     * Create a new mapping from a collection of vertices.
     *
     * @param vertices the input collection of vertices
     * @throws NullPointerException if {@code vertices} is {@code null}
     * @throws IllegalArgumentException if the vertices are not distinct
     */
    public VertexToIntegerMapping(Collection<V> vertices){
        Objects.requireNonNull(vertices, "the input collection of vertices cannot be null");

        vertexMap = new HashMap<>(vertices.size());
        indexList = new ArrayList<>(vertices.size());

        for (V v : vertices) {
            if (!vertexMap.containsKey(v)){
                vertexMap.put(v, vertexMap.size());
                indexList.add(v);
            }
            else{
                throw new IllegalArgumentException("vertices are not distinct");
            }
        }
    }

    /**
     * Get the {@code vertexMap}, a mapping from vertices to integers (i.e. the inverse of {@code indexList}).
     *
     * @return a mapping from vertices to integers
     */
    public Map<V, Integer> getVertexMap(){
        return vertexMap;
    }

    /**
     * Get the {@code indexList}, a mapping from integers to vertices (i.e. the inverse of {@code vertexMap}).
     *
     * @return a mapping from integers to vertices
     */
    public List<V> getIndexList(){
        return indexList;
    }
}
