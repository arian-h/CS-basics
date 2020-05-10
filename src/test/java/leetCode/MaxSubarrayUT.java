package leetCode;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MaxSubarrayUT {

    @Test
    public void testFind() {
        Assert.assertEquals(new Pair<>(3, 6), MaxSubarray.findSubarray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        Assert.assertEquals(new Pair<>(3, 15), MaxSubarray.findSubarray(new int[] {7, -1, -12, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
        Assert.assertEquals(new Pair<>(0, 2), MaxSubarray.findSubarray(new int[] {7, -1, 2}));
        Assert.assertEquals(new Pair<>(0, 0), MaxSubarray.findSubarray(new int[] {7, -1, 1}));
    }

    @Test
    public void testGet() {
        Assert.assertEquals(6, MaxSubarray.getMaxSum(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        Assert.assertEquals(13, MaxSubarray.getMaxSum(new int[] {7, -1, -12, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
        Assert.assertEquals(8, MaxSubarray.getMaxSum(new int[] {7, -1, 2}));
        Assert.assertEquals(7, MaxSubarray.getMaxSum(new int[] {7, -1, 1}));
    }
}
