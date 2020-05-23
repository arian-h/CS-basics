package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SubArraySumMultipleUT {

    @Test
    public void testHasSubArray() {
        Assert.assertTrue(SubArraySumMultiple.hasSubArray(new int[] {1, 2, 3, 3, 11}, 7));
        Assert.assertFalse(SubArraySumMultiple.hasSubArray(new int[] {2}, 2));
        Assert.assertTrue(SubArraySumMultiple.hasSubArray(new int[] {2 , 2}, 2));
    }
}
