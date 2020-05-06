package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static algorithm.greedy.KMeans.Point;

public class KMeansUT {

    @Test
    public void testClusters_equalCentersAndPoint() {
        List<Point> centers = KMeans.findCenters(Arrays.asList(new Point(0, 0), new Point(10, 10)), 2);
        Assert.assertTrue(centers.containsAll(Arrays.asList(new Point(0, 0), new Point(10, 10))));
    }

    @Test
    public void testClusters_2() {
        List<Point> centers = KMeans.findCenters(Arrays.asList(new Point(-1, 0), new Point(5, 3),
                new Point(7, 3), new Point(6, 4), new Point(6, 2), new Point(1, 0)), 2);
        Assert.assertTrue(centers.containsAll(Arrays.asList(new Point(0, 0), new Point(6, 3))));
    }
}
