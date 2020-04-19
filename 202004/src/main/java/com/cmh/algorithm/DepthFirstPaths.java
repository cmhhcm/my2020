package com.cmh.algorithm;

import edu.princeton.cs.algs4.Stack;

/**
 * 使用深度优先搜索查找图中的路径
 *
 * @author:起舞的日子 Date:  2020/4/19 下午11:28
 */
public class DepthFirstPaths {
    /**
     * 这个顶点上调用过dfs()了嘛？
     */
    private boolean[] marked;
    /**
     * 从起点到一个顶点的的已知路径上的最后一个顶点
     */
    private int[] edgeTo;

    /**
     * 起点
     */
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    /**
     * 总结：
     * 1、单点路径问题：
     *      给定一幅图和一个起点s,回答"从s到给定目的顶点v是否存在一条路径？"，如果有，请找出
     *      这条路径。
     *
     * 2、这段代码找出途中从给定的起点s到它所连通的所有顶点的路径。
     *
     */
}
