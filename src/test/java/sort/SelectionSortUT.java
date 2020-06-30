package sort;

import org.junit.Assert;
import org.junit.Test;

public class SelectionSortUT {

    @Test
    public void testSort() {
        Integer[] arr = new Integer[] {10, -1, 3, 2};
        SelectionSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {-1, 2, 3, 10}, arr);
    }

    @Test
    public void testSort_nullArray() {
        SelectionSort.sort(null);
    }

    @Test
    public void testSort_emptyArray() {
        SelectionSort.sort(new Integer[] {});
    }

    @Test
    public void testSort_singleElement() {
        Integer[] arr = new Integer[] { 1 };
        SelectionSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {1}, arr);
    }

}
