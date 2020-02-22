package com.cmh.java8;

import static com.cmh.java8.material.Dish.menu;

import com.cmh.java8.material.Dish;
import com.cmh.java8.material.Trader;
import com.cmh.java8.material.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Author:meice Huang
 * Time: 2020/1/25 上午7:20
 */
public class Java8Test {
    /**
     * FlatMap test
     */
    @Test
    public void flatMapTest() {
        String[] words = {"Hello", "World"};
        List<String[]> result = Arrays.stream(words).map(word -> word.split(""))
                .distinct()
                .collect(toList());//映射成了数组流
        result.forEach(e -> {
            for (int i = 0; i < e.length; i++) {
                System.out.print(e[i] + "  ");
            }
        });

        /**
         * flatMap
         */
        List<String> resultStr = Arrays.stream(words)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.print(resultStr.toString() + " ");
        /**
         * flatMap可以使各个数组并不是分别映射成一个流，而是映射成流的内容
         * 让你把一个流中的每一个值都换成另一个流，然后把所有的流连接起来成为一个流。
         */
    }

    @Test
    public void flatMapTest2() {
        Integer[] list1 = {1, 2, 3};
        Integer[] list2 = {3, 4};
        List<Stream<Integer[]>> collect = Arrays.stream(list1)
                .map(i -> Arrays.stream(list2).map(j -> new Integer[]{i, j}))
                .collect(toList());//这个流数组不是我们想要的
        List<Integer[]> arrayList = Arrays.stream(list1).flatMap(i -> Arrays.stream(list2).map(j -> new Integer[]{i, j}))
                .collect(toList());
        arrayList.forEach(e -> {
            System.out.print(Arrays.asList(e));
        });
//        Arrays.stream(list1).flatMap(i -> Arrays.stream(list2).flatMap(j -> new int[]{i,j}));
    }

    /**
     * 发现2个都用flatMap是行不通的
     */

    @Test
    public void flatMapTest3() {
        Integer[] list1 = {1, 2, 3};
        Integer[] list2 = {3, 4};
        List<Integer[]> result = Arrays.stream(list1).flatMap(i -> Arrays.stream(list2)
                .filter(j -> (i + j) % 3 == 0)
                .map(j -> new Integer[]{i, j}))
                .collect(toList());

        result.forEach(e -> {
            System.out.println(Arrays.asList(e));
        });
    }

    /**
     * 查找和匹配
     * allMatch anyMatch noneMatch findFirst findAny
     */

    /**
     * anyMatch
     */
    @Test
    public void anyMatchTest() {
        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("has some vegetarian...");
        }

    }

    /**
     * allMatch
     */
    @Test
    public void allMatchTest() {
        boolean isHealth = menu.stream().allMatch(m -> m.getCalories() < 1000);
        System.out.println("菜单中的菜品是否都健康?" + isHealth);
    }

    /**
     * noneMatch
     */
    @Test
    public void noneMatchTest() {
        boolean isNotHealth = menu.stream().noneMatch(m -> m.getCalories() >= 10000);
        System.out.println(isNotHealth);
    }

    /**
     * 总结：
     * anyMatch、allMatch、noneMatch三个操作都用到了短路，类似||或者&&的短路机制。
     */

    @Test
    public void findAnyTest() {
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));//Optional<Dish>
    }

    /**
     * findFirst
     */
    @Test
    public void findFirst() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();

    }

    /**
     * 规约
     */
    @Test
    public void foldOperator() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //之前的求和方法
        int sum = 0;
        for (int a : numbers) {
            sum += a;
        }
        //lambda求和
        Integer reduceSum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("sum " + sum + "   reduceSum:" + reduceSum + " reduce" + reduceSum);

        //方法引用
        Integer reduce = numbers.stream()
                .reduce(0, Integer::sum);
        //reduce可不接受任何初始值
        Optional<Integer> sumOptional = numbers.stream()
                .reduce(Integer::sum);

        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
        Optional<Integer> max2 = numbers.stream()
                .reduce((a, b) -> a > b ? a : b);
        System.out.println("max:" + max + "  max2" + max2);

        //统计流中有多少个菜
        Integer dishSum = menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);

        long count = menu.stream().count();
        System.out.println("菜品的数量是：" + dishSum + "  直接用内置的count:" + count);
    }

    /**
     * 总结：
     * 1.map-reduce模式
     * 2.可变的累加器模式对于并行化来说是死路一条;如果你加入了同步，
     * 很可能会发现线程竞争抵消了并行本应带来的性能提升
     */

    /**
     * 第五章 8道练习题
     */
    @Test
    public void eightPractices() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /**
         * 1)
         */
        List<Transaction> transSortedByYearAsc = transactions.stream()
                .filter(e -> e.getYear() == 2011)
                .sorted()
                .collect(toList());
        transSortedByYearAsc.forEach(e -> {
            System.out.println(e);
        });

    }

    /**
     *
     */
    @Test
    public void test() {

    }
}
