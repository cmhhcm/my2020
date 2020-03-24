package com.chm.algorithms;

/**
 * Author:meice Huang
 * Time: 2020/2/25 下午9:42
 */
public class Example {
    public static void sort(Comparable[] a) {
        //各种算法
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
//        Arrays.stream(a).forEach(System.out::print("\t"));
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        //测试数组元素是否有序？
        //TODO 用java8优化下
/*
        Arrays.stream(a)
               .filter(a.)
*/
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 原地归并的抽象方法
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid] 和[mid+1,hi] 归并
        int i = lo, j = mid + 1;
        Comparable[] aux = new Comparable[a.length];
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; //将a[lo..hi]复制到aux[lo..hi]
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }
    /**
     * 我觉得上面这原地归并抽象方法不好理解，
     * 原因是：变量很多，设计到i j k
     * 尤其是四个条件判断，左半边用尽(取右半边的元素)；
     * 右半边用尽(取左半边的元素)；
     * 右半边的当前元素小于左半边的当前元素(取右半边的元素)；
     * 右半边的当前元素大于等于左半边的当前元素(取左半边的当前元素)
     * 这里的"用尽"怎么理解呢？
     */

}
