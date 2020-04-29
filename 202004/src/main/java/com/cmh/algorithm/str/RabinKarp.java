package com.cmh.algorithm.str;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp指纹字符串查找算法
 * Author:起舞的日子
 * Date:  2020/4/29 07:37
 */
public class RabinKarp {
    /**
     * 模式字符串(仅拉斯维加斯算法需要)
     */
    private String pat;

    /**
     * 模式字符串的散列值
     */
    private long patHash;

    /**
     * 模式字符串的长度
     */
    private int M;

    /**
     * 一个很大的素数
     */
    private long Q;

    /**
     * 字母表大小
     */
    private int R = 256;

    /**
     * R^(M-1)%Q
     */
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i < M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    /**
     * 拉斯维加斯
     */
    private boolean check(String txt, int i) {
        for (int j = 0; j < M; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;

    }

    /**
     * 蒙特卡洛
     */
    public boolean check(int i) {
        return true;
    }

    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    private int search(String txt) {
        //在文本中查找相等的散列值
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(0)) {
            //一开始就匹配成功
            return 0;
        }
        for (int i = M; i < N; i++) {
            //减去第一个数字，加上最后一个数字，再次检查匹配
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                if (check(i - M + 1)) {
                    return i - M + 1;
                }
            }
        }
        return N;
    }
}
