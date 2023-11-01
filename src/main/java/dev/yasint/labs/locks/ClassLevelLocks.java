package dev.yasint.labs.locks;

import java.util.concurrent.TimeUnit;

public class ClassLevelLocks {

    public synchronized static void doSomething() {
        System.out.println(Thread.currentThread().getName() + " is going to access critical static method");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " is going to exit the critical static method");
    }

    public static void doSomeOtherThing() {
        System.out.println(Thread.currentThread().getName() + " is going to access critical section");
        synchronized (ClassLevelLocks.class) {
            System.out.println(Thread.currentThread().getName()
                    + " is accessing ObjectLevelLocks.doSomeOtherThing()'s critical section");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void doSomeOtherOtherThing() {
        // Only one thread across all instances of ClassLevelLocks
        // can execute this block at a time.
        System.out.println(Thread.currentThread().getName() + " is going to access critical section");
        synchronized (ClassLevelLocks.class) {
            System.out.println(Thread.currentThread().getName()
                    + " is accessing ObjectLevelLocks.doSomeOtherThing()'s critical section");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
