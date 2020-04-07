package com.study.concurrent.advance.aqs;

import java.util.concurrent.locks.StampedLock;

public class Point {

    private double x, y;

    // 锁实例
    private final StampedLock s1 = new StampedLock();


    public void move(double deltaX, double deltaY) {
        // 使用排它锁--写锁
        long stamp = s1.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            s1.unlockWrite(stamp);
        }
    }


    public double distanceFromOrigin() {
        // 尝试获取乐观读锁
        long stamp = s1.tryOptimisticRead();
        // 复制变量到方法栈
        double currentX = x;
        double currentY = y;

        // stamp失效，则将锁转换为悲观读锁
        if (!s1.validate(stamp)) {
            stamp = s1.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                s1.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = s1.readLock();
        try {
            // 当前点在原点，则移动
            while (x == 0.0 && y == 0.00) {
                // 尝试转换为写锁
                long ws = s1.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败
                    s1.unlockRead(stamp);
                    stamp = s1.writeLock();
                }
            }
        } finally {
            s1.unlock(stamp);
        }
    }
}
