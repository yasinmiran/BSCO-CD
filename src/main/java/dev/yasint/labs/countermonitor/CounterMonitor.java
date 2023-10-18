package dev.yasint.labs.countermonitor;


// Create a Java program that initializes two threads:
// one that increments a counter and another that monitors
// the counter's value. The incrementing thread should
// increase the counter by one every second and the
// monitoring thread should check the counter's value
// every three seconds, printing a message to the console
// if the counter's value is a multiple of five.
// The program should run for 30 seconds before terminating both threads gracefully.

import dev.yasint.labs.countermonitor.Count;

public class CounterMonitor implements Runnable {

    private final Count count;
    private boolean stopRunning = false;

    public CounterMonitor(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (!stopRunning) {
            System.out.println("Current count: " + count.get());
            if ((count.get() % 5) == 0 && count.get() > 0) {
                System.out.println("The counter is a multiply of 5: " + count.get());
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public void setStopRunning(boolean stopRunning) {
        this.stopRunning = stopRunning;
    }

}
