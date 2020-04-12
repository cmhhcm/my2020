package com.cmh.Lambdaandstream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

import static java.util.Comparator.comparingInt;

/**
 * @author: meice Huang
 * @date: 2020/3/29 下午4:26
 */
public class LambdaAndStreamTest {

    /**
     * <Effective Java>第42条：Lambda优先匿名类
     */

    /**
     * 1、用传统方法对一个字符串集合排序
     */

    List<String> strings = Arrays.asList("dfkaf", "io", "qoda", "dkfajflfdkaow", "a", "ceefh", "fajsdkfafodwieiefajkdafo");

    @Test
    public void testSortStrAsc() {
        //1、传统方法对给定字符串按长度ASC排序
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }

        });
        System.out.println(strings.toString());

        //2. 用Lambada方式写
        Collections.sort(strings, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }

    /**
     * 2、用Lambada方式改进方法1-testSortStr1
     *
     * @see #testSortStrAsc
     * 核心：
     * 1）Lambada使用的场景是创建函数接口的实例；函数接口指的这个接口只有一个抽象方法(不算default方法)；一般有@FunctionalInterface标记；
     * 2）替代了匿名类很多样本代码，看起来就是要做什么
     */
    @Test
    public void testEnhanceSort1() {
        Collections.sort(strings, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println(strings.toString());
    }

    /**
     * 3、在以上2个方法上继续简化代码
     *
     * @see #testSortStrAsc
     * @see #testEnhanceSort1
     * 核心：
     * 1、利用已有的静态比较方法
     * 2、方法优先Lambada表达式,因为comparingInt底层就是用lambada写的
     */
    @Test
    public void testEnhanceSort2() {
        Collections.sort(strings, comparingInt(String::length));
        System.out.println(strings);
    }

    /**
     * 4、在3的基础上做最后优化
     *
     * @see #testEnhanceSort2()
     * 核心：
     * java8在List接口中就添加了sort方法，可以直接使用
     * ☆☆☆☆☆ 大力推荐使用！
     */
    @Test
    public void testEnhanceSort3() {
        strings.sort(comparingInt(String::length));
        System.out.println(strings);
    }

    /**
     * 5、在笔者以下博客基础上,可以对：特定于常量的方法实现 做优化
     * https://blog.csdn.net/meiceatcsdn/article/details/104744859
     */

    enum Operation {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y),
        TIMES("*", (x, y) -> x * y),
        DIVIDE("/", (x, y) -> x / y);
        private final String symbol;
        private final DoubleBinaryOperator op;

        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    /**
     * 第43条：方法引用优先于Lambada
     * <p>
     * <p>
     * 1、方法引用，就是说方法可以作为"一等公民"作为参数传递了，类似值传递
     */
    @Test
    public void testMethodReference() {
        Map<String, Integer> map = new HashMap<>();
        map.put("ssa", 3);
        map.merge("ssa", 1, (count, incr) -> count + incr);
        //作用：找指定的键"ssa"是否有对应的值？如果没有，就把指定的值1插入；如果有，就把给定的value和"ssa"对应的值相加
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    /**
     * 2、优化
     *
     * @see #testMethodReference()
     */

    @Test
    public void enhanceMethodReference() {
        Map<String, Integer> map = new HashMap<>();
        map.put("ssa", 3);
        map.merge("ssa", 1, Integer::sum);
    }
}

