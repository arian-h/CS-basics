package leetCode;

import java.util.HashMap;
import java.util.Map;

public class CountSubMatricesSum {

    /**
     * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.
     * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
     * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
     *
     * Example 1:
     * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
     * Output: 4
     * Explanation: The four 1x1 submatrices that only contain 0.
     *
     * Example 2:
     * Input: matrix = [[1,-1],[-1,1]], target = 0
     * Output: 5
     * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
     *
     * Constraints:
     * 1 <= matrix.length <= 100
     * 1 <= matrix[0].length <= 100
     * -1000 <= matrix[i] <= 1000
     * -10^8 <= target <= 10^8
     *
     * Solution:
     * First find the SubMatrix sum for every (0, 0, i, j)
     * Then for every two rows (i, j >= i) create an array the same length as its columns
     * Fill in the array and use the regular 1D sub-array sum counting method
     *
     * @param matrix
     * @param target
     * @return int
     */
    public static int numSubMatrixSumTarget(int[][] matrix, int target) {
        int[][] preSum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    preSum[i][j] = matrix[i][j];
                } else if (i == 0) {
                    preSum[i][j] = preSum[i][j - 1] + matrix[i][j];
                } else if (j == 0) {
                    preSum[i][j] = preSum[i - 1][j] + matrix[i][j];
                } else {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i][j];
                }
            }
        }
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int[] arr = new int[matrix[0].length];
                for (int c = 0; c < matrix[0].length; c++) {
                    if (i > 0) {
                        arr[c] = preSum[j][c] - preSum[i - 1][c];
                    } else {
                        arr[c] = preSum[j][c];
                    }
                }
                Map<Integer, Integer> sum = new HashMap<>();
                sum.put(0, 1);
                for (int s : arr) {
                    count += sum.getOrDefault(s - target, 0);
                    sum.put(s, 1 + sum.getOrDefault(s, 0));
                }
            }
        }
        return count;
    }
}
