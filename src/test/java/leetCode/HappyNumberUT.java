package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class HappyNumberUT {

    @Test
    public void testIsHappy() {
        Assert.assertTrue(HappyNumber.isHappy(1));
        Assert.assertFalse(HappyNumber.isHappy(2));
        Assert.assertFalse(HappyNumber.isHappy(3));
        Assert.assertFalse(HappyNumber.isHappy(4));
        Assert.assertTrue(HappyNumber.isHappy(7));
        Assert.assertTrue(HappyNumber.isHappy(100));
        Assert.assertTrue(HappyNumber.isHappy(19));
    }
}
