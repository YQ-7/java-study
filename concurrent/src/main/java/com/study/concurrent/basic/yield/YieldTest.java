package com.study.concurrent.basic.yield;

/**
 * yield():
 *  线程让出CPU使用权，然后处于就绪状态
 *  线程调度器会从线程就绪队列中获取一个优先级最高的线程，有可能会调度到刚刚让出CPU的线程
 */
public class YieldTest implements Runnable {

    public YieldTest() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + " yield cpu...");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + " is over");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
