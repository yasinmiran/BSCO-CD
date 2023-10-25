package dev.yasint.labs.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class TaskProcessor extends RecursiveAction {

    private static final int THRESHOLD = 2; // This can vary
    private final Computable[] tasks;
    private final int start;
    private final int end;

    public TaskProcessor(Computable[] tasks, int start, int end) {
        this.tasks = tasks;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                tasks[i].process();
            }
        } else {
            int middle = start + (end - start) / 2;
            TaskProcessor leftProcessor = new TaskProcessor(tasks, start, middle);
            TaskProcessor rightProcessor = new TaskProcessor(tasks, middle, end);
            ForkJoinTask.invokeAll(leftProcessor, rightProcessor);
        }
    }
}

