package algorithm.greedy;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {

    /**
     * A greedy solution to task scheduling problem. The goal of this algorithm is to minimize average completion time.
     * Tasks take different times to complete, and they start at different points in time.
     *
     * At every second, check if there is any incoming task, if yes, put it in the pool of candidate tasks to
     * take from. Take the task that is shortest (in terms of time left to complete).
     *
     * @param processLength time it takes for tasks to finish
     * @param startTime time that each task starts
     * @return list of tasks with their completion time populated
     */
    public static List<Task> schedule(int[] processLength, int[] startTime) {
        Preconditions.checkArgument(processLength != null);
        Preconditions.checkArgument(startTime != null);
        Preconditions.checkArgument(processLength.length == startTime.length);
        int tasksCount = startTime.length;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < tasksCount; i++) {
            tasks.add(new Task(startTime[i], processLength[i]));
        }
        tasks.sort(Comparator.comparingInt(t -> t.startTime));
        PriorityQueue<Task> toProcess = new PriorityQueue<>(Comparator.comparing(Task::getRemainingTime));
        List<Task> completedTasks = new ArrayList<>();
        int time = 0;
        while (completedTasks.size() < tasksCount) {
            while (!tasks.isEmpty() && time >= tasks.get(0).startTime) {
                toProcess.offer(tasks.remove(0));
            }
            time++;
            if (!toProcess.isEmpty()) {
                Task task = toProcess.poll();
                task.remainingTime--;
                if (task.remainingTime <= 0) {
                    task.completionTime = time;
                    completedTasks.add(task);
                } else {
                    toProcess.offer(task);
                }
            }
        }
        return completedTasks;
    }

    static class Task {
        private final int startTime;
        private int remainingTime;
        private int completionTime;
        public Task(int startTime, int remainingTime) {
            this.startTime = startTime;
            this.remainingTime = remainingTime;
            this.completionTime = Integer.MAX_VALUE;
        }
        public int getRemainingTime() {
            return remainingTime;
        }
        public int getCompletionTime() {
            return completionTime;
        }
    }
}
