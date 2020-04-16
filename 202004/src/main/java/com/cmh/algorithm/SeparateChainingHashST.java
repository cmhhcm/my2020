package com.cmh.algorithm;

import edu.princeton.cs.algs4.SequentialSearchST;

import java.util.Arrays;

/**
 * 基于拉链法的散列表
 *
 * @author Author:起舞的日子
 * Date:  2020/4/16 下午11:04
 * <p>
 *
 */
public class SeparateChainingHashST<Key, Value> {
    /**
     * 键值对总数
     */
    private int N;
    /**
     * 散列表的大小
     */
    private int M;
    /**
     * 存放链表对象的数组
     */
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        /*
        创建M条链表
         */
        this.M = M;
        st = new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    public Iterable<Key> keys() {
        return (Iterable<Key>) Arrays.stream(st)
                .map(e -> e.keys());
    }

    /**
     * 总结：
     * 1、散列表是算法在时间和空间上做出权衡的经典例子；散列函数能够将键转化为数组的一个索引；
     * 2、散列查找需要处理碰撞冲突，这里介绍2种解决碰撞的办法：拉链法、线性探测法
     * 3、可将各种数据类型散列：
     *      整数-除留余数法
     *      浮点数-把键表示为二进制再使用除留余数法
     *      字符串-
     *      组合键
     *      java.hashCode()
     *      自定义hashCode()方法
     * 4、软缓存：每次计算散列值很耗时，就把计算的值缓存起来，下次直接用；
     * 5、为避免链表过长，动态调整链表数组大小，无论符号表中有多少键值对都能保证链表较短：
     *      二项分布
     *      泊松分布
     *
     */
}
