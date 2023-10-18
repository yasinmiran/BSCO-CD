package dev.yasint.labs.threadpools;

public class Worker implements Runnable {


    private final int i;
    private long sleepTime = 0;

    public Worker(int i, long sleepTime) {
        this.i = i;
        this.sleepTime = sleepTime;
    }

    public Worker(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        if (sleepTime != 0) {
            try {
                System.out.printf("Worker %d doing some task but it takes a %d seconds.\n", i, sleepTime / 1000);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.printf("Worker %d doing some task immediately.\n", i);
        }
        System.out.printf("Worker %d is finished.\n", i);
    }

}
