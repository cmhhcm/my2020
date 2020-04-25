package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的可达性
 * Author:起舞的日子
 * Date:  2020/4/25 上午11:03
 */
public class DirectedDFS {
    /**
     * 多点可达性：给定一副有向图和顶点的集合，回答："是否存在一条从集合中任意顶点到达给定顶点v的有向路径？"等类似问题
     */

    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }
        DirectedDFS reachable = new DirectedDFS(G, sources);
        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }

    /**
     * 这份深度优先搜索的实现是的用例能够判断从给定的一个或者一组顶点能能够到达哪些其他顶点。
     *
     */
}
