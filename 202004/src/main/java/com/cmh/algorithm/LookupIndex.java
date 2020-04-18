package com.cmh.algorithm;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 索引(以及反向索引)的查找
 * <p>
 * Author:起舞的日子
 * Date:  2020/4/18 上午7:04
 */
public class LookupIndex {
    public static void main(String[] args) {
        /**
         * 索引数据库
         */
        In in = new In(args[0]);
        /**
         * 分隔符
         */
        String sp = args[1];

        ST<String, Queue<String>> st = new ST<>();
        ST<String, Queue<String>> ts = new ST<>();

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            String key = args[0];
            for (int i = 1; i < a.length; i++) {
                String val = a[i];
                if (!st.contains(key)) {
                    st.put(key, new Queue<String>());
                }
                if (!ts.contains(key)) {
                    ts.put(val, new Queue<String>());
                }
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query)) {
                for (String s : st.get(query)) {
                    StdOut.println(" " + s);
                }
            }
            if (ts.contains(query)) {
                for (String s : ts.get(query)) {
                    StdOut.println(" " + s);
                }
            }
        }
    }

    /**
     * 总结：
     * 1、 索引：这里的索引指的是 描述一个键和多个值相关联的符号表
     * 2、反向索引：将键和值的角色互换，一般指的是根据值查找键
     * 3、对照索引 concordance
     */

}
