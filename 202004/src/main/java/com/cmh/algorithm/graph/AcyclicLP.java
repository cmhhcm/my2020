package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 无环加权有向图中的单点最长路径
 * Author:起舞的日子
 * Date:  2020/4/26 下午9:24
 */
public class AcyclicLP {
    /**
     * 无环加权有向图中的单点最长路径
     * 给定一副无环加权有向图(边的权重可能为负数)和一个起点s,回答"是否存在一条从s到给定的顶点v的路径?"
     * 如果有，找出最长(总权重最大)的那条路径等类似问题。
     */
    private DirectedEdge[] edgeTo;

    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }

    /**
     * 顶点的松弛
     */
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    /**
     * 最短路径树(SPT)的标准查询算法
     */
    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo(v) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

}

