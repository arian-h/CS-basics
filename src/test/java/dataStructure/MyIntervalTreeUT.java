package dataStructure;

import com.google.common.collect.ImmutableList;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyIntervalTreeUT {

    @Test
    public void testPointQuery_distinctIntervals() {
        IMyIntervalTree tree = IMyIntervalTree.getInstance(ImmutableList.of(
                new Pair<> (1, 2),
                new Pair<> (3, 3),
                new Pair<> (4, 6),
                new Pair<> (7, 10),
                new Pair<> (11, 16)
        ));
        List<Pair<Integer, Integer>> intersects = tree.find(3);
        Assert.assertEquals(1, intersects.size());
        Assert.assertEquals(Integer.valueOf(3), intersects.get(0).getKey());
        Assert.assertEquals(Integer.valueOf(3), intersects.get(0).getValue());
    }

    @Test
    public void testPointQuery_overlappingIntervals() {
        IMyIntervalTree tree = IMyIntervalTree.getInstance(ImmutableList.of(
                new Pair<> (7, 19),
                new Pair<> (11, 16),
                new Pair<> (14, 21),
                new Pair<> (1, 4),
                new Pair<> (3, 3),
                new Pair<> (4, 8)
        ));
        List<Pair<Integer, Integer>> intersects = tree.find(14);
        Assert.assertEquals(3, intersects.size());
        Assert.assertEquals(Integer.valueOf(7), intersects.get(0).getKey());
        Assert.assertEquals(Integer.valueOf(19), intersects.get(0).getValue());

        Assert.assertEquals(Integer.valueOf(11), intersects.get(1).getKey());
        Assert.assertEquals(Integer.valueOf(16), intersects.get(1).getValue());

        Assert.assertEquals(Integer.valueOf(14), intersects.get(2).getKey());
        Assert.assertEquals(Integer.valueOf(21), intersects.get(2).getValue());
    }

    @Test
    public void testIntervalQuery_distinctIntervals() {
        IMyIntervalTree tree = IMyIntervalTree.getInstance(ImmutableList.of(
                new Pair<>(1, 2),
                new Pair<> (3, 3),
                new Pair<> (4, 6),
                new Pair<> (7, 10),
                new Pair<> (11, 16)
        ));
        List<Pair<Integer, Integer>> intersects = tree.find(new Pair<>(1,7));
        Assert.assertEquals(4, intersects.size());
        Assert.assertTrue(intersects.containsAll(ImmutableList.of(
                new Pair<> (1, 2),
                new Pair<> (3, 3),
                new Pair<> (4, 6),
                new Pair<> (7, 10)
        )));
    }

    @Test
    public void testIntervalQuery_overlappingIntervals() {
        IMyIntervalTree tree = IMyIntervalTree.getInstance(ImmutableList.of(
                new Pair<> (7, 19),
                new Pair<> (11, 16),
                new Pair<> (14, 21),
                new Pair<> (1, 4),
                new Pair<> (3, 3),
                new Pair<> (4, 8)
        ));
        List<Pair<Integer, Integer>> intersects = tree.find(new Pair<>(8, 13));
        Assert.assertEquals(3, intersects.size());
        Assert.assertTrue(intersects.containsAll(ImmutableList.of(
                new Pair<> (7, 19),
                new Pair<> (11, 16),
                new Pair<> (4, 8)
        )));
    }

}
