package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的Prim算法的延时实现
 * Author:起舞的日子
 * Date:  2020/4/25 下午4:31
 */
public class LazyPrimMST {
    /**
     * 最小生成树的顶点
     */
    private boolean[] marked;

    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 横切边(包括失效的边)
     */
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();

        //假设G是连通的
        visit(G, 0);
        while (!pq.isEmpty()) {
            //从pq中得到的权重最小的边
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            if (!marked[v]) {
                visit(G, v);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        //标记顶点v并将所有连接v和维度标记顶点的边加入pq
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        //TODO
        return 0.0;
    }

    /**
     * Prim算法的这种实现使用了一条优先队列来保存所有的横切边，一个由顶点索引的数组来标记树的顶点以及一条队列来保存最小生成树的边。
     * 这种延时实现会在优先队列中保留失效的边。
     */

}
