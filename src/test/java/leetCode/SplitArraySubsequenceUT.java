package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SplitArraySubsequenceUT {

    @Test
    public void test() {
        Assert.assertTrue(SplitArraySubsequence.isSplittable(new int[] {1, 2, 3, 4}));
        Assert.assertFalse(SplitArraySubsequence.isSplittable(new int[] {1}));
        Assert.assertTrue(SplitArraySubsequence.isSplittable(new int[] {1, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7}));
        Assert.assertTrue(SplitArraySubsequence.isSplittable(new int[] {1, 1, 2, 2, 3, 3, 4, 4}));
    }
}
