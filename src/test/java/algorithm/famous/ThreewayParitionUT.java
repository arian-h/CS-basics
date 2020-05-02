package algorithm.famous;

import org.junit.Assert;
import org.junit.Test;

public class ThreewayParitionUT {

    @Test
    public void testParition() {
        int[] arr = new int[] {0, 1, 2, 1, 2, 0};
        ThreewayParition.threewayPartition(arr, 1);
        Assert.assertArrayEquals(new int[] {0, 0, 1, 1, 2, 2}, arr);
    }

    @Test
    public void testParition_sorted() {
        int[] arr = new int[] {0, 0, 1, 1, 2, 2, 2};
        ThreewayParition.threewayPartition(arr, 1);
        Assert.assertArrayEquals(new int[] {0, 0, 1, 1, 2, 2, 2}, arr);
    }

    @Test
    public void testParition_emptyArray() {
        int[] arr = new int[] {};
        ThreewayParition.threewayPartition(arr, 1);
        Assert.assertArrayEquals(new int[] {}, arr);
    }

    @Test
    public void testParition_singleElement() {
        int[] arr = new int[] {3};
        ThreewayParition.threewayPartition(arr, 1);
        Assert.assertArrayEquals(new int[] {3}, arr);
    }

    @Test
    public void testParition_noSmaller() {
        int[] arr = new int[] {1, 1, 2, 1, 2, 1};
        ThreewayParition.threewayPartition(arr, 1);
        Assert.assertArrayEquals(new int[] {1, 1, 1, 1, 2, 2}, arr);
    }

    @Test
    public void testParition_noLarger() {
        int[] arr = new int[] {1, 1, 2, 1, 2, 1};
        ThreewayParition.threewayPartition(arr, 3);
        Assert.assertArrayEquals(new int[] {1, 1, 2, 1, 2, 1}, arr);
    }
}
