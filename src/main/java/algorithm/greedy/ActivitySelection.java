package algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitySelection {

    /**
     * Sort activities based on their finish time, and select the
     * one that finishes soonest from the list of available activities. Remove activities that have
     * overlaps with this activity from the list, and move on to the next activity in the list.
     *
     * Why does this algorithm work? This is a greedy algorithm, meaning with a locally optimum solution, we hope to
     * reach a globally optimum result.
     * Let's call the first activity in the list of sorted activities A, and the first activity in globally optimum
     * list B. We know that A.finishTime <= B.finishTime, therefore we can replace B with A, without losing any other
     * activity in the list. So we won't lose any number of activities, and it may even get better! So greedy works 
     * here!
     *
     * @param activities List of activities to select from
     * @param <T>
     * @return list with the maximum number of activities
     */
    public static <T> List<Activity<T>> select(List<Activity<T>> activities) {
        if (activities == null || activities.size() == 0) {
            return Collections.emptyList();
        }
        List<Activity<T>> sortedActivities =
                activities.stream().sorted((a1, a2) -> (int) (a1.endTime - a2.endTime)).collect(Collectors.toList());
        List<Activity<T>> selectedActivities = new ArrayList<>();
        selectedActivities.add(sortedActivities.get(0));
        for (Activity<T> activity : sortedActivities) {
            if (!overlaps(selectedActivities.get(selectedActivities.size() - 1), activity)) {
                selectedActivities.add(activity);
            }
        }
        return selectedActivities;
    }

    private static <T> boolean overlaps(Activity<T> activity1, Activity<T> activity2) {
        // activity 1 encompasses activity 2
        if (activity1.startTime <= activity2.startTime && activity1.endTime >= activity2.endTime) {
            return true;
        }
        // activity 2 encompasses activity 1
        if (activity1.startTime >= activity2.startTime && activity1.endTime <= activity2.endTime) {
            return true;
        }
        // activity 2 starts during activity 1
        if (activity1.startTime <= activity2.startTime && activity2.startTime <= activity1.endTime) {
            return true;
        }
        // activity 2 ends during activity 1
        if (activity1.startTime <= activity2.endTime && activity2.endTime <= activity1.endTime) {
            return true;
        }
        return false;
    }

    static class Activity<T> {
        T activity;
        long startTime, endTime;
        private Activity(T activity, long startTime, long endTime) {
            this.activity = activity;
            this.startTime = startTime;
            this.endTime = endTime;
        }
        public static <T> Activity<T> getInstance(T activity, long startTime, long endTime) {
            return new Activity<>(activity, startTime, endTime);
        }
    }
}
