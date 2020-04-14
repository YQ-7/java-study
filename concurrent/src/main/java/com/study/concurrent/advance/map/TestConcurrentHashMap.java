package com.study.concurrent.advance.map;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 利用ConcurrentHashMap，模拟进入直播间场景
 *  每个直播间有一个topic，
 *  进入直播间的设备，需要添加到topic关联的列表中
 */
public class TestConcurrentHashMap {

    //key: topic, value:设备列表
    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<String, List<String>>();

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("device1");
                list.add("device2");

                // put方法使用value覆盖原先的值
//                map.put("topic1", list);
                // putIfAbsent：原子性操作，key不存在，则新增，否则返回原先的value
                List<String> oldList = map.putIfAbsent("topic1", list);
                if (null != oldList) {
                    oldList.addAll(list);
                }
                System.out.println(JSON.toJSONString(map));
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("device11");
                list.add("device22");

//                map.put("topic1", list);
                List<String> oldList = map.putIfAbsent("topic1", list);
                if (null != oldList) {
                    oldList.addAll(list);
                }
                System.out.println(JSON.toJSONString(map));
            }
        });

        Thread threadThree = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("device111");
                list.add("device222");

//                map.put("topic2", list);
                List<String> oldList = map.putIfAbsent("topic2", list);
                if (null != oldList) {
                    oldList.addAll(list);
                }
                System.out.println(JSON.toJSONString(map));
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}
