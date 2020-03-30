package com.study.concurrent.basic.volatiletest;

/**
 * volatile:
 *      保证内存可见性
 *      防止指令重排
 */
public class WriteAndRead {
    private static int num = 0;
    private volatile static boolean ready = false;

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                    break;
                }
            }
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread rt = new ReadThread();
        rt.start();
        WriteThread wt = new WriteThread();
        wt.start();
        Thread.sleep(10);
        rt.interrupt();
        System.out.println("main exit");
    }
}
