package dev.yasint;

import dev.yasint.labs.atomic.CommonResource;
import dev.yasint.labs.atomic.NonAtomicVariable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVarTest {

    @Test
    public void thisCodeHasAtomicityIssues() throws InterruptedException {

        NonAtomicVariable variable = new NonAtomicVariable();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + variable.get());


    }

    @Test
    public void thisCodeHasNoAtomicityIssues() throws InterruptedException {

        AtomicInteger variable = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + variable.get());

    }

    @Test
    public void atomicVarTest() {

        final CommonResource commonResource = new CommonResource();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable[] tasks = new Runnable[]{
                () -> {
                    for (int i = 0; i < 1000; i++) {
                        commonResource.count++;
                    }
                },
                () -> {
                    for (int i = 0; i < 500; i++) {
                        commonResource.count++;
                    }
                },
                () -> {
                    for (int i = 0; i < 100; i++) {
                        commonResource.count++;
                    }
                },
        };

        for (Runnable runnable : tasks) {
            executor.submit(runnable);
        }

        Thread t = new Thread(() -> {
            while (true)
                System.out.println("Count is: " + commonResource.count);
        });
        t.setDaemon(true);
        t.start();


    }

}
