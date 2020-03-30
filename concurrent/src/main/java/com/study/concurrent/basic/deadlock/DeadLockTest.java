package com.study.concurrent.basic.deadlock;

/**
 * 线程死锁
 * 避免死锁：使用资源申请的有序性原则
 */
public class DeadLockTest {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get ResourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + " waiting get ResourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + " get ResourceB");
                    }

                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            // 获取共享资源的顺序与threadA保持一致可避免死锁
            @Override
            public void run() {
                synchronized (resourceB) {
//                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get ResourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + " waiting get ResourceA");
                    synchronized (resourceA) {
//                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + " get ResourceA");
                    }

                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
