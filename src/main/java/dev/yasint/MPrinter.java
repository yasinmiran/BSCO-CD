package dev.yasint;

public class MPrinter implements Runnable {

    String message;

    public MPrinter(String m) {
        message = m;
    }

    public void run() {
        while (true) {
            System.out.println(message);
            Thread.yield();
        }
    }

}
