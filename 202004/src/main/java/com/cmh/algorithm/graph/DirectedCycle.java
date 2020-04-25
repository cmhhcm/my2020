package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 寻找有向环
 * Author:起舞的日子
 * Date:  2020/4/25 上午11:27
 */
public class DirectedCycle {
    /**
     * 为什么需要检查有向图中的环呢？
     * eg:任务调度时，任务有优先级，如果x必须在y之前完成;y又必须在z之前完成;z又必须在x之前完成，那么这个问题是无解的
     * 在类似的场景下要排除有向环
     */

    private boolean[] marked;
    private int[] edgeTo;
    /**
     * 有向环中的所有顶点
     */
    private Stack<Integer> cycle;

    /**
     * 递归调用栈上所有顶点
     */
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
