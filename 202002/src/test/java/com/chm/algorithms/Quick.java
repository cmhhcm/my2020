package com.chm.algorithms;

import edu.princeton.cs.algs4.StdRandom;

import static com.chm.algorithms.Example.exch;
import static com.chm.algorithms.Example.less;

/**
 * Author:meice Huang
 * Time: 2020/3/21 下午9:28
 * <p>
 * 快速排序
 * 快速排序也是分治的思想，把一个数组分成2个子数组，将两部分独立的排序，子数组有序后整个数组自然就有序了。
 * 归并排序子数组有序后还需要再次归并成有序的。
 */
public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);//消除对输入的依赖
        sort(a, 0, a.length - 1); // 这里调用可以换成三向切分的私有sort()方法(Quick3way)

    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);//切分
        sort(a, lo, j - 1);//将左半部分a[lo .. j-1]排序
        sort(a, j + 1, hi);//将右半部分a[j+1 .. hi]排序
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //将素组切分为a[lo.. i-1], a[i],a[i+1..hi]
        int i = lo, j = hi + 1;//左右扫描指针
        Comparable v = a[lo];//切分元素
        while (true) {
            //扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);//将v = a[j]放入正确的位置
        return j;//a[lo..j-1] <= a[j] <= a[j+1..hi] Bingo!
    }
}
