package com.chm.foundation;

import org.junit.jupiter.api.Test;

/**
 * Author:meice Huang
 * Time: 2020/3/15 下午10:34
 */
public class TestObjectSetValue {

    @Test
    public void testSetValueForObj(StatisticsDTO statisticsDTO) {
        StatisticsDTO dto = new StatisticsDTO();
        dto.setCompletedCount(dto.getCompletedCount() + 1);
        System.out.println(dto);
    }

    @Test
    public void test(){
        StatisticsDTO dto = new StatisticsDTO();
        testSetValueForObj(dto);
    }
}
