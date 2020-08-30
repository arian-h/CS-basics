package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class ThreeEqualBinariesUT {

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {0, 3}, ThreeEqualBinaries.threeEqualParts(new int[] {1,0,1,0,1}));
        Assert.assertArrayEquals(new int[] {15, 32},
                ThreeEqualBinaries.threeEqualParts(new int[] {1,1,1,0,1,0,0,1,0,1,0,0,0,1,0,0,1,1,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,1,1,1,0,1,0,0,1,0,1,0,0,0,1,0,0}));
    }
}

