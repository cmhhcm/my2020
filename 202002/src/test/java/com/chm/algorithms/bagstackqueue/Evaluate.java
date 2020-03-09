package com.chm.algorithms.bagstackqueue;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;

/**
 * Author:meice Huang
 * Time: 2020/3/7 下午10:47
 * Dijkstra的双栈算术表达式求值算法（二十世纪六十年代）
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty() && !StdIn.readString().equals("exist")) {
            String s = StdIn.readString();
            if (s.equals("(")) {

            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                Double v = vals.pop();
                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v;
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);
                }
            } else if (!s.equals("exist")) {
                vals.push(Double.parseDouble(s));
            }
        }
    }

    /**
     * 总结：
     * 1、通过栈的方式来处理计算问题，且通过)来计算前2个数，思想非常精妙！
     * 2、不知为何我的exist不能中断输入？
     */
}
