package com.study.concurrent.basic.interrupt;

/**
 * 中断sleep线程退出程序
 */
public class ExitWhenInterruptedSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread begin sleep for 2000 seconds");
                    Thread.sleep(2000000);
                    System.out.println("thread awaking");
                } catch (InterruptedException e) {
                    System.out.println("thread is interrupted while sleeping");
                    return;
                }
                System.out.println("thread-leaving normally");
            }
        });

        thread.start();
        Thread.sleep(1000);

        thread.interrupt();

        thread.join();
        System.out.println("main is over");
    }
}
