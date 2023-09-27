package dev.yasint;

public class CountUp extends Thread {

    private final int intervalInSeconds;
    private final int count;

    public CountUp(int count, int interval) {
        this.count = count;
        this.intervalInSeconds = interval;
    }

    @Override
    public void run() {
        int j = 0;
        while (j <= count) {
            System.out.print(j + " ");
            try {
                Thread.sleep(intervalInSeconds * 1000L);
            } catch (InterruptedException e) {
                return;
            }
            j++;
        }

    }

}
