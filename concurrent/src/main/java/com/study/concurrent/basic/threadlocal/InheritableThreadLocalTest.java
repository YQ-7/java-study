package com.study.concurrent.basic.threadlocal;

/**
 * 子线程不会继承父线程ThreadLocal中的变量
 * 子线程初始化时会复制父线程InheritableThreadLocal中的变量(浅复制)
 *     可变对象：父/子线程拥有相同的变量引用，对变量的操作相互影响
 *     不可变对象：父/子线程相互不影响
 */
public class InheritableThreadLocalTest {

//    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("hello work");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread: " + threadLocal.get());
            }
        });
        // Thread 只在初始化时，将父线程的inheritableThreadLocals复制到Thread.inheritableThreadLocals中
        // 不感知后续父线程的inheritableThreadLocals中的改动
        threadLocal.set("change");
        Thread.sleep(1000);
        thread.start();
        System.out.println("main: " + threadLocal.get());
    }
}
