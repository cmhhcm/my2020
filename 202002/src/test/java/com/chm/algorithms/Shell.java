package com.chm.algorithms;

/**
 * Author:meice Huang
 * Time: 2020/3/4 上午7:40
 */

import java.util.Arrays;

import static com.chm.algorithms.Example.exch;
import static com.chm.algorithms.Example.less;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {49, 38, 65, 97, 76, 13, 27, 48, 55, 4};
        sort(numbers);
    }
}
