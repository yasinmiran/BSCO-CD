package dev.yasint;

public class MessagePrinter implements Runnable {

    private final String message;
    private final int n;

    public MessagePrinter(String message, int n) {
        this.message = message;
        this.n = n;
    }

    public void run() {
        int k = 0;
        while (k < n) {
            System.out.println(message);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            k++;
        }
    }

}
