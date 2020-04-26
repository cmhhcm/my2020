package com.cmh.algorithm.graph;

/**
 * 加权有向边的数据类型
 * Author:起舞的日子
 * Date:  2020/4/25 下午5:43
 */
public class DirectedEdge {
    /**
     * 边的起点
     */
    private final int v;

    /**
     * 边的终点
     */
    private final int w;

    /**
     * 边的权重
     */
    private final double weight;

    public DirectedEdge(int v,int w,double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    @Override
    public String toString(){
        return String.format("%d -> %d %.2f",v,w,weight);
    }
}
