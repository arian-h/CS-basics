package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeUT {

    @Test
    public void testCount() {
        Assert.assertEquals(2, BinarySearchTree.getCount(2));
        Assert.assertEquals(5, BinarySearchTree.getCount(3));
    }

}
