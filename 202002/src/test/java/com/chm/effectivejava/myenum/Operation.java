package com.chm.effectivejava.myenum;

public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unkonwn op:" + this);
    }
    /**
     * 总结
     * 1、增加枚举类型，需要同时在switch中增加，如果忘记会导致计算不准确；
     * 2、:
     */
}
