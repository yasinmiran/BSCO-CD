package dev.yasint;

import java.util.Random;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        coinTossRunExample();
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
