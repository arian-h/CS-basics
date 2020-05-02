package algorithm.famous;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class TwoDimensionalArraySpiralPrintUT {

    @Test
    public void testPrint_square() {
        Assert.assertEquals(Arrays.asList(1, 2, 4, 3), TwoDimensionalArraySpiralPrint.print(new Integer[][] {
                new Integer[] {1, 2},
                new Integer[] {3, 4}
        }));
    }

    @Test
    public void testPrint_column() {
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), TwoDimensionalArraySpiralPrint.print(new Integer[][] {
                new Integer[] {1},
                new Integer[] {2},
                new Integer[] {3},
                new Integer[] {4}
        }));
    }

    @Test
    public void testPrint_row() {
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), TwoDimensionalArraySpiralPrint.print(new Integer[][] {
                new Integer[] {1, 2, 3, 4}
        }));
    }

    @Test
    public void testPrint_rectangle() {
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 10, 9, 8, 7, 6), TwoDimensionalArraySpiralPrint.print(new Integer[][] {
                new Integer[] {1, 2, 3, 4, 5},
                new Integer[] {6, 7, 8, 9, 10}
        }));
    }

    @Test
    public void testPrint_single() {
        Assert.assertEquals(Collections.singletonList(1), TwoDimensionalArraySpiralPrint.print(new Integer[][] {
                new Integer[] {1}
        }));
    }

}
