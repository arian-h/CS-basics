package sort;

import org.junit.Assert;
import org.junit.Test;

public class OrderStatisticUT {

    @Test
    public void testFind() {
        int[] arr = new int[] { -1, 2, -3, 4, -2, -5 };
        Assert.assertEquals(-5, OrderStatistic.find(arr, 0));
        Assert.assertEquals(-3, OrderStatistic.find(arr, 1));
        Assert.assertEquals(-2, OrderStatistic.find(arr, 2));
        Assert.assertEquals(-1, OrderStatistic.find(arr, 3));
    }

    @Test
    public void testFind_singleElement() {
        int[] arr = new int[] { -1 };
        Assert.assertEquals(-1, OrderStatistic.find(arr, 0));
    }

    @Test
    public void testFind_oddElements() {
        int[] arr = new int[] { -1, -3, 11};
        Assert.assertEquals(11, OrderStatistic.find(arr, 2));
    }

}
