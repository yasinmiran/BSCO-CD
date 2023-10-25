package dev.yasint.labs.atomic;

public class NonAtomicVariable {

    private int count;

    public void increment() {
        this.count++;
    }

    public int get() {
        return this.count;
    }

}
