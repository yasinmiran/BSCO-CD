package dev.yasint.futures;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class FuturesTest {

    @Test
    public void test() {

        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<String> myCallable = () -> {
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            return "Some String";
        };

        Future<String> future = executor.submit(myCallable);
        try {
            System.out.println("Getting the value...");
            String result = future.get(); // Blocks until the callable completes
            System.out.println("The result is " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }


}

