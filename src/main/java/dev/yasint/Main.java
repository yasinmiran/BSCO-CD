package dev.yasint;

public class Main {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        MyOtherTask myOtherTask = new MyOtherTask();

        Thread myTask_Thread = new Thread(myTask);
        myTask_Thread.setDaemon(false);

        myTask_Thread.start();
        myOtherTask.start();

    }

}