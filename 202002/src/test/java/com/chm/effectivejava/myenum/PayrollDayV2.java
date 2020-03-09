package com.chm.effectivejava.myenum;

public enum PayrollDayV2 {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY(PayType.WEEKEND),
    SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDayV2() {
        this.payType = PayType.WEEKDAY;
    }

    PayrollDayV2(PayType payType) {
        this.payType = payType;
    }

    int pay(int minsWorked, int payRate) {
//        return PayType.pay(minsWorked,payRate);//这里虽然不能引用，但是大体就是这个思想
        return 0;
    }

    private enum PayType {
        WEEKDAY {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }

    /**
     * 总结：
     * 1、策略模式
     * 2、如果扩展，非常方便且不会出错。加入新冠肺炎疫情期间，居家办公新增了一种考核方式
     * VOVID-19-EXAMINE,
     * 只需要新增一个PayType，重写overtimePay，然后在PayRollDayV2中新增一个常量并指定类型即可。
     *
     */
    public static void main(String[] args) {
        System.out.println(MONDAY.ordinal());
        System.out.println(TUESDAY.ordinal());
    }
}
