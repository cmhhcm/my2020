package com.chm.effectivejava.myenum;

public enum PayrollDay {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    private static final int MINS_PER_SHIFT = 8 * 60;

    int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;
        int overtimePay;
        switch (this) {
            case SATURDAY:
            case SUNDAY:
                overtimePay = basePay / 2;
                break;
            default:
                overtimePay = minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
        }

        return basePay = overtimePay;
    }

    /**
     * 总结
     * 1、这段代码很简洁的表明了周内和周末算工资的方式；
     * 2、不足：新增枚举的时候，需要改变switch。
     * 类似这种枚举变化可能性还是很大的，但是频率会很低。最好能做到为每种类型的常量都有对应的报酬方式。
     */
}
