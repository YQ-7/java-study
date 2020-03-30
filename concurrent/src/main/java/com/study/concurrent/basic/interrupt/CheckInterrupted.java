package com.study.concurrent.basic.interrupt;

/**
 * isInterrupted()：检测当前线程是否被中断，不会清除中断标志
 * interrupted(): 静态方法，检测当前线程是否被中断，会清除中断标志
 */
public class CheckInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                }
            }
        });

        threadOne.start();

        threadOne.interrupt();
        System.out.println("threadOne isInterrupted:" + threadOne.isInterrupted());
        // 获取中断标志，并重置
        // 这里返回False: interrupted()是静态方法，返回的是当前main线程的中断标志
        System.out.println("main thread isInterrupted:" + threadOne.interrupted());
        System.out.println("main thread isInterrupted:" + Thread.interrupted());
        System.out.println("threadOne isInterrupted:" + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("main is over");

    }
}
