package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class MatrixMultiplicationUT {

    @Test
    public void testGetMinOperations() {
        Assert.assertEquals(4500, MatrixMultiplication.getMinOperations(new int[] {10, 30, 5, 60}));
    }

    @Test
    public void testGetMinOperations_2() {
        Assert.assertEquals(30000, MatrixMultiplication.getMinOperations(new int[] {10, 20, 30, 40, 30}));
    }
}
