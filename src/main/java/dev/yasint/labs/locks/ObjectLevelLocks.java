package dev.yasint.labs.locks;

import java.util.concurrent.TimeUnit;

public class ObjectLevelLocks {

    public synchronized void doSomething() {
        // this entire method is synchronized.
        System.out.println(Thread.currentThread().getName() + " is going to access critical method");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " is going to exit the critical method");
    }

    public void someOtherThing() {
        System.out.println(Thread.currentThread().getName() + " is going to access critical section");
        synchronized (this) {
            // Only one thread per instance of `ObjectLevelLocks`
            // can execute this block at a time.
            //
            // So, we synchronize whatever inside this block.
            System.out.println(Thread.currentThread().getName()
                    + " is accessing ObjectLevelLocks.someOtherThing()'s critical section");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
