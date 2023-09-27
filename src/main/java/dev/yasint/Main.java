package dev.yasint;

public class Main {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        MyOtherTask myOtherTask = new MyOtherTask();

        Thread myTask_Thread = new Thread(myTask);
        myTask_Thread.setDaemon(false); // This will wait
        // myTask_Thread.setDaemon(true); // This will not wait

        myTask_Thread.start();
        myOtherTask.start();

        Thread messagePrinterThread = new Thread(new MessagePrinter(
                "My Message", 10
        ));

        messagePrinterThread.start();

    }

}