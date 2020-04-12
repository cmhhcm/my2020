package com.cmh.algorithm;

import edu.princeton.cs.algs4.Queue;

import static edu.princeton.cs.algs4.BinaryStdIn.isEmpty;

/**
 * @author: 起舞的日子
 * @date: 2020/4/6 下午11:40
 * 符号表：基于有序数组的二分查找
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        //调整数组大小... 省略
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        } else {
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                return vals[i];
            } else {
                return null;
            }
        }
    }

    //基于有序数组的二分查找(无递归调用)
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * 还可以用递归方式实现二分查找
     */
    public int rank(Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public void put(Key key, Value val) {
        //查找键，找到则更新值，否则创建新元素
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Iterable<Key> kes(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);

        }
/*
        if(contains(hi)){
            q.enqueue(keys[rank(hi)]);

        }
*/
        return q;
    }
    /**
     * 总结：
     * 二分查找优点：
     * 1、查找算法复杂度是对数log级别的
     *
     * 缺点：
     * 1、数据需有序；
     * 2、把很大量的数据排序在很多情况下复杂度会大大提升；
     * 3、有些数据查找和插入是混合进行的，排序好的静态数据就不合适；
     *
     *
     * 所以需要一种新的数据结构，可以结合二分查找的优缺点：
     * 二叉查找树
     * 1）可以用二分法快速查找；
     * 2）可以类似链表快速插入
     * 下一篇博客介绍
     */
}
