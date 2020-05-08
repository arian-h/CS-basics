package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class MinimumDistanceUT {

    @Test
    public void testDistance_1() {
        Assert.assertEquals(3, MinimumDistance.findMin("sunday", "saturday"));
    }

    @Test
    public void testDistance_2() {
        Assert.assertEquals(1, MinimumDistance.findMin("geek", "gesek"));
    }
}
