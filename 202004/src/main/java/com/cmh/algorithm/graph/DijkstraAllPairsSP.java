package com.cmh.algorithm.graph;

/**
 * 任意顶点对之间的最短路径
 * Author:起舞的日子
 * Date:  2020/4/26 下午9:19
 */
public class DijkstraAllPairsSP {
    /**
     * 任意顶点对之间的最短路径
     * "给定一个起点s和一个终点t,是否存在一条从s到t的路径？如果有，找出最短(权重最小)的那条路径"等类似问题
     */

    private DijkstraSP[] all;

    DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DijkstraSP(G, v);
        }
    }

    Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    double dist(int s, int t) {
        return all[s].distTo(t);
    }
}
