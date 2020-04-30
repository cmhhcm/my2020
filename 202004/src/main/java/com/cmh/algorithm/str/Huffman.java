package com.cmh.algorithm.str;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 霍夫曼压缩
 * Author:起舞的日子
 * Date:  2020/4/30 08:19
 */
public class Huffman {
    /**
     * ASCII字母表
     */
    private static int R = 256;

    public static void compress() {
        //读取输入
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        //统计频率
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        //构造霍夫曼编码树
        Node root = buildTrie(freq);
        //递归地构造编译表
        String[] st = new String[R];
        buildCode(st, root, "");
        //打印解码用的单词查找树
        writeTrie(root);
        //打印字符总数
        BinaryStdOut.write(input.length);
        //使用霍夫曼编码处理输入
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                } else {
                    BinaryStdOut.write(false);
                }
            }
        }
        BinaryStdOut.close();
    }

    /**
     * 单词查找树的结点
     */
    private static class Node implements Comparable<Node> {
        /**
         * 内部结点不会使用该变量
         */
        private char ch;

        /**
         * 展开过程不会使用该变量
         */
        private int freq;

        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * 构造一棵霍夫曼编码单词查找树
     */
    private static Node buildTrie(int[] freq) {
        //使用多棵单结点树初始化优先队列
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.insert(new Node(c, freq[c], null, null));
            }
        }
        while (pq.size() > 1) {
            //合并两棵频率最小的树
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    /**
     * 通过前缀码字典查找树构建编译表
     */

    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    /**
     * 将单词查找树写为比特字符串
     */
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    /**
     * 前缀码的展开(解码)
     */
    public void expand() {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i < N; i++) {
            //展开第i个编码所对应的字符
            Node x = root;
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) {
                    x = x.right;
                } else {
                    x = x.left;
                }
            }
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();
    }

    /**
     * 从比特流的前序表示中重建单词查找树
     */
    private static Node readTrie() {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        }
        return new Node('\0', 0, readTrie(), readTrie());
    }

}
