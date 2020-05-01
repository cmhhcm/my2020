package com.cmh.algorithm.background;

/**
 * B-Tree
 * Author:起舞的日子
 * Date:  2020/5/1 16:53
 */
public class BTree<Key extends Comparable<Key>, Value> {
    /**
     * B-树不会将数据保存在树中，
     * 而是会构造一棵由键的副本组成的树，每个副本都关联着一条链接。这种方式能够方便的将索引和符号表分开
     * 就像一本实体书的索引一样。
     * 和2-3树一样，我们限制了每个结点中能够含有的"键-链接"对的上下数量界限：选择一个参数M(一般都是偶数)
     * 并构造一棵多向树，每个结点做多含有M-1对键和链接。
     * <p>
     * 这种树被Bayer和McCreight在1970年命名为B-树。
     */
    private static final int M = 4;

    private BTree.Node root;

    private int height;

    private int n;

    private static final class Node {
        private int m;
        private BTree.Entry[] children = new BTree.Entry[M];

        private Node(int k) {
            m = k;
        }
    }

    private static class Entry {
        private Comparable key;

        private final Object val;

        private BTree.Node next;

        public Entry(Comparable key, Object val, BTree.Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public BTree() {
        root = new Node(0);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public int height() {
        return height;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return search(root, key, height);
    }

    private Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, children[j].key)) {
                    return (Value) children[j].val;
                }
            }
        } else {
            for (int j = 0; j < x.m; j++) {
                if (j + 1 == x.m || less(key, children[j + 1].key)) {
                    return search(children[j].next, key, ht - 1);
                }
            }
        }
        return null;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("argument key to put is null");
        }
        Node u = insert(root, key, val, height);
        n++;
        if (u == null) {
            return;
        }
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[2] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) {
                    break;
                }
            }
        } else {
            for (j = 0; j < h.m; j++) {
                if ((j + 1 == h.m) || less(key, h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht - 1);
                    if (u == null) {
                        return null;
                    }
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--) {
            h.children[i] = h.children[i - 1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) {
            return null;
        } else {
            return split(h);
        }
    }

    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++) {
            t.children[j] = h.children[M / 2 + j];
        }
        return t;
    }

    @Override
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;
        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) {
                    s.append(indent + "(" + children[j].key + " )\n");
                }
                s.append(toString(children[j].next, ht - 1, indent + "  "));
            }
        }
        return s.toString();
    }

}
