package sort;

import org.junit.Assert;
import org.junit.Test;

public class HeapSortUT {

    @Test
    public void testSort() {
        int[] arr = new int[] {-5, 1, -10 , 7, 0, 8};
        HeapSort.sort(arr);
        Assert.assertArrayEquals(new int[] {-10, -5, 0, 1, 7, 8}, arr);
    }

    @Test
    public void testSort_singleElement() {
        int[] arr = new int[] {-5};
        HeapSort.sort(arr);
        Assert.assertArrayEquals(new int[] {-5}, arr);
    }

}
