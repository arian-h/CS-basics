package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeUT {

    @Test
    public void testCount() {
        Assert.assertEquals(2, BinarySearchTree.getCount(2));
        Assert.assertEquals(5, BinarySearchTree.getCount(3));
    }

    @Test
    public void testMinCost() {
        Assert.assertEquals(1.5, BinarySearchTree.minCost(new double[] {0.3, 0.1, 0.6}), 0.001);
        Assert.assertEquals(0.5, BinarySearchTree.minCost(new double[] {0.3, 0.1}), 0.001);
    }
}
