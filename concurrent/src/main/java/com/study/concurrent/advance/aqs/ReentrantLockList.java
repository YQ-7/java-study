package com.study.concurrent.advance.aqs;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock实现线程安全的list
 */
public class ReentrantLockList {

    private ArrayList<String> array = new ArrayList<>();

    // 独占锁
    private volatile ReentrantLock lock = new ReentrantLock();

    public void add(String e) {
        lock.lock();
        try {
            array.add(e);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String e) {
        lock.lock();
        try {
            array.remove(e);
        } finally {
            lock.unlock();
        }
    }

    public void get(int index) {
        lock.lock();
        try {
            array.get(index);
        } finally {
            lock.unlock();
        }
    }
}
