package com.chm.java8;

import com.chm.java8.material.CaloricLevel;
import com.chm.java8.material.Dish;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.chm.java8.material.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

/**
 * Author:meice Huang
 * Time: 2020/2/23 下午1:47
 */

public class java8CollectorsTest {

    @Test
    public void testCollectors() {
        Long howManyDishess = menu.stream().collect(counting());
        long dishesCount = menu.stream().count();
        println("howManyDishes:" + howManyDishess);
        //查找流中最大值和最小值
        Optional<Dish> maxCaloriesDish = menu.stream()
                .reduce((a, b) -> a.getCalories() > b.getCalories() ? a : b);
        println(maxCaloriesDish);

        //maxBy+自定义Comparator
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxCaloriesDish2 = menu.stream()
                .collect(maxBy(dishCaloriesComparator));
        println(maxCaloriesDish2);

        //菜单总卡路里数
        Integer sumCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));
        println(sumCalories);

        IntSummaryStatistics intSummaryStatistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        println(intSummaryStatistics);

        //joining
        String joinedDishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining());
        println(joinedDishNames);//缺点是连接起来不够清晰

        String joinedDishNames2 = menu.stream()
                .map(Dish::getName)
                .collect(joining("  ,"));
        println(joinedDishNames2);

        //collect(reduct())
        Optional<Dish> maxCalories = menu.stream()
                .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        println(maxCalories);

        Integer totalCaloriesByReduce = menu.stream()
                .collect(reducing(0, Dish::getCalories, (c1, c2) -> c1 + c2));
        println(totalCaloriesByReduce);

        Integer totalCaloriesByReduce2 = menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        println(totalCaloriesByReduce2);

        Integer totalCaloriesByMap = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .get();
        int totalCaloriesByMap2 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        println(totalCaloriesByMap);
        println(totalCaloriesByMap2);

        //分组

        Map<Dish.Type, List<Dish>> groupByType = menu.stream()
                .collect(groupingBy(Dish::getType));

        println(groupByType);

        Map<CaloricLevel, List<Dish>> groupByType2 = menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));
        println(groupByType2);

        //多级分组

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> multiGroupBy = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })));
        println(multiGroupBy);

        Map<Dish.Type, Long> countByType = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        println(countByType);

        Map<Dish.Type, Optional<Dish>> maxCaloryDish = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        println(maxCaloryDish);
        /**
         * 结果：
         * k:FISH	 v:Optional[salmon]
         * k:MEAT	 v:Optional[pork]
         * k:OTHER	 v:Optional[pizza]
         */

        //去除Optional

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        println(mostCaloricByType);

        //分区
        Map<Boolean, List<Dish>> partition = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        println(partition);

        List<Dish> partition2 = menu.stream()
                .filter(Dish::isVegetarian).collect(toList());
        println(partition2);

    }

    private void println(Object obj) {
        if (obj instanceof Optional) {
            Optional op = (Optional) obj;
            System.out.println(op.get().toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++");
        } else if (obj instanceof Map) {
            Map objMap = (Map) obj;
            objMap.forEach((k, v) -> {
                System.out.println("k:" + k + "\t v:" + v.toString());
            });
            System.out.println("+++++++++++++++++++++++++++++++++++++");
        } else if (obj instanceof List) {
            List obj1 = (List) obj;
            System.out.println(obj1.toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++");
        } else {
            System.out.println(obj.toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++");
        }
    }

}
