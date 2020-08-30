package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class CountSubMatricesSumUT {

    @Test
    public void test() {
        Assert.assertEquals(4, CountSubMatricesSum.numSubMatrixSumTarget(new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
        Assert.assertEquals(5, CountSubMatricesSum.numSubMatrixSumTarget(new int[][] {{1, -1}, {-1, 1}}, 0));
    }
}
