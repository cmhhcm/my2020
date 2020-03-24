package com.chm.algorithms;

import org.junit.jupiter.api.Test;

import static com.chm.algorithms.Example.merge;

/**
 * Author:meice Huang
 * Time: 2020/3/21 下午8:58
 * <p>
 * 自顶向下的归并排序—— 高效算法设计中分治思想的最典型例子
 */
public class Merge {

    private static Comparable[] aux; //归并所需要的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        //将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);// 将左半边排序
        sort(a, mid + 1, hi);//将右半边排序
        merge(a, lo, mid, hi);
    }

    @Test
    public void testMergeSort() {
        sort(new Comparable[]{10, 3, 7, 8, 5, 1, 4, 9, 2, 6});
    }
}
