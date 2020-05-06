package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import static algorithm.greedy.TaskScheduler.Task;

public class TaskSchedulerUT {

    @Test
    public void testTaskSchedule_startTogether() {
        int[] processingTime = new int[] {4, 3, 2, 5};
        int[] startTime = new int[] {0, 0, 0, 0};
        List<Task> tasks = TaskScheduler.schedule(processingTime, startTime);
        Assert.assertEquals(7.5, getAvgCompletionTime(tasks), 0.001);
    }

    @Test
    public void testTaskSchedule() {
        int[] processingTime = new int[] {4, 2, 2, 5};
        int[] startTime = new int[] {1, 4, 5, 2};
        List<Task> tasks = TaskScheduler.schedule(processingTime, startTime);
        Assert.assertEquals(8.75, getAvgCompletionTime(tasks), 0.001);
    }


    private double getAvgCompletionTime(List<TaskScheduler.Task> tasks) {
        double completionTime = 0d;
        for (Task task: tasks) {
            completionTime += task.getCompletionTime();
        }
        return completionTime / tasks.size();
    }
}
