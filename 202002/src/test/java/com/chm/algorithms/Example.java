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
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+"  ");
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

    
}
