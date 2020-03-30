package com.study.concurrent.basic.join;

/**
 * 线程A调用线程B的join方法后被阻塞，当其他线程调用线程A的interrupt()方法中断线程A时，
 * 线程A会抛出InterruptedException异常返回
 */
public class InterruptJoinThread {

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadOne begion run");
                for(;;) {
                }
            }
        });

        Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 中断主线程
                mainThread.interrupt();
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println("main thread:" + e);
        }
    }
}
