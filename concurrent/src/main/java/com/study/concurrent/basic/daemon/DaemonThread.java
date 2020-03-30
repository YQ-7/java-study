package com.study.concurrent.basic.daemon;

/**
 * main线程运行结束后，JVM启动叫作DestroyJavaVM的线程，该线程会等待所有用户线程结束后终止JVM进程
 */
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread begin");
                for (;;) {
                }
            }
        });
        // 设置为守护进程
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread is over");
    }

}
