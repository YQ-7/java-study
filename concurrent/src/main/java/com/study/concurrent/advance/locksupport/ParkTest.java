package com.study.concurrent.advance.locksupport;

import java.util.concurrent.locks.LockSupport;

public class ParkTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park!");
                LockSupport.park();
                System.out.println("child thread unpark!");

            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark!");
        // 使thread线程获取到许可证
        LockSupport.unpark(thread);
    }
}
