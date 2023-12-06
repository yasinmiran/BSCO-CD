package dev.yasint.pipelining;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PipelineExampleTest {

    static class PipelineStage implements Runnable {

        private final BlockingQueue<Integer> inputQueue;
        private final BlockingQueue<Integer> outputQueue;
        private final int addToValue;

        public PipelineStage(BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue, int addToValue) {
            this.inputQueue = inputQueue;
            this.outputQueue = outputQueue;
            this.addToValue = addToValue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int value = inputQueue.take();
                    value += addToValue;
                    outputQueue.put(value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    @Test
    public void test() throws InterruptedException {

        BlockingQueue<Integer> inputSource = new LinkedBlockingQueue<>();

        BlockingQueue<Integer> stage1Queue = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> stage2Queue = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> stage3Queue = new LinkedBlockingQueue<>();

        Thread stage1 = new Thread(new PipelineStage(inputSource, stage1Queue, 1));
        Thread stage2 = new Thread(new PipelineStage(stage1Queue, stage2Queue, 2));
        Thread stage3 = new Thread(new PipelineStage(stage2Queue, stage3Queue, 3));

        stage1.start();
        stage2.start();
        stage3.start();

        // Feeding data to the first stage
        stage1Queue.put(1);

        // Getting the output from the last stage
        System.out.println("Final Output: " + stage3Queue.take());

        // Stopping the threads (for simplicity, interrupting them)
        stage1.interrupt();
        stage2.interrupt();
        stage3.interrupt();

        stage1.join();
        stage2.join();
        stage3.join();

    }


}
