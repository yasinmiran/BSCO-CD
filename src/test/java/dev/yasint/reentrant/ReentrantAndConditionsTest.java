package dev.yasint.reentrant;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantAndConditionsTest {

    @Test
    public void test() {

        // ReentrantLock
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            // critical section
        } finally {
            lock.unlock();
        }

        // Conditions
//        ReentrantLock lock = new ReentrantLock();
//        Condition condition = lock.newCondition();
//
//        lock.lock();
//        try {
//            while (/* condition not met */) {
//                condition.await();
//            }
//            // critical section
//            condition.signalAll();
//        } finally {
//            lock.unlock();
//        }

    }

    @Test
    public void test2() throws InterruptedException {

        MyCounter myCounter = new MyCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                myCounter.inc();
            }
        });

        Thread t2 = new Thread(() -> {

        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(myCounter.get());

    }

}

class MyCounter {

    private int count = 0;
    ReentrantLock lock = new ReentrantLock();

    public void inc() {
        lock.lock();
        count++;
        lock.unlock();
    }

    public void mul() {
        lock.lock();
        if (count % 2 == 0) {
            count = count * 2;
        }
        lock.unlock();
    }

    public int get() {
        return count;
    }

}

