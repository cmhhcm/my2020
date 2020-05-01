package com.cmh.algorithm.background;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最短增广路径的Ford-Fulkerson最大流量算法
 * Author:起舞的日子
 * Date:  2020/5/1 17:40
 */
public class FordFulkerson {

    /**
     * 网络中的初始流量为零，沿着任意从起点到终点(且不含有饱和的正向边或是空逆向边)的增广路径增大流量
     * ，知道网络中不存在这样的路径为止
     */

    /**
     * 在剩余网络中是否存在从s到v的路径？
     */
    private boolean[] marked;

    /**
     * 从s到v的最短路径上的最后一条边
     */
    private FlowEdge[] edgeTo;

    /**
     * 当前最大流量
     */
    private double value;

    public FordFulkerson(FlowNetWork G, int s, int t) {
        //找出从s到t的流量网络G的最大流量配置
        while (hasAugmentingPath(G, s, t)) {
            //利用所有存在的增广路径
            //计算当前的瓶颈容量
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            //增大流量
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        return marked[v];
    }

    /**
     * 在剩余网络中屠龙过广度优先搜索寻找增广路径
     */
    private boolean hasAugmentingPath(FlowNetWork G, int s, int t) {

        //标记路径已知的顶点
        marked = new boolean[G.V()];

        //路径上最后一条边
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> q = new Queue<>();
        //标记起点
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    //在剩余网络中，对于任意一条连接到一个未被标记的顶点的边
                    //保存路径上最后一条边
                    edgeTo[w] = e;
                    //标记w，因为路径现在是已知的了
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }

        return marked[t];
    }

    public static void main(String[] args) {
        FlowNetWork G = new FlowNetWork(new In(args[0]));
        int s = 0, t = G.V() - 1;
        FordFulkerson maxFlow = new FordFulkerson(G, s, t);
        StdOut.println("Max flow from " + s + " to" + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0) {
                    StdOut.println("  " + e);
                }
            }
        }
        StdOut.println("Max flow value = " + maxFlow.value());

    }

}
