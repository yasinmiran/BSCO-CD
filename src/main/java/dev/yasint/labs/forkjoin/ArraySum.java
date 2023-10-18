package dev.yasint.labs.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ArraySum extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    private static final long THRESHOLD = 1;

    public ArraySum(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        int length = end - start;
        long sum = 0;

        if (length <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
        } else {

            int mid = start + (length / 2);
            ArraySum first = new ArraySum(numbers, start, mid);
            ArraySum second = new ArraySum(numbers, mid, end);

            System.out.printf(
                    "greater than the threshold.\ndividing numbers: first: {s:%d,e:%d}\nsecond:{s:%d,e:%d}\n",
                    start, mid, mid, end);

            first.fork();

            long secondResult = second.compute();
            long firstResult = first.join();

            sum = firstResult + secondResult;

        }

        return sum;

    }

}
