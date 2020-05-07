package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetCoverUT {

    @Test
    public void testCover_optimalSolution() {
        Set<Integer> set1 = new HashSet<Integer>() {{
            add(1);
            add(4);
            add(3);
        }};
        Set<Integer> set2 = new HashSet<Integer>() {{
            add(2);
            add(5);
        }};
        Set<Integer> set3 = new HashSet<Integer>() {{
            add(1);
            add(4);
            add(3);
            add(2);
        }};
        List<Set<Integer>> solution = SetCover.getMinCostSubsets(Arrays.asList(set1, set2, set3),
                new double[] {5, 10, 3});
        Assert.assertEquals(2, solution.size());
        Assert.assertTrue(solution.containsAll(Arrays.asList(set3, set2)));
    }

    @Test
    public void testCover_subOptimalSolution() {
        Set<Integer> set1 = new HashSet<Integer>() {{
            add(1);
            add(2);
        }};
        Set<Integer> set2 = new HashSet<Integer>() {{
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        Set<Integer> set3 = new HashSet<Integer>() {{
            add(6);
            add(7);
            add(8);
            add(9);
            add(10);
            add(11);
            add(12);
            add(13);
        }};
        Set<Integer> set4 = new HashSet<Integer>() {{
            add(1);
            add(3);
            add(5);
            add(7);
            add(9);
            add(11);
            add(13);
        }};
        Set<Integer> set5 = new HashSet<Integer>() {{
            add(2);
            add(4);
            add(6);
            add(8);
            add(10);
            add(12);
            add(13);
        }};
        List<Set<Integer>> solution = SetCover.getMinCostSubsets(Arrays.asList(set1, set2, set3, set4, set5),
                new double[] {1, 1, 1, 1, 1});
        Assert.assertEquals(3, solution.size());
        Assert.assertTrue(solution.containsAll(Arrays.asList(set3, set3, set1)));
    }
}
