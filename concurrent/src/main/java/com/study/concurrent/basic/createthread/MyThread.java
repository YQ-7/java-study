package com.study.concurrent.basic.createthread;

/**
 * 继承Thread类创建线程
 */
public class MyThread extends Thread {

    private String taskName;

    @Override
    public void run() {
        System.out.println(
                String.format(
                        "I am a child thread, taskName: %s",
                        getTaskName()));
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setTaskName("java");
        myThread.start();
    }
}
