package com.cmh.algorithm;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图的数据类型
 * <p>
 * 此实现允许用例用字符串代替数字索引来表示图中的顶点
 * Author:起舞的日子
 * Date:  2020/4/20 上午12:12
 */
public class SymbolGraph {
    /**
     * 符号表 -> 索引
     */
    private ST<String, Integer> st;

    /**
     * 索引 -> 符号名
     */
    private String[] keys;

    private Graph G;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        // 第一遍：构造索引
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        //用来获得顶点名的反向索引是一个数组
        keys = new String[st.size()];

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        G = new Graph(st.size());
        in = new In(stream);
        //第二遍，构造图
        while (in.hasNextLine()) {
            //将每一行的第一个顶点和改行的其他顶点相连
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }
}
