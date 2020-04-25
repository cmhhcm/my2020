package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图的数据类型
 * Author:起舞的日子
 * Date:  2020/4/25 下午4:20
 */
public class EdgeWeightedGraph {
    /**
     * 顶点总数
     */
    private final int V;

    /**
     * 边的总数
     */
    private int E;

    /**
     * 邻接表
     */
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        //TODO
        this.V = 0;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 返回加权无向图中的所有边
     */
    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    b.add(e);
                }
            }
        }
        return b;
    }
}
