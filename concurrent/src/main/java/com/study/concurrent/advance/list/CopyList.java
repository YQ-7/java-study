package com.study.concurrent.advance.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList：写加锁(ReentranLock(JDK8)/synchronized)，读不加锁
 * 使用写时复制原则：写操作都是在底层的一个复制的数组(快照)上进行
 * 提供弱一致性
 */
public class CopyList {

    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("hello");
        arrayList.add("alibaba");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("hangzhou");

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                // 修改操作，底层会用新的数组替换旧的数组
                arrayList.set(1, "baba");
                arrayList.remove(2);
                arrayList.remove(3);
            }
        });

        Iterator<String> itr = arrayList.iterator();
        threadOne.start();
        threadOne.join();
        // 读取的任然是修改前的数据，itr保持的是旧数组的索引
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}
