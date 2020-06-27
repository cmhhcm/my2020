package com.cmh.executortest;

import org.junit.Test;

/**
 * @author huangmc 2020/6/23 下午4:24
 */
public class JunitThreadTest {
    /**
     * main方法中，先执行主线程，所有线程执行完毕算结束.
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//子线程睡眠2s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("子线程结束...");
            }
        }).start();
        System.out.println("main方法的主线程结束...");
    }


    /**
     * Junit测试中，主线程结束后，子线程不在执行。
     */
    @Test
    public void testJunitThreadExecuteOrder() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Junit测试子线程开始...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Junit测试子线程结束...");
            }
        }).start();
        System.out.println("Junit测试主线程结束...");
    }

    @Test
    public void testJunitThreadExecuteOrder2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Junit 子线程开始...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Junit 子线程结束...");
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Junit 主线程结束...");
    }


    /**
     * 结论:
     * 1、main方法中，先执行main方法的线程，再执行子线程;所有线程执行完毕，程序才算执行完毕；
     * 2、Junit中，主线程结束，子线程随即结束。
     */
}
