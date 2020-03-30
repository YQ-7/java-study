package com.study.concurrent.basic.createthread;

/**
 * 实现Runnable接口创建线程
 */
public class RunnableTask implements Runnable{

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
        RunnableTask task = new RunnableTask();
        task.setTaskName("java");
        new Thread(task).start();

        String taskName = "java2";
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(
                        String.format(
                                "I am a child thread, taskName: %s",
                                taskName));
            }
        }).start();
    }
}
