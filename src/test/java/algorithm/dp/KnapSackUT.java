package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class KnapSackUT {

    @Test
    public void testSelect() {
        Assert.assertEquals(220, KnapSack.select(50, new int[] {10, 20, 30}, new int[] {60, 100, 120}));
        Assert.assertEquals(90, KnapSack.select(10, new int[] {5, 4, 6, 3}, new int[] {10, 40, 30, 50}));
    }

}
