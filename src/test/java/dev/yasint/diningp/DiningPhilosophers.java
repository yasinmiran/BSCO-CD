//package dev.yasint.diningp;
//
//import org.junit.jupiter.api.Test;
//
//public class DiningPhilosophers {
//
//
//    @Test
//    public void test() throws InterruptedException {
//
//        final Object fork1 = new Object();
//        final Object fork2 = new Object();
//        final Object fork3 = new Object();
//        final Object fork4 = new Object();
//        final Object fork5 = new Object();
//
//        dev.yasint.diningp2.Philosopher p1 = new dev.yasint.diningp2.Philosopher(fork1, fork2);
////        dev.yasint.diningp2.Philosopher p2 = new dev.yasint.diningp2.Philosopher(fork2, fork3);
//        dev.yasint.diningp2.Philosopher p3 = new dev.yasint.diningp2.Philosopher(fork3, fork4);
//        dev.yasint.diningp2.Philosopher p4 = new dev.yasint.diningp2.Philosopher(fork4, fork5);
//        dev.yasint.diningp2.Philosopher p5 = new dev.yasint.diningp2.Philosopher(fork5, fork1);
//
//        p1.start();
//        p2.start();
//        p3.start();
//        p4.start();
//        p5.start();
//
////        p1.join();
////        p2.join();
////        p3.join();
////        p4.join();
////        p5.join();
//
//    }
//
//}
//
//
//class Philosopher extends Thread {
//
//    private final Object leftFork;
//    private final Object rightFork;
//
//    Philosopher(Object leftFork, Object rightFork) {
//        this.leftFork = leftFork;
//        this.rightFork = rightFork;
//    }
//
//    void eat() {
//        try {
//            synchronized (leftFork) {
//                synchronized (rightFork) {
//                    System.out.println(Thread.currentThread().getName() + " is eating...");
//                }
//            }
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    void think() {
//        System.out.println(Thread.currentThread().getName() + " is thinking....");
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            think();
//            eat();
//        }
//    }
//}
//
//class Fork {
//
//
//}
