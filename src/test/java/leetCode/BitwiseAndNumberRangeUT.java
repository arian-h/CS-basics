package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class BitwiseAndNumberRangeUT {


    @Test
    public void testBitwiseAnd() {
        Assert.assertEquals(8, BitwiseAndNumberRange.bitwiseAnd(9, 12));
        Assert.assertEquals(12, BitwiseAndNumberRange.bitwiseAnd(13, 14));
        Assert.assertEquals(16, BitwiseAndNumberRange.bitwiseAnd(17, 26));
        Assert.assertEquals(0, BitwiseAndNumberRange.bitwiseAnd(0, 1));
    }
}
