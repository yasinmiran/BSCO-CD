package dev.yasint.pipelining;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataPipeliningTest {

    @Test
    public void test() throws InterruptedException {

        // Create buffers
        Buffer buffer1 = new Buffer();
        Buffer buffer2 = new Buffer();
        Buffer buffer3 = new Buffer();

        // Set up pipeline stages
        Thread stage1 = new Thread(new PipelineStage(new Buffer(), buffer1, "Stage1"));
        Thread stage2 = new Thread(new PipelineStage(buffer1, buffer2, "Stage2"));
        Thread stage3 = new Thread(new PipelineStage(buffer2, buffer3, "Stage3"));

        // Start pipeline stages
        stage1.start();
        stage2.start();
        stage3.start();

        // Input data into the pipeline
        buffer1.put("10");

        // Retrieve the processed data
        String finalData = buffer3.take();
        System.out.println("Final Output: " + finalData);

        stage1.join();
        stage2.join();
        stage3.join();

        // Stop the pipeline (in a real scenario, you'd have
        // a more graceful shutdown mechanism)
//        stage1.interrupt();
//        stage2.interrupt();
//        stage3.interrupt();

    }

}


class Buffer {

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public void put(String data) throws InterruptedException {
        queue.put(data);
    }

    public String take() throws InterruptedException {
        return queue.take();
    }

}

class PipelineStage implements Runnable {

    private Buffer inputBuffer;
    private Buffer outputBuffer;
    private String stageName;

    public PipelineStage(Buffer inputBuffer, Buffer outputBuffer, String stageName) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
        this.stageName = stageName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = inputBuffer.take();
                String processedData = process(data);
                outputBuffer.put(processedData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String process(String data) {
        // Simulate data processing by appending stageName
        return data + " processed by " + stageName + "; ";
    }
}