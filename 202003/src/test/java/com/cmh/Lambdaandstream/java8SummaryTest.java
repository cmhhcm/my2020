package com.cmh.Lambdaandstream;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: meice Huang
 * @date: 2020/4/4 下午11:15
 */
public class java8SummaryTest {
    @Test
    public void mapTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("abc", 111);
        map.put("acd", 222);
        map.put("ade", 333);
        map.put("aef", 444);
        //entrySet方式
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.forEach(e -> {
            System.out.println("k:" + e.getKey() + " v:" + e.getValue());
        });

    }

}
