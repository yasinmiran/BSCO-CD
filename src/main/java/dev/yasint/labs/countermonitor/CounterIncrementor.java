package dev.yasint.labs.countermonitor;

public class CounterIncrementor implements Runnable {

    private final Count count;
    private boolean stopRunning = false;

    public CounterIncrementor(Count count) {
        this.count = count;
    }


    @Override
    public void run() {
        while (!stopRunning) {
            synchronized (count) {
                count.increment();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public void setStopRunning(boolean stopRunning) {
        this.stopRunning = stopRunning;
    }


}
