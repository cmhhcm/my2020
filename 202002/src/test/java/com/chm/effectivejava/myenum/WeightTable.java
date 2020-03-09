package com.chm.effectivejava.myenum;

/**
 * Author:meice Huang
 * Time: 2020/3/8 下午10:31
 */
public class WeightTable {
    public static void main(String[] args) {
        //根据某个物体在地球上的重量打印出在各个行星上的重量
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet planet : Planet.values()) {
            System.out.printf("Weight on %s is %f%n", planet, planet.surfaceWeight(mass));
        }
    }
}
