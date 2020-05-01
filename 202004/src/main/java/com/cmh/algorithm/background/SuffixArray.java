package com.cmh.algorithm.background;

import com.cmh.algorithm.str.Quick3String;

/**
 * 后缀数组(初级实现)
 * Author:起舞的日子
 * Date:  2020/5/1 17:23
 */
public class SuffixArray {
    /**
     * 用后缀排序来查找子字符串
     * 将N个后缀为键，以这些键(后缀)创建一个有序的数组并使用二分查找法搜索数组，
     * 比较被查找的键和所有后缀
     */

    /**
     * 后缀数组
     */
    private final String[] suffixes;

    /**
     * 字符串和数组的长度
     */
    private final int N;

    public SuffixArray(String s) {
        N = s.length();
        suffixes = new String[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = s.substring(i);
        }
        Quick3String.sort(suffixes);
    }

    public int length() {
        return N;
    }

    public String select(int i) {
        return suffixes[i];
    }

    public int index(int i) {
        return N - suffixes[i].length();
    }

    /**
     * 两个字符串的最长公共前缀
     */
    private static int lcp(String s, String t) {
        int N = Math.min(s.length(), t.length());
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return i;
            }
        }
        return N;
    }

    public int lcp(int i) {
        return lcp(suffixes[i], suffixes[i - 1]);
    }

    public int rank(String key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }

        }
        return lo;
    }

}
