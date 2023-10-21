package dev.yasint.labs.forkjoin;

import java.util.concurrent.TimeUnit;

public class Task implements Computable {

    private final int id;
    // All the other required fields...

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void process() {

        try {
            System.out.printf("processing task %d...%n", id);
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(e.getMessage());
        }

    }

}
