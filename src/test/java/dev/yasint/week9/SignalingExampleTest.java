package dev.yasint.week9;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class SignalingExampleTest {

    private final Semaphore semaphore = new Semaphore(0);

    public void waitingTask() {

        ConcurrentHashMap<String, String > s = new ConcurrentHashMap<>();
        try {
            System.out.println("Waiting for signal");
            System.out.println("Before the acquire  " + semaphore.availablePermits());
            semaphore.acquire();
            System.out.println("After the acquire " + semaphore.availablePermits());
            System.out.println("Received signal");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void signalingTask() {
        System.out.println("Sending signal");
        System.out.println("Before the release " + semaphore.availablePermits());
        semaphore.release();
        System.out.println("After the release " + semaphore.availablePermits());
    }

    public static void main(String[] args) {
        SignalingExampleTest example = new SignalingExampleTest();
        new Thread(example::waitingTask).start();
        new Thread(example::signalingTask).start();
    }

}
