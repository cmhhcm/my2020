package com.cmh.algorithm;

import edu.princeton.cs.algs4.ST;

/**
 * 能够完成点乘的稀疏向量
 * <p>
 * Author:起舞的日子
 * Date:  2020/4/18 上午8:13
 */
public class SparseVector {
    private ST<Integer, Double> st;

    public SparseVector() {
        st = new ST<>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.contains(i)) {
            return 0.0;
        } else {
            return st.get(i);
        }
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keys()) {
            sum += that[i] * this.get(i);
        }
        return sum;
    }

    /**
     * 总结：
     * 1、PageRank算法的核心思想：矩阵和向量的乘法+稀疏向量
     */

    public void classicMatrixImp() {
        int N = 10000000;
        double[][] a = new double[N][N];
        double[] x = new double[N];
        double[] b = new double[N];
        /**
         * 初始化省略
         */

        for (int i = 0; i < N; i++) {
            double sum = 0.0;
            for (int j = 0; j < N; j++) {
                sum += a[i][j] * x[j];
            }
            b[i] = sum;
        }
    }
}
