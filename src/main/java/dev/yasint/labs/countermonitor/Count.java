package dev.yasint.labs.countermonitor;

public class Count {

    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int get() {
        return count;
    }

}
