package com.cmh.algorithm;

/**
 * 基于线性探测法的散列表
 * <p>
 * Author:起舞的日子
 * Date:  2020/4/16 下午11:29
 */
public class LinearProbingHashST<Key, Value> {
    /**
     * 符号表中的键值对总数
     */
    private int N;
    /**
     * 线性探测表的大小
     */
    private int M = 16;
    /**
     * 并行数组
     */
    private Key[] keys;

    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        M = cap;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize() {

    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
            keys = t.keys;
            vals = t.vals;
            M = t.M;
        }
    }

    public void put(Key key, Value val) {
        /**
         * 扩容，为什么扩2倍？
         * 因为这样可以避免键簇形成
         */
        if (N > M / 2) {
            resize(2 * M);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            keys[i] = key;
            vals[i] = val;
            N++;
        }
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
    }

    /**
     * 总结：
     * 1、 用大小为M的数组保存N个键值对，其中M>N.依靠数组中空位解决碰撞冲突。——开放地址散列表
     * 2、开放地址散列表中最简单的方法是：线性探测法
     * 3、用散列函数找到键所在的索引，检查其中的键和被查找的键是否相同，如果不同继续查找；
     * 4、探测：检查一个数组位置是否含有被查找的键的操作
     * 5、键簇-元素插入数组后形成一组连续的条目，如何避免？调整数组大小
     * 6、均摊分析  动态调整数组大小使散列表长度加倍的插入操作需要大量探测
     *
     */
}
