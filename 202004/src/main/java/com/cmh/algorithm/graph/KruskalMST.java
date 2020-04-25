package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 最小生成树的KruskaMST
 * Author:起舞的日子
 * Date:  2020/4/25 下午5:14
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            //从pq得到权重最小的边和它的顶点
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                //忽略失效的边
                continue;
            }
            //合并分量
            uf.union(v, w);
            //将边添加到最小生成树
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return 0.0;
    }
}
