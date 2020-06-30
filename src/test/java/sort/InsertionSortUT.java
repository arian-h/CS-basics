package sort;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortUT {

    @Test
    public void testSort() {
        Integer[] arr = new Integer[] {-5, 1, -10 , 7, 0, 8};
        InsertionSort.sort(arr);
        Assert.assertArrayEquals(new Integer[] {-10, -5, 0, 1, 7, 8}, arr);
    }

}
