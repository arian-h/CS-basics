package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SingleNumberUT {

    @Test
    public void test() {
        Assert.assertEquals(11, SingleNumber.singleNumber(new int[] {11, 22, 22, 22, 33, 33, 33}));
    }
}
