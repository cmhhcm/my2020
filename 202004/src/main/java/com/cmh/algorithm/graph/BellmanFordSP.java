package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 基于队列的Bellman-Ford算法
 * Author:起舞的日子
 * Date:  2020/4/26 下午11:36
 */
public class BellmanFordSP {
    /**
     * 考虑：一般加权有向图中的最短路径问题，也就是：既可能含有环也可能含有负权重的边的加权有向图
     * 的最短路径问题
     */

    /**
     * 从起点到某个顶点的路径长度
     */
    private double[] distTo;
    /**
     * 从起点到某个顶点的最后一条边
     */
    private DirectedEdge[] edgeTo;

    /**
     * 该顶点是否存在于队列中
     */
    private boolean[] onQ;

    /**
     * 正在被放松的顶点
     */
    private Queue<Integer> queue;

    /**
     * relax()的调用次数
     */
    private int cost;

    /**
     * edgeTo[]中的是否有负权重环
     */
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
            }
        }
    }

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

    /**
     * 负权重环检测方法
     */
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle cf;
        cf = new EdgeWeightedDirectedCycle(spt);
        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
