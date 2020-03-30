package com.study.concurrent.basic.notify;

/**
 * notify():一个线程调用共享对象的notify()后， 会随机唤醒一个在该共享变量上调用wait系列方法后被挂起的线程
 * notifyAll():会唤醒所有在该共享变量上调用wait系列方法而被挂起的线程
 */
public class NotifyWaitThread {
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (resourceA) {
                        System.out.println("threadA get resourceA lock");
                        System.out.println("threadA begin wait");
                        resourceA.wait();
                        System.out.println("threadA end wait");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadC get resourceA lock");
                    System.out.println("threadC begin notify");
                    resourceA.notify();
//                    resourceA.notifyAll();
                    System.out.println("threadC end wait");
                }
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");
    }
}
