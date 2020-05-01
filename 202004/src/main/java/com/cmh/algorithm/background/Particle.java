package com.cmh.algorithm.background;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * 基于事件驱动模拟的粒子碰撞
 * 粒子对象
 * Author:起舞的日子
 * Date:  2020/5/1 00:51
 */
public class Particle {
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    /**
     * position(位置)
     */
    private double rx, ry;

    /**
     * velocity(速度)
     */
    private double vx, vy;

    /**
     * number of collisions so far
     * 碰撞的次数
     */
    private int count;

    /**
     * 半径
     */
    private final double radius;

    /**
     * 质量
     */
    private final double mass;

    private final Color color;

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
        this.vx = vx;
        this.vy = vy;
        this.rx = rx;
        this.ry = ry;
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    public Particle() {
        rx = StdRandom.uniform(0.0, 1.0);
        ry = StdRandom.uniform(0.0, 1.0);
        vx = StdRandom.uniform(-0.005, 0.005);
        vy = StdRandom.uniform(-0.005, 0.005);
        radius = 0.02;
        mass = 0.5;
        color = Color.BLACK;
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public int count() {
        return count;
    }

    /**
     * 距离该粒子和粒子b碰撞所需的时间
     */
    public double timeToHit(Particle that) {
        if (this == that) {
            return INFINITY;
        }
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) {
            return INFINITY;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        if (dvdv == 0) {
            return INFINITY;
        }
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) {
            return INFINITY;
        }
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    /**
     * 距离该粒子和垂直墙体碰撞所需的时间
     */
    public double timeToHitVerticalWall() {
        if (vx > 0) {
            return (1.0 - rx - radius) / vx;
        } else if (vx < 0) {
            return (radius - rx) / vx;
        } else {
            return INFINITY;
        }
    }

    /**
     * 距离该粒子和水平墙体碰撞所需的时间
     */
    public double timeToHitHorizontalWall() {
        if (vy > 0) {
            return (1.0 - ry - radius) / vy;
        } else if (vy < 0) {
            return (radius - ry) / vy;
        } else {
            return INFINITY;
        }
    }

    /**
     * 碰撞后该粒子的速度
     */
    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;

        double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;

        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;

        this.count++;
        that.count++;

    }

    /**
     * 碰撞垂直墙体后该粒子的速度
     */
    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
    }

    /**
     * 碰撞水平墙体后该粒子的速度
     */
    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
    }

    /**
     * 动能
     */
    public double kineticEnergy() {
        return 0.5 * mass * (vx * vx + vy * vy);
    }

}
