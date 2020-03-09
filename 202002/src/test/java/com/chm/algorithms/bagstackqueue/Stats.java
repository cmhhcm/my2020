package com.chm.algorithms.bagstackqueue;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Author:meice Huang
 * Time: 2020/3/7 下午9:51
 */
public class Stats {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty() && StdIn.readDouble() != 1.11) {
            numbers.add(StdIn.readDouble());//实际操作发现没法终止,所以加了1.11
        }
        int N = numbers.size();
        double sum = 0.0;
        for (double x : numbers) {
            sum += x;
        }
        double mean = sum / N;
        sum = 0.0;
        //标准差：每个值和平均值之差的平方和除以N-1之后的平方根
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double std = Math.sqrt(sum / (N - 1));
        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("Std dev:  %.2f\n", std);
    }

    /**
     * 总结：
     * 1、Bag，这是标准库的包，实际代码中我没看到过用这个的；
     * 2、就是终端输入及时中断，计算过程没
     */
}
