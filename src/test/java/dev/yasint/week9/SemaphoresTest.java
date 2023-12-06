package dev.yasint.week9;

import java.util.concurrent.Semaphore;

class MyStaredResource {

    private final Semaphore semaphore = new Semaphore(10);

    public void useResource() {
        try {
            semaphore.acquire();
            // Access the shared resource
            System.out.println("Resource being used by thread: " + Thread.currentThread().getName());
            // Simulate resource use with a sleep
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

}

public class SemaphoresTest {


    public static void main(String args[]) {

        MyStaredResource resource = new MyStaredResource();
        for (int i = 0; i < 10; i++) {
            new Thread(resource::useResource).start();
        }
    }

}
