package dev.yasint.week9;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    @Test
    public void test() throws BrokenBarrierException, InterruptedException {

        final int numberOfThreads = 3;

        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {
                // Perform some task
                System.out.println("Thread " + Thread.currentThread().getId() + " finished task.");
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        barrier.await();

    }

    // RUN THIS CODE IN A MAIN CLASS WITH A MAIN METHOD:
    // -----------------------------------------------------------------------
    //
    // CyclicBarrier barrier = new CyclicBarrier(4, () -> {
    //            System.out.println("All threads passed the barrier...");
    //            try {
    //                Thread.sleep(2000);
    //            } catch (InterruptedException e) {
    //                throw new RuntimeException(e);
    //            }
    //            System.out.println("Action is complete.");
    //        });
    //
    //        for (int i = 0; i < 3; i++) {
    //            final int __i = i;
    //            new Thread(() -> {
    //                // Perform some task
    //                try {
    //                    Thread.sleep(TimeUnit.SECONDS.toMillis(__i + 1));
    //                    System.out.println("Thread " + Thread.currentThread().getId() +
    //                            " waiting for " + (__i + 1) + " seconds and" + " finished task.");
    //                    barrier.await();
    //                    System.out.println("Thread " + Thread.currentThread().getId() + " barrier passed.");
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
    //            }).start();
    //        }


}
