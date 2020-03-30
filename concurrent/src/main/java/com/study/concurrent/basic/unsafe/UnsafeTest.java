package com.study.concurrent.basic.unsafe;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 通过Unsafe来实现CAS操作
 */
public class UnsafeTest {
    private static Unsafe unsafe;

    private static long startOffset;

    private volatile long state = 0;

    static {
        try {
            // 通过反射来获取Unsafe实例
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            // 获取"state"变量在UnsafeTest对中的偏移量
            startOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeTest test = new UnsafeTest();
        Boolean success = unsafe.compareAndSwapLong(test, startOffset, 0, 1);
        System.out.println(success);
    }
}
