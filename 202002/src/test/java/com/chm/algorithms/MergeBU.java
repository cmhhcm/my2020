package com.chm.algorithms;

import static com.chm.algorithms.Example.*;

/**
 * Author:meice Huang
 * Time: 2020/3/21 下午9:20
 * 自底向上的归并排序
 */
public class MergeBU {
    private static Comparable[] aux;//归并所需辅助数组

    public static void sort(Comparable[] a) {
        //进行logN次两两归并
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz++) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) { //sz 子数组大小
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));//lo 子数组索引
            }
        }
    }
}
