package sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortUT {

    @Test
    public void testSort_sorted() {
        Integer[] arr = new Integer[] {1, 2, 3};
        QuickSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {1, 2, 3}, arr);
    }

    @Test
    public void testSort_empty() {
        Integer[] arr = new Integer[] {};
        QuickSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {}, arr);
    }

    @Test
    public void testSort_equalElements() {
        Integer[] arr = new Integer[] {1, 1, 1, 1};
        QuickSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1}, arr);
    }

    @Test
    public void testSort_mix_oddLength() {
        Integer[] arr = new Integer[] {1, -1, 2, 3, -4, 5, 6};
        QuickSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {-4, -1, 1, 2, 3, 5, 6}, arr);
    }

    @Test
    public void testSort_mix_evenLength() {
        Integer[] arr = new Integer[] {11, 1, -1, 2, 3, -4, 5, 6};
        QuickSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {-4, -1, 1, 2, 3, 5, 6, 11}, arr);
    }
}
