package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class MaxCardPointsUT {

    @Test
    public void test() {
        Assert.assertEquals(4, MaxCardPoints.maxScore(new int[] {2, 2, 2}, 2));
        Assert.assertEquals(55, MaxCardPoints.maxScore(new int[] {9, 7, 7, 9, 7, 7, 9}, 7));
        Assert.assertEquals(536, MaxCardPoints.maxScore(new int[] {96, 90, 41, 82, 39, 74, 64, 50, 30}, 8));
    }
}
