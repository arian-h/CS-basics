package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static algorithm.greedy.ActivitySelection.Activity;

public class ActivitySelectionUT {

    @Test
    public void testActivitySelection() {
        Activity<String> breakfast = Activity.getInstance("breakfast", 0, 3);
        Activity<String> teethBrushing = Activity.getInstance("brushing teeth", 4, 5);
        Activity<String> meditation = Activity.getInstance("meditation", 6, 8);
        Activity<String> music = Activity.getInstance("music", 7, 12);
        Activity<String> workWarmUp = Activity.getInstance("code warm up", 9, 10);
        Activity<String> meeting = Activity.getInstance("meeting", 10, 13);
        Activity<String> lunch = Activity.getInstance("lunch", 14, 15);
        List<Activity<String>> selectedActivities =
                ActivitySelection.select(Arrays.asList(lunch, breakfast, music, workWarmUp, teethBrushing, meeting, meditation));
        Assert.assertEquals(Arrays.asList(breakfast, teethBrushing, meditation, workWarmUp, lunch), selectedActivities);
    }

    @Test
    public void testActivitySelection_encompassedActivities() {
        Activity<String> breakfast = Activity.getInstance("breakfast", 2, 11);
        Activity<String> music = Activity.getInstance("music", 1, 15);
        List<Activity<String>> selectedActivities =
                ActivitySelection.select(Arrays.asList(music, breakfast));
        Assert.assertEquals(Collections.singletonList(breakfast), selectedActivities);
    }

    @Test
    public void testActivitySelection_emptyList() {
        Assert.assertEquals(0, ActivitySelection.select(Collections.emptyList()).size());
    }
}
