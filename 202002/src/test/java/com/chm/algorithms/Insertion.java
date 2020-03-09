package com.chm.algorithms;

import static com.chm.algorithms.Example.exch;
import static com.chm.algorithms.Example.less;
import static com.chm.algorithms.Example.show;

/**
 * Author:meice Huang
 * Time: 2020/2/26 上午7:33
 */


public class Insertion {
    /**
     * 插入排序
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
        show(a);
    }

    public void testInsertionSort() {
        sort(new Comparable[]{12, 345, 98, 3, 987, 1234, -9, 23});
    }
}
