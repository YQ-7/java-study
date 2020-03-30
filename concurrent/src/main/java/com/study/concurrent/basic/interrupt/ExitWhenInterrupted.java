package com.study.concurrent.basic.interrupt;

/**
 * 根据中断标志判断程序是否退出
 */
public class ExitWhenInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + " hello");
                }
                System.out.println(Thread.currentThread() + " is over");
            }
        });

        thread.start();
        Thread.sleep(1000);

        System.out.println("main thread interrupt thread");
        thread.interrupt();

        thread.join();
        System.out.println("main is over");

    }
}
