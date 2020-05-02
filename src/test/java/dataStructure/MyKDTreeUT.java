package dataStructure;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MyKDTreeUT {

    @Test
    public void testFind_existingPoint() {
        IMyKDTree tree = IMyKDTree.getInstance(new Pair[] {
                new Pair<>(1, 2),
                new Pair<>(3, 4),
                new Pair<>(1, 1),
                new Pair<>(2, 2)
        });
        Pair<Integer, Integer> closest = tree.findNearest(new Pair<>(1, 2));
        Assert.assertEquals(new Pair<>(1, 2), closest);
    }

    @Test
    public void testFind() {
        IMyKDTree tree = IMyKDTree.getInstance(new Pair[] {
                new Pair<>(1, 2),
                new Pair<>(3, 4),
                new Pair<>(1, 1),
                new Pair<>(2, 2),
                new Pair<>(4, 7)
        });
        Pair<Integer, Integer> closest = tree.findNearest(new Pair<>(6, 7));
        Assert.assertEquals(new Pair<>(4, 7), closest);
    }

    @Test(expected = RuntimeException.class)
    public void badQuery() {
        IMyKDTree tree = IMyKDTree.getInstance(new Pair[] {
                new Pair<>(1, 2)
        });
        tree.findNearest(null);
    }

    @Test(expected = RuntimeException.class)
    public void testBadConstructionInput() {
        IMyKDTree.getInstance(null);
    }


    @Test
    public void testQueryEmptyTree() {
        IMyKDTree tree = IMyKDTree.getInstance(new Pair[] {});
        Assert.assertNull(tree.findNearest(new Pair<>(6, 7)));
    }
}
