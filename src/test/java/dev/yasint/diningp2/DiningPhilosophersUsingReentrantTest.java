package dev.yasint.diningp2;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersUsingReentrantTest {

    @Test
    public void test() throws InterruptedException {

        int numP = 5;
        Philosopher[] philosophers = new Philosopher[numP];
        ReentrantLock[] forks = new ReentrantLock[numP];
        ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < numP; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < numP; i++) {
            ReentrantLock leftFork = forks[i];
            ReentrantLock rightFork = forks[(i + 1) % numP];
            philosophers[i] = new Philosopher(i, leftFork, rightFork, lock);
        }

        Thread t1 = new Thread(philosophers[0]);
        Thread t2 = new Thread(philosophers[1]);
        Thread t3 = new Thread(philosophers[2]);
        Thread t4 = new Thread(philosophers[3]);
        Thread t5 = new Thread(philosophers[4]);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();


    }

}

class Philosopher implements Runnable {

    private int id;
    private ReentrantLock leftFork;
    private ReentrantLock rightFork;
    private Condition condition;

    private ReentrantLock lock;

    public Philosopher(int id, ReentrantLock leftFork, ReentrantLock rightFork, ReentrantLock lock) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    public void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep(((int) (Math.random() * 1000)));
    }

    public void eat() {
        lock.lock();
        try {
            while (!leftFork.tryLock() || !rightFork.tryLock()) {
                condition.await();
            }
            System.out.println("Philosopher " + id + " is eating.");
            Thread.sleep(((int) (Math.random() * 1000)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            leftFork.unlock();
            rightFork.unlock();
            condition.signalAll();
            lock.unlock();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }


}


