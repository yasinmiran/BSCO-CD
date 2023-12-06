package dev.yasint.boundedbuffer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedBufferTest {

    static class Buffer {

        private final BlockingQueue<Integer> queue;

        public Buffer(int size) {
            queue = new ArrayBlockingQueue<>(size);
        }

        public void put(int value) throws InterruptedException {
            queue.put(value);
        }

        public int take() throws InterruptedException {
            return queue.take();
        }

    }

    @Test
    public void testBuffer() {

        Buffer buffer = new Buffer(10);

        // Producer
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.put(i);
                    System.out.println("Produced " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer 1
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println("Consumed " + buffer.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer 2
        new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println("Consumed " + buffer.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

    }

}
