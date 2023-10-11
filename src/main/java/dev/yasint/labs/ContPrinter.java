package dev.yasint.labs;

public class ContPrinter extends Thread {

    private String message;
    private static long EVERY_MS = 100;
    private boolean run = true;

    public ContPrinter(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        while (run) {
            System.out.println(message);
            try {
                Thread.sleep(EVERY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        }

    }

    public void setRun(boolean bool) {
        this.run = bool;
    }

}
