package dev.yasint;

import dev.yasint.labs.forkjoin.Task;
import dev.yasint.labs.forkjoin.TaskProcessor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {

    @Test
    public void forkJoin() {

        final Task[] tasks = generateTasks();

        TaskProcessor taskProcessor = new TaskProcessor(tasks, 0, tasks.length);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(taskProcessor);
        pool.shutdown();

    }

    private Task[] generateTasks() {
        Task[] tasks = new Task[10];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(i + 1);
        }
        return tasks;
    }

}
