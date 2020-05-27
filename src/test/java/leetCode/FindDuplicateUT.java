package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class FindDuplicateUT {

    @Test
    public void testFind() {
        Assert.assertEquals(4, FindDuplicate.find(new int[] {5, 4, 4, 1, 2, 3}));
    }
}
