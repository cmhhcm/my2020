package com.cmh.algorithm.background;

/**
 * 流量网络中的边(剩余网络)
 * Author:起舞的日子
 * Date:  2020/5/1 17:45
 */
public class FlowEdge {
    /**
     * 边的起点
     */
    private final int v;

    /**
     * 边的终点
     */
    private final int w;

    /**
     * 容量
     */
    private final double capacity;

    /**
     * 流量
     */
    private double flow;

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else if (vertex == w) {
            return capacity - flow;
        } else {
            throw new RuntimeException(" Inconsistent edge");
        }
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) {
            flow = flow -= delta;
        } else if (vertex == w) {
            flow += delta;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }

    public String toString() {
        return String.format("%d->%d %2f %2.f", v, w, capacity, flow);
    }

}
