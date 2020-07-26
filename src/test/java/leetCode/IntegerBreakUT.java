package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class IntegerBreakUT {

    @Test
    public void test() {
        Assert.assertEquals(1, IntegerBreak.integerBreak(1));
        Assert.assertEquals(36, IntegerBreak.integerBreak(10));
        Assert.assertEquals(4, IntegerBreak.integerBreak(4));
        Assert.assertEquals(2125764, IntegerBreak.integerBreak(40));

    }
}
