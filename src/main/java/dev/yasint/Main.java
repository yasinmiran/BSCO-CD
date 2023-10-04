package dev.yasint;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        joinExample();

//        MyTask myTask = new MyTask();
//        MyOtherTask myOtherTask = new MyOtherTask();
//
//        Thread myTask_Thread = new Thread(myTask);
//        myTask_Thread.setDaemon(false); // This will wait
//        // myTask_Thread.setDaemon(true); // This will not wait
//
////        myTask_Thread.start();
////        myOtherTask.start();
//
//        Thread messagePrinterThread = new Thread(new MessagePrinter(
//                "My Message", 10
//        ));
//
////        messagePrinterThread.start();
//
//        CountUp countUp = new CountUp(10, 1);
//        countUp.start();

    }

    public static void joinExample() {

        Thread thread1 = new Thread(new MessagePrinter(
                "My Message from Thread-1", 10
        ));
        thread1.setName("Thread-1");


        Thread thread2 = new Thread(new MessagePrinter(
                "My Message from Thread-2", 10
        ));
        thread2.setName("Thread-2");

        ThreadStateChecker state = new ThreadStateChecker(
                Arrays.asList(thread1, thread2)
        );

        state.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread1.start();
        thread2.start();


    }

    private static class ThreadStateChecker extends Thread {

        private final List<Thread> threadList;

        public ThreadStateChecker(List<Thread> threadList) {
            this.threadList = threadList;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                for (Thread t : threadList) {
                    System.out.println(t.getName() + " | "
                            + t.getState()
                            + " | isAlive? " + t.isAlive());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}