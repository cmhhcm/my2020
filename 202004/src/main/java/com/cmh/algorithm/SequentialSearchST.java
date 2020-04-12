package com.cmh.algorithm;

/**
 * @author: 起舞的日子
 * @date: 2020/4/6 下午11:24
 * 符号表：基于无序链表的顺序查找
 * ST = Symbol Table
 */
public class SequentialSearchST<Key, Value> {
    /**
     * 链表首节点
     */
    private Node first;

    private class Node {
        /**
         * 链表节点定义
         */
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        /*
         *根据给定的键返回对应的值//命中  如果key是对象，要考虑对象对象等价性
         */
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        //根据给定的键，找到则更新其值，否则在表中新建节点
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = val;
                return;
            } else {
                first = new Node(key, val, first);
            }
        }
    }

    /**
     * 使用无序链表实现K，V查找和插入，最大问题是数据量很大时候，算法复杂度至少在O(N)级别，
     * 算法消耗在：比较的次数上
     */
}
