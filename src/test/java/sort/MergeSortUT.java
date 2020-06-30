package sort;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortUT {

    @Test
    public void testMergeSort() {
        int[] arr = new int[] {-5, 1, -10 , 7, 0, 8};
        MergeSort.sort(arr);
        Assert.assertArrayEquals(new int[] {-10, -5, 0, 1, 7, 8}, arr);
    }

    @Test
    public void testMergeSort_singleElement() {
        int[] arr = new int[] {-5};
        MergeSort.sort(arr);
        Assert.assertArrayEquals(new int[] {-5}, arr);
    }

    @Test
    public void testMergeSort_evenElement() {
        int[] arr = new int[] {-5, -10};
        MergeSort.sort(arr);
        Assert.assertArrayEquals(new int[] {-10, -5}, arr);
    }

}