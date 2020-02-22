package com.chm.foundation;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author:meice Huang
 * Time: 2020/2/22 下午2:59
 */
public class CoreJavaVolumeOne {
    /**
     * 选择排序
     */

    public void selectionSort(Integer[] sortArray, SortDes sortDes) throws Exception {
        if (sortArray.length == 0) {
            throw new Exception("Array is not null...");
        }
        for (int i = 0; i < sortArray.length - 1; i++) {
            if (sortDes == SortDes.ASC || sortDes == null) {
                if (sortArray[i] > sortArray[i + 1]) {
                    int temp = sortArray[i + 1];
                    sortArray[i + 1] = sortArray[i];
                    sortArray[i] = temp;
                }
            } else {
                if (sortArray[i] < sortArray[i + 1]) {
                    int temp = sortArray[i + 1];
                    sortArray[i + 1] = sortArray[i];
                    sortArray[i] = temp;
                }
            }
        }
        System.out.println(Arrays.asList(sortArray).toString());
    }

    enum SortDes {
        ASC,
        DESC
    }

    @Test
    public void testSelectionSort() throws Exception {
        Integer[] testArray = new Integer[]{1234, 23, 7, 123, 3, 987, 1, 666, 34, 566666};
        selectionSort(testArray, SortDes.DESC);//只是比较了相邻的，未比较全局的
        //官方思路
        pickLess(testArray);
        sort(testArray);
        show(testArray);
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private void pickLess(Integer[] array) {
        int min = 0;
        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            if (array[i] > array[i + 1]) {
                min = i + 1;
            }
        }
        System.out.println("Min:" + array[min]);
    }
    public void testSortLess(Integer[] array){
        for(int i=0;i<array.length;i++){
            int min = 0;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[i]){
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];


        }
    }
}
