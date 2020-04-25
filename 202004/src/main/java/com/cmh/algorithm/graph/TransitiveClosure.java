package com.cmh.algorithm.graph;

/**
 * 顶点对的可达性
 * Author:起舞的日子
 * Date:  2020/4/25 下午3:21
 */
public class TransitiveClosure {
    /**
     * 顶点对的可达性：给定一副有向图，回答“是否存在一条从一个给定的顶点v到另一个给定顶点w的路径？”等类似问题
     */
    private DirectedDFS[] all;

    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
