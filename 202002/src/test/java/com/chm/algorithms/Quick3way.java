package com.chm.algorithms;

import static com.chm.algorithms.Example.exch;

/**
 * Author:meice Huang
 * Time: 2020/3/21 下午9:46
 * 三向切分的快速排序
 * 荷兰国旗问题
 */
public class Quick3way {
    private static void sort(Comparable[] a, int lo, int hi) {
        //调用此私有方法，可通过Quick类的共有方法调用
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }//现在 a[lo .. lt-1] < v =a[lt .. gt] < a[gt+1 .. hi]成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
