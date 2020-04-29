package com.cmh.algorithm.str;

/**
 * 暴力子字符串查找
 * Author:起舞的日子
 * Date:  2020/4/29 09:06
 */
public class ViolenceSearch {

    /**
     * 一种实现
     */

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                //找到匹配
                return i;
            }
        }
        //未找到匹配
        return N;
    }

    /**
     * 另一种实现
     * 显式回退
     */
    public static int search2(String pat, String txt) {
        int j, M = pat.length();
        int i, N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }
}
