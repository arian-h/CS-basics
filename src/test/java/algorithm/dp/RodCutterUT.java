package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class RodCutterUT {

    @Test
    public void testCut() {
        Assert.assertEquals(22, RodCutter.maxGain(new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20}));
    }

    @Test
    public void testCut_2() {
        Assert.assertEquals(24, RodCutter.maxGain(new int[] {0, 3, 5, 8, 9, 10, 17, 17, 20}));
    }

    @Test
    public void testCut_3() {
        Assert.assertEquals(1, RodCutter.maxGain(new int[] {0, 1}));
    }
}
