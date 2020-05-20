package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SearchRotateSortedArrayUT {

    @Test
    public void testSearch() {
        Assert.assertTrue(SearchRotateSortedArray.contains(new int[] {3, 1, 1, 1, 2}, 3));
        Assert.assertTrue(SearchRotateSortedArray.contains(new int[] {3, 1}, 1));
        Assert.assertTrue(SearchRotateSortedArray.contains(new int[] {3, 1}, 3));
        Assert.assertFalse(SearchRotateSortedArray.contains(new int[] {}, 3));
        Assert.assertTrue(SearchRotateSortedArray.contains(new int[] {1, 2, 3, 4, 5}, 3));
        Assert.assertFalse(SearchRotateSortedArray.contains(new int[] {1, 2, 3, 4, 5}, 6));
    }

}
