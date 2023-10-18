package dev.yasint;

import dev.yasint.labs.CoinToss;
import dev.yasint.labs.ContPrinter;
import dev.yasint.labs.countermonitor.Count;
import dev.yasint.labs.countermonitor.CounterIncrementor;
import dev.yasint.labs.countermonitor.CounterMonitor;

import java.util.Random;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        counterMonitorExample();
    }

    private static void counterMonitorExample() {

        Count count = new Count();

        CounterIncrementor incrementor1 = new CounterIncrementor(count);
        CounterIncrementor incrementor2 = new CounterIncrementor(count);
        CounterIncrementor incrementor3 = new CounterIncrementor(count);
        CounterMonitor monitor = new CounterMonitor(count);

        Thread incThread1 = new Thread(incrementor1);
        Thread incThread2 = new Thread(incrementor2);
        Thread incThread3 = new Thread(incrementor3);
        Thread monitorThread = new Thread(monitor);

        incThread1.start();
        incThread2.start();
        incThread3.start();
        monitorThread.start();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted by someone");
        }

        monitor.setStopRunning(true);
        incrementor1.setStopRunning(true);
        incrementor2.setStopRunning(true);
        incrementor3.setStopRunning(true);

        try {
            incThread1.join();
            incThread2.join();
            incThread3.join();
            monitorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted during joining");

        }

    }

    private static void contPrinterExample() {

        ContPrinter printer = new ContPrinter("My Message");

        Thread controlThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("5 seconds are completed");
                printer.setRun(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        printer.start();
        controlThread.start();

    }

    private static void coinTossRunExample() {
        Consumer<Integer> func = times -> {

            Random random = new Random();

            for (int i = 0; i < times; i++) {
                synchronized (CoinToss.class) {
                    if (random.nextInt(2) == 0) {
                        CoinToss.headsCount++;
                    } else {
                        CoinToss.tailsCount++;
                    }
                }
            }

        };


        Thread thread1 = new Thread(() -> {
            int times = 500;
            func.accept(times);
        });

        Thread thread2 = new Thread(() -> {
            int times = 500;
            func.accept(times);

        });

        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }

        System.out.println("Results after 1000 coin tosses.");
        System.out.println("Heads: " + CoinToss.headsCount);
        System.out.println("Tails: " + CoinToss.tailsCount);

        if (Math.abs(CoinToss.headsCount - CoinToss.tailsCount) <= CoinToss.FREQ) {
            System.out.println("Coin is fair");
        } else {
            System.out.println("Coin is biased");
        }
    }

}
