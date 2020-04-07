package com.study.concurrent.advance.aqs;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用ReentrantLock实现线程安全的list
 */
public class RWReentrantLockList {

    private ArrayList<String> array = new ArrayList<>();

    // 读写锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.readLock();

    public void add(String e) {
        writeLock.lock();
        try {
            array.add(e);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String e) {
        writeLock.lock();
        try {
            array.remove(e);
        } finally {
            writeLock.unlock();
        }
    }

    public void get(int index) {
        readLock.lock();
        try {
            array.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
