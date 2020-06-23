package com.cmh.parttendesign;

/**
 * @author huangmc 2020/5/31 下午5:57
 */
public class TestRerun {
    public static void main(String[] args) {
        testA();
    }

    public static void testA(){

        String[] split = "https://dzj-test.oss-cn-shanghai.aliyuncs.com/2019/06/12/5d00a2c9d08e37cabfd9d867.jpg".split(":,");
        if(split.length==0){
            System.out.println("长度为0");
        }else{
            for(String s:split){
                System.out.println(s);
                System.out.println();
            }
        }
    }
}
