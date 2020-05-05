package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CoinChangeUT {

    @Test
    public void testChange() {
        Assert.assertEquals(Arrays.asList(6, 5), CoinChange.change(11, new Integer[] {9, 6, 5, 1}));
    }

    @Test
    public void testChange_noSolution() {
        Assert.assertNull(CoinChange.change(3, new Integer[] {9, 6, 5, 4}));
    }

    @Test(expected = RuntimeException.class)
    public void testChange_badValue() {
        CoinChange.change(-1, new Integer[] {9, 6, 5, 4});
    }
}
