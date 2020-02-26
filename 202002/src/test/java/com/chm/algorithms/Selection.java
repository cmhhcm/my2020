package com.chm.algorithms;

/**
 * Author:meice Huang
 * Time: 2020/2/26 上午5:34
 */

import org.junit.Test;

import static com.chm.algorithms.Example.exch;
import static com.chm.algorithms.Example.less;
import static com.chm.algorithms.Example.show;

public class Selection {
    /**
     * 选择排序
     * 前提：整数，默认ASC排序且数组.length>=1
     */
    public static void sort(Comparable[] a) {
        int L = a.length;
        for (int i = 0; i < L; i++) {
            for (int j = i + 1; j < L; j++) {
                int minIndex = i;
                if (less(a[j], a[i])) {
                    minIndex = j;
                }
                exch(a, i, minIndex);
            }
        }
        show(a);
    }

    /**
     * 总结：
     * 这种简化过程，抽离方法的做法很好。
     * 其实排序的核心思想就是：比较+交换
     *
     * 算法效率：对于长度为N的数组，需要大约N^2/2次比较和N次交换
     */
    @Test
    public void testAscSelectionSorted() {
        sort(new Comparable[]{12, 345, 98, 3, 987, 1234, -9, 23});
    }
}
