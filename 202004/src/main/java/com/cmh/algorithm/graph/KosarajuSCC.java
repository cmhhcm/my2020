package com.cmh.algorithm.graph;

/**
 * 计算强连通分量的Kosaraju算法
 * Author:起舞的日子
 * Date:  2020/4/25 下午3:09
 */
public class KosarajuSCC {
    /**
     * 已访问过的顶点
     */
    private boolean[] marked;

    /**
     * 强连通分量的标识符
     */
    private int[] id;

    /**
     * 强连通分量的数量
     */
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    /**
     * 该算法解决了什么问题呢？
     * 强连通性：给定一副有向图，回答："给定的两个顶点是强连通的吗？这幅有向图中含有多少个强连通分量？"等类似问题
     */
}

