package dev.yasint.week9;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    public void test() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is processing...");
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is processing...");
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Perform another task
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is processing...");
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Perform yet another task
            latch.countDown();
        }).start();

        // Main thread will wait until all threads
        // have called countDown()
        latch.await();

        System.out.println("All tasks completed.");

    }

}
