package com.chm.effectivejava.myenum;

/**
 * Author:meice Huang
 * Time: 2020/3/8 下午8:22
 */
public enum Planet {

    MERCURY(3.302e+23, 2.439e6),

    VENUS(4.869e+24, 6.502e6),

    EARTH(5.975e+24, 6.378e6),

    MARS(6.419e+23, 6.378e6),

    JUPITER(1.899e+27, 6.378e6),

    SATURN(5.685e+26, 6.378e6),

    URANUS(8.683e+25, 6.378e6),

    NEPTUNE(1.204e+26, 6.378e6);

    private final double mass;// In kilograms

    private final double radius;// In meters

    private final double surfaceGravity; //In m/s^2

    private static final double G = 6.67E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity; // F =ma;
    }
}
