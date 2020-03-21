package com.chm.myutils;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

/**
 * Author:meice Huang
 * Time: 2020/3/6 下午10:15
 *
 * @see StringUtils#UUID()   _20200320
 * @see StringUtils#isEmpty(String)
 * @see StringUtils#isNotEmpty(String)
 */
public class StringUtils {

    public Long parseLongsafe(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
        }

        return null;
    }

    @Test
    public void testNpe() {
        Long result = parseLongsafe("fdafkalfkdafafdakf");
        System.out.println(result);
    }

    @Test
    public void testStrFormat() {
        //格式化字符串 %s %格式转换符
        System.out.printf("fkadkfa%skfdajk%3fkkfdkafk%nfkdalkfd%d", "cmh", 2.4455, 34);
        //%tc 格式化日期,以t开始，以任意日期-时间转换符结束
        System.out.printf("%n%tc", new Date());//星期日 三月 08 22:57:17 CST 2020

        System.out.printf("%n%tF", new Date());//2020-03-08
        // 格式化参数索引：%索引$
        System.out.printf("%n %1$s %2$tY  %2$te/%2$tm %2$tT", "时间", new Date());
    }

    @Test
    public void testUUID() {
        String strUUid = UUID.randomUUID().toString();
        System.out.println(strUUid);
        System.out.println(strUUid.substring(0, 7));
        System.out.println(UUID());
    }

    public static String UUID() {
        String strUUID = UUID.randomUUID().toString();
        System.out.println(strUUID);
        return strUUID.substring(0, 8) + strUUID.substring(9, 13) + strUUID.substring(14, 18) + strUUID.substring(19, 23) + strUUID.substring(24);
    }

    public static boolean isEmpty(String s) {
        return (s == null || "".equals(s));
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
