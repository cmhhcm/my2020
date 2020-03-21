package com.chm.foundation;

/**
 * Author:meice Huang
 * Time: 2020/3/15 下午10:35
 */
public class StatisticsDTO {
    private int completedCount = 10;

    public int getCompletedCount() {
        return completedCount;
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
                "completedCount=" + completedCount +
                '}';
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }
}
