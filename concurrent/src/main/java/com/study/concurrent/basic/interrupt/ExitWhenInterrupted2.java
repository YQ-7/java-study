package com.study.concurrent.basic.interrupt;

/**
 * 根据中断标志判断程序是否退出
 * 使用Thread.interrupted()
 */
public class ExitWhenInterrupted2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 中断标志为true退出循环，并清除中断标志
                while (!Thread.interrupted()) {
                    System.out.println(Thread.currentThread() + " hello");
                }
                System.out.println("thread isInterrupted:" + Thread.currentThread().isInterrupted());
            }
        });

        thread.start();
        Thread.sleep(100);

        System.out.println("main thread interrupt thread");
        thread.interrupt();

        thread.join();
        System.out.println("main is over");

    }
}
