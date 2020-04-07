package com.study.concurrent.advance.locksupport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 利用park实现先进先出锁
 */
public class FIFOMutex {

    // 是否已有线程持有锁标志位
    private final AtomicBoolean locked = new AtomicBoolean(false);
    // 请求锁的线程队列
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    // 线程请求锁
    public void lock() {
        boolean wasInterrupted = false;
        // 获取请求线程
        Thread current = Thread.currentThread();
        waiters.add(current);

        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            // 不是队首线程或未成功获得锁的线程需被挂起
            LockSupport.park(this);
            // 若挂起线程被中断，则记录被中断标志
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
            waiters.remove();
            if (wasInterrupted) {
                // 被中断的线程需将中断标志置位true
                current.interrupt();
            }
        }
    }

    // 释放锁
    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
