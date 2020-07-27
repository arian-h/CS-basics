package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class MaxDistanceToClosestPersonUT {

    @Test
    public void test() {
        Assert.assertEquals(4, MaxDistanceToClosestPerson.maxDistToClosest(new int[] {0,0,0,0,1,0,0}));
        Assert.assertEquals(2, MaxDistanceToClosestPerson.maxDistToClosest(new int[] {1,0,0,0,1,0,1}));
    }
}
