package com.study.concurrent.advance.aqs;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

public class ProducerConsumerTest {

    private final static NonReentrantLock lock = new NonReentrantLock();
    private final static Condition notFull = lock.newCondition();
    private final static Condition notEmpty = lock.newCondition();

    private static Queue<String> queue = new LinkedBlockingQueue<>();
    private static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {

                    // 如果队列满了，则等待
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }

                    queue.add("ele");
                    // 唤醒消费线程
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (0 == queue.size()) {
                        notFull.await();
                    }
                    System.out.println("consumer: " + queue.poll());
                    // 唤醒生产线程
                    notEmpty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        producer.start();
        consumer.start();
    }

}
