package com.chm.effectivejava.myenum;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 特定于常量的类主体constant-specific class body
 * 特定于常量的方法的实现constant-specific method implementation
 */
public enum OperationV2 {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    OperationV2(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }//重写了toString方法，意味着只要打印这个类，就是"+-*/"这种符号了，valueOf()方法就没法用了

    public abstract double apply(double x, double y);

    //注意：这里需要通过命令行运行哈:
    public static void main(String[] args) {
        double number1 = Double.parseDouble(args[0]);
        double number2 = Double.parseDouble(args[1]);
        for (OperationV2 op : values()) {
            String result = String.format("%f %ps %f = %f", number1, op, number2, op.apply(number1, number2));
            System.out.println(result);
        }
    }

    private static final Map<String, OperationV2> stringToEnum = Arrays.stream(values())
            .collect(Collectors.toMap(OperationV2::toString, e -> e));

    public static Optional<OperationV2> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    /**
     * 总结
     * 1、特定于常量的方法的实现，这个在生产代码中少见；个人觉得会让枚举类显得很臃肿，不过确实是一种方案；
     * 2、stringToEnum方法写得很好,用到了java8的知识；
     * 3、fromString用到了Optional<>
     */

}
