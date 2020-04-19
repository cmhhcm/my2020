package com.cmh.algorithm;

/**
 * G是无环图嘛？(假设不存在自环或平行边)
 * <p>
 * Author:起舞的日子
 * Date:  2020/4/20 上午12:03
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else if (w != u) {
                hasCycle = true;
            }
        }
    }

    private boolean hasCycle() {
        return hasCycle;
    }
}
