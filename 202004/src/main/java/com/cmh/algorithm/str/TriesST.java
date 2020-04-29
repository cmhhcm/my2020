package com.cmh.algorithm.str;

/**
 * 基于单词查找树的符号表
 * Author:起舞的日子
 * Date:  2020/4/29 08:21
 */
public class TriesST<Value> {
    /**
     * 这是一种新的以字符串为结点的树结构
     * 树结构最大的优点是：
     * 既有利于快速查找
     * 也有利于快速插入(优化到极致)
     */

    /**
     * 基数
     */
    private static int R = 256;

    /**
     * 单词查找树的根结点
     */
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        //返回以x作为根结点的子单词查找树中与key相关联的值
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        //找到第d个字符所对应的子单词查找树
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value val) {

    }

    private Node put(Node x, String key, Value val, int d) {
        //如果key存在于以x为根结点的子单词查找树中则更新与它相关的值
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        //找到第d个字符所对应的子单词查找树
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * 总结
     * 缺点：极端情况单向分支很长，"长长的尾巴"（大量长键导致）
     */
}
