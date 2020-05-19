package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SetMatrixToZeroUT {

    @Test
    public void testSet() {
        int[][] matrix = new int[][] {
                new int[] {0, 1, 2, 0},
                new int[] {4, 3, 0, 9},
                new int[] {7, 8, 6, 5},
                new int[] {0, 3, 5, 6}
        };
        int[][] expected = new int[][] {
                new int[] {0, 0, 0, 0},
                new int[] {0, 0, 0, 0},
                new int[] {0, 8, 0, 0},
                new int[] {0, 0, 0, 0}
        };
        SetMatrixToZero.set(matrix);
        assertMatrixEqual(expected, matrix);
    }

    public void assertMatrixEqual(int[][] expected, int[][] actual) {
        if (expected.length != actual.length || expected[0].length != actual[0].length) {
            Assert.fail();
        }
        for (int i = 0; i < expected.length; i++) {
            Assert.assertArrayEquals(expected[i], actual[i]);
        }
    }
}
