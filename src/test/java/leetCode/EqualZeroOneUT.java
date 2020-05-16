package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class EqualZeroOneUT {

    @Test
    public void testMaxLength() {
        Assert.assertEquals(0, EqualZeroOne.maxLength(new int[] { 1 }));
        Assert.assertEquals(2, EqualZeroOne.maxLength(new int[] { 1, 0, 1 }));
        Assert.assertEquals(2, EqualZeroOne.maxLength(new int[] { 1, 0, 0 }));
        Assert.assertEquals(2, EqualZeroOne.maxLength(new int[] { 1, 0, 0, 0, 1 }));
        Assert.assertEquals(6, EqualZeroOne.maxLength(new int[] { 1, 0, 0, 0, 1, 1 }));
        Assert.assertEquals(0, EqualZeroOne.maxLength(new int[] { 1, 1, 1, 1 }));
    }
}
