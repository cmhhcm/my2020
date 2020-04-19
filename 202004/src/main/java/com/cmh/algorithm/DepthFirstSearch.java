package com.cmh.algorithm;

/**
 * 深度优先搜索
 *
 * @author :起舞的日子
 * Date:  2020/4/19 下午11:15
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked(w);
    }

    public int count() {
        return count;
    }

    /**
     * 总结：
     * 1、什么是深度优先搜索？
     *   搜索连通图的经典递归算法（遍历所有的顶点和边）和Tremaux搜索类似，
     *   要搜索一幅图，只需用一个递归方法来遍历所有顶点，在访问其中一个顶点时：
     *      将它标记为已访问；
     *      递归的访问它的所有没有被标记过的邻居顶点
     */
}
