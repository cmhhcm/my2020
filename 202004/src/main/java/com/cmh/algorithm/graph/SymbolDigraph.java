package com.cmh.algorithm.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 有向无环图的符号图
 * <p>
 * 因为跟SymbolGraph几乎一模一样，就不做任何注释了
 * Author:起舞的日子
 * Date:  2020/4/25 上午11:55
 */
public class SymbolDigraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Digraph G;

    public SymbolDigraph(String stream, String sp) {
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String name : keys) {
            keys[st.get(name)] = name;
        }
        G = new Digraph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
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

    public Digraph G() {
        return G;
    }
}
