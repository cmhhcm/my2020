package com.cmh.algorithm;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 字典的查找
 * <p>
 * eg:对于一些.csv格式的文档，文档示例的内容都是用,分隔开的
 * Author:起舞的日子
 * Date:  2020/4/18 上午6:12
 */
public class LookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
        ST<String, String> st = new ST<>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);
        }
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) StdOut.println(st.get(query));
        }
    }

    /**
     * 总结：
     * 1、根据key 找val
     * 2、对现实或者产品设计的场景要能灵活运用
     */
}
