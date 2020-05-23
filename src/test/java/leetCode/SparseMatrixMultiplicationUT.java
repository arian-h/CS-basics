package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class SparseMatrixMultiplicationUT {

    @Test
    public void testMultiplyNormal() {
        int[][] A = new int[][] { new int[] {1,0,0}, new int[] {-1,0,3} };
        int[][] B = new int[][] { new int[] {7,0,0}, new int[] {0,0,0}, new int[] {0,0,1}};
        int[][] multiply = SparseMatrixMultiplication.multiplyNormal(A, B);
        int[][] expected = new int[][] {new int[] {7,0,0}, new int[] {-7,0,3}};
        for (int i = 0; i < multiply.length; i++) {
            Assert.assertArrayEquals(multiply[i], expected[i]);
        }
    }

    @Test
    public void testMultiplySparse() {
        int[][] A = new int[][] { new int[] {1,0,0}, new int[] {-1,0,3} };
        int[][] B = new int[][] { new int[] {7,0,0}, new int[] {0,0,0}, new int[] {0,0,1}};
        int[][] multiply = SparseMatrixMultiplication.multiplySparse(A, B);
        int[][] expected = new int[][] {new int[] {7,0,0}, new int[] {-7,0,3}};
        for (int i = 0; i < multiply.length; i++) {
            Assert.assertArrayEquals(multiply[i], expected[i]);
        }
    }
}
