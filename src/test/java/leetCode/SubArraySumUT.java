package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SubArraySumUT {

    @Test
    public void testCount() {
        Assert.assertEquals(2, SubArraySum.count(new int[] {1, 1, 1}, 2));
        Assert.assertEquals(55, SubArraySum.count(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
    }
}
