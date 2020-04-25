package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 * Author:起舞的日子
 * Date:  2020/4/25 上午11:40
 */
public class DepthFirstOrder {

    /**
     * 优先级限制下的调度问题等价于 计算有向无环图中的所有顶点的拓扑顺序
     */

    private boolean[] marked;
    /**
     * 所有顶点的前序排列
     */
    private Queue<Integer> pre;

    /**
     * 所有顶点的后续排列
     */
    private Queue<Integer> post;

    /**
     * 所有顶点的逆后序排列
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);

    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

}
