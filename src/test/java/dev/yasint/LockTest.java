package dev.yasint;

import dev.yasint.labs.locks.ClassLevelLocks;
import dev.yasint.labs.locks.ObjectLevelLocks;
import org.junit.jupiter.api.Test;

public class LockTest {

    @Test
    public void testObjectLevelLocksOnCriticalBlocks() throws InterruptedException {

        ObjectLevelLocks o = new ObjectLevelLocks();

        Thread t1 = new Thread(o::someOtherThing);
        Thread t2 = new Thread(o::someOtherThing);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    @Test
    public void testObjectLevelLocksOnCriticalMethods() throws InterruptedException {
        ObjectLevelLocks o = new ObjectLevelLocks();
        Thread t1 = new Thread(o::doSomething);
        Thread t2 = new Thread(o::doSomething);
        Thread t3 = new Thread(o::doSomething);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    @Test
    public void testClassLevelLocksOnCriticalMethods() throws InterruptedException {

        Thread t1 = new Thread(ClassLevelLocks::doSomething);
        Thread t2 = new Thread(ClassLevelLocks::doSomething);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    @Test
    public void testClassLevelLocksOnCriticalCodeBlocks() throws InterruptedException {

        Thread t1 = new Thread(ClassLevelLocks::doSomeOtherThing);
        Thread t2 = new Thread(ClassLevelLocks::doSomeOtherThing);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    @Test
    public void testClassLevelLocksOnCriticalCodeBlocksButOnStaticMethods() throws InterruptedException {

        Thread t1 = new Thread(ClassLevelLocks::doSomeOtherThing);
        Thread t2 = new Thread(ClassLevelLocks::doSomeOtherThing);
        Thread t3 = new Thread(ClassLevelLocks::doSomeOtherThing);
        Thread t4 = new Thread(ClassLevelLocks::doSomeOtherThing);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

    }

    @Test
    public void testClassLevelLocksOnCriticalCodeBlocksInInstanceMethods() throws InterruptedException {

        ClassLevelLocks i1 = new ClassLevelLocks();
        ClassLevelLocks i2 = new ClassLevelLocks();

        Thread t1 = new Thread(i1::doSomeOtherOtherThing);
        Thread t2 = new Thread(i2::doSomeOtherOtherThing);
        Thread t3 = new Thread(i1::doSomeOtherOtherThing);
        Thread t4 = new Thread(i2::doSomeOtherOtherThing);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        // Only one thread across all instances of ClassLevelLocks
        // can execute this block at a time.


    }

}
