package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class PermutationSequenceUT {

    @Test
    public void testGet() {
        Assert.assertEquals(1234, PermutationSequence.get(4, 1));
        Assert.assertEquals(1432, PermutationSequence.get(4, 6));
        Assert.assertEquals(2143, PermutationSequence.get(4, 8));
        Assert.assertEquals(2314, PermutationSequence.get(4, 9));
    }
}
