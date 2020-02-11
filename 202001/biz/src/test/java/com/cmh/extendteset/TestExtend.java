package com.cmh.extendteset;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:meice Huang
 * Time: 2020/2/5 下午3:50
 */
public class TestExtend {
    public static void main(String[] args) {

        Entity entity = new Entity();
        entity.setCreatedBy("entity createdBy");
        BaseEntity baseEntity = new BaseEntity();

        String s = "\"<p>发打发打发<span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span></p>\\n<p>&nbsp;</p>\\n<p>发打发打发<span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\">发打发打发</span></p>\\n<p>&nbsp;</p>\\n<p><span style=\\\"font-family: -apple-system, BlinkMacSystemFont, 'Microsoft Yahei', 'WenQuanYi Micro Hei', 'Segoe UI', STHeiti, Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\\\"><img src=\\\"https://dzj-test.oss-cn-shanghai.aliyuncs.com/2020/02/07/5e3d1b21e4b0c53877df685b.jpg\\\" alt=\\\"首页Banner-2.jpg\\\" width=\\\"1024\\\" height=\\\"576\\\" /></span></p>\"";
        System.out.println(s.length());//2603
        String Cnstr = "中国";
        System.out.println(Cnstr.length());
        System.out.println(Cnstr.getBytes().length);

        Map<String,Integer> unReadAmount = new HashMap<>();
        unReadAmount.put("123",123);
        System.out.println(unReadAmount.get("fda"));

    }
}
