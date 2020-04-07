package com.study.concurrent.advance.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程情况下，利用AtomicLong统计数据源中0的个数
 */
public class Atomic {

    // 创建Long类型的原子计数器
    private static AtomicLong atomicLong = new AtomicLong();

    // 创建数据源
    private static Integer[] arrayOne = new Integer[] {0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[] {10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                for (Integer n : arrayOne) {
                    if (n == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                for (Integer n : arrayTwo) {
                    if (n == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("count 0:" + atomicLong.get());
    }
}
