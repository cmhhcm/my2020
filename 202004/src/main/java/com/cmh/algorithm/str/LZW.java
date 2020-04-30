package com.cmh.algorithm.str;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * LZW算法压缩
 * Author:起舞的日子
 * W* Date:  2020/4/30 23:44
 */
public class LZW {
    /**
     * 霍夫曼算法是为输入中的定长模式产生一张边长的编码编译表，
     * LZW算法是三位算法发明人名字的缩写：思想与霍夫曼相反，是为输入中的变长模式生成一张
     * 定长的编码编译表：
     * 它有令人惊讶的特性：输出中不需要附上这张编译表
     */

    /**
     * 输入字符数
     */
    private static final int R = 256;

    /**
     * 编码总数
     */
    private static final int L = 4096;

    /**
     * 编码宽度
     */
    private static final int W = 12;

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }
        //R为文件结束(EOF)的编码
        int code = R + 1;
        while (input.length() > 0) {
            //找到匹配的最长前缀
            String s = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(s), W);
            int t = s.length();
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand() {
        String[] st = new String[L];
        //下一个待补全的编码值
        int i;
        for (i = 0; i < R; i++) {
            st[i] = "" + (char) i;
        }
        //(未使用)文件结束标记(EOF)的前瞻字符
        st[i++] = " ";
        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) {
                break;
            }
            //获取下一个编码
            String s = st[codeword];
            //如果前瞻字符不可用
            if (i == codeword) {
                //根据上一个字符串的首字母得到编码的字符串
                s = val + val.charAt(0);
            }
            if (i < L) {
                st[i++] = val + s.charAt(0);
            }
            val = s;
        }
        BinaryStdOut.close();
    }

}
