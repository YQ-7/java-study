package com.study.concurrent.basic.wait;

/**
 *  其他线程调用了挂起线程的interrupt()方法，
 *  则挂起线程抛出InterruptedException异常返回
 */
public class WaitNotifyInterrupt {

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("---begin---");
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("---end---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("--begin interrupt threadA---");
        threadA.interrupt();
        System.out.println("--end interrupt threadA---");
    }
}
