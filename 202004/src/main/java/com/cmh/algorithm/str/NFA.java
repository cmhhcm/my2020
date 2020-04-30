package com.cmh.algorithm.str;

import com.cmh.algorithm.graph.Digraph;
import com.cmh.algorithm.graph.DirectedDFS;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

/**
 * 正则表达式的模糊匹配(grep)
 * Author:起舞的日子
 * Date:  2020/4/30 07:52
 */
public class NFA {
    /**
     * 匹配转换
     */
    private char[] re;

    /**
     * epsilon转换
     */
    private Digraph G;

    /**
     * 状态数量
     */
    private int M;

    public NFA(String regexp) {
        //根据给定的正则表达式构建NFA(非确定有限状态自动机)
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1);
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == 'C' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }
            //查看下一个字符
            if (i < M - 1 && re[i + 1] == '*') {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if (re[i] == 'C' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i + 1);
            }
        }
    }

    /**
     * NFA能否识别文本txt?
     */
    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }
        for (int i = 0; i < txt.length(); i++) {
            //计算txt[i+1]可能到达的所有NFA的状态
            Bag<Integer> match = new Bag<>();
            for (int v : pc) {
                if (v < M) {
                    if (re[v] == txt.charAt(i) || re[v] == '.') {
                        match.add(v + 1);
                    }
                }
            }
            pc = new Bag<>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }

        for (int v : pc) {
            if (v == M) {
                return true;
            }
        }
        return false;

    }
}
