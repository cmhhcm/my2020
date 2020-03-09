package com.chm.java8;

import com.chm.java8.material.Dish;
import com.chm.java8.material.Trader;
import com.chm.java8.material.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.chm.java8.material.Dish.menu;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

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
         * 1) 找出2011年发生的所有交易，并按交易额排序(ASC)
         */
        List<Transaction> result = transactions.stream()
                .filter(e -> e.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());//如果这里用toSet，会提示redundant
        print(result);

/**
 * 2)交易员都在哪些不同的城市工作过？
 */
        List<String> cities1 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());
        print(cities1);

        List<String> cities2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        print(cities2);

        Set<String> cities3 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());//toSet自带去重
        print(cities3);

        /**
         * 3）查找所有来自剑桥的交易员，并按照姓名排序
         */
        List<Transaction> cambridgeTraders = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .sorted(comparing(transaction -> transaction.getTrader().getName()))
                .collect(toList());
        print(cambridgeTraders);

        /**
         *4) 返回所有交易员的姓名字符串，按字母顺序排列
         */
        List<String> names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .collect(toList());
        print(names);

        String names2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .reduce(" ", (a, b) -> a + b);
        System.out.println(names2);
        /**
         * 5)有没有交易员是在米兰工作的？
         */
        boolean isWorkInMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(isWorkInMilan);
        Optional<Boolean> milan = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .findAny();
        /**
         * 6)打印生活在剑桥的交易员的所有交易额
         */
        Set<Integer> tradesInCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .collect(toSet());
        print(tradesInCambridge);

        /**
         * 7）所有交易额中最高交易额是多少?
         */
        Optional<Integer> maxValue = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::max);
        System.out.println(maxValue.get());

        /**
         * 8）找到交易额最小的交易
         */
        Optional<Transaction> minTrade = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(minTrade.get().getValue());

        Optional<Transaction> minTrade2 = transactions.stream()
                .min(comparing(transaction -> transaction.getValue()));
        System.out.println(minTrade2.get());

        /**
         * 数值流
         */

        Integer sumCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        int sumCalories2 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        long evenCount = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println(evenCount);

        //由值创建流
        Stream<String> valueStream = Stream.of("java8", "Lambdas", "in", "Acttion");
        valueStream.map(String::toUpperCase).forEach(System.out::print);
        Stream<Object> empty = Stream.empty();

        //由数组创建流
        int[] numbers = {1, 3, 455, 5, 55};
        int sumSream = Arrays.stream(numbers).sum();
        System.out.println(sumSream);

        //文件创建流
        try {
            long uniqueWords = 0;
            Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split("")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //函数生成流
        Stream.iterate(0, n -> n + 2)
                .limit(3)
                .forEach(System.out::println);
        //斐波那契数列
        //1 1 2 3 5 8 13 21 34 55 89 146 236 382
        //TODO

        //generate
        Stream.generate(Math::random)
                .limit(20)
                .forEach(System.out::println);

    }

    private void print(Collection collection) {
        collection.stream()
                .forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void test() {
        //重新温习reduce
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        System.out.println(sum);
        //使用reduce
        Integer sum2 = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum2);
        //用方法引用简化
        Integer sum3 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum3);
        //求最大最小值
        Optional<Integer> sumReduce = numbers.stream()
                .reduce(Integer::sum);

    }

    @Test
    public void reTestFlatMap(){
        String[] words = {"Hello","World"};
        List<String[]> collect = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        List<String> collect1 = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = numbers.stream()
                .map(each -> each * each)
                .collect(toList());
        result.stream()
                .forEach(System.out::println);

        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        List<Stream<int[]>> collect2 = number1.stream()
                .map(n1 -> number2.stream().map(n2 -> new int[]{n1, n2}))
                .collect(toList());
        List<int[]> collect3 = number1.stream()
                .flatMap(n1 -> number2.stream().map(n2 -> new int[]{n1, n2}))
                .collect(toList());
        collect3.stream()
                .forEach(System.out::print);

    }


}
