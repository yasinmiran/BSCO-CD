package dev.yasint.interthreads;

import org.junit.jupiter.api.Test;

public class InterThreadCommunicationTest {


    @Test
    public void test() throws InterruptedException {

        Counter counter = new Counter(5); // Set limit to 5

        Thread producerThread = new Thread(new Producer(counter));

        Thread consumerThread1 = new Thread(new Consumer(counter));
        Thread consumerThread2 = new Thread(new Consumer(counter));

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();

        consumerThread1.join();
        consumerThread2.join();

    }


}


class Counter {

    public int count = 0;
    private final int limit;

    public Counter(int limit) {
        this.limit = limit;
    }

    public synchronized void increment() {
        count++;
        System.out.println("Counter: " + count);
        // Notify waiting threads when the limit is reached
        if (count == limit) {
            notifyAll();
        }
    }

    public synchronized void waitForLimit() {
        while (count < limit) {
            try {
                wait(); // Wait until the counter reaches the limit
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("Limit reached: " + count);
    }

}

class Producer implements Runnable {

    private final Counter counter;

    public Producer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
            try {
                Thread.sleep(100); // Simulating work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}

class Consumer implements Runnable {

    private final Counter counter;

    public Consumer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println(counter.count);
        counter.waitForLimit();
    }

}