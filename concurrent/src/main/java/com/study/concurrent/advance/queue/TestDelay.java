package com.study.concurrent.advance.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TestDelay {

    static class DelayeEle implements Delayed {

        // 延时时间
        private final long delayTime;
        //到期时间
        private final long expire;

        private String taskName;

        public DelayeEle(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            this.expire = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(
                    this.expire - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("DelayedEle{");
            sb.append("delay=").append(delayTime);
            sb.append(",expire=").append(expire);
            sb.append(",taskName=").append(taskName);
            sb.append("}");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayeEle> delayQueue = new DelayQueue<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            DelayeEle element = new DelayeEle(
                    random.nextInt(500),
                    "task:" + i);
            delayQueue.offer(element);
        }
        DelayeEle ele = null;
        try {
            while ((ele = delayQueue.take()) != null) {
                System.out.println(ele.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
