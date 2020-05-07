package algorithm.dp;

import java.util.Arrays;

public class MatrixMultiplication {

    /**
     * For a list of matrices, find the minimum number of operations that are needed to multiple these matrices
     *
     * Given is the list of dimensions for the matrices, for n matrices, the dimension list is of size n + 1
     *
     * This is a dynamic programming approach to solve this problem. Let's say there is a list of M matrices,
     * This algorithm splits the problem in to two subproblems:
     *      first matrix * multiplication of rest of the matrices,
     *      multiplications of the first m - 1 matrices * last matrix
     * and it takes minimum operations needed to recreate the first problem according to these two subproblems.
     *
     * In order to hold the solution for the subproblem, it uses a 2d array. It fills the matrix diagonally.
     * Time complexity: O(n ^ 2), space complexity: O(n ^ 2)
     *
     * @param dimension dimension of the matrices, except for the first and last values, the rest are shared
     *                  between two matrices on left and right
     * @return minimum number of operations (sum and multiplications)
     */
    public static int getMinOperations(int[] dimension) {
        int d = dimension.length - 1;
        int[][] minCost = new int[d][d];
        for (int i = 0; i < d; i++) {
            Arrays.fill(minCost[i], 0, 0, dimension.length);
        }
        for (int l = 1; l < d; l++) {
            for (int i = 0; i + l < d; i++) {
                int c = i + l;
                minCost[i][c] = Math.min(minCost[i][c - 1] + dimension[i] * dimension[c] * dimension[c + 1],
                        minCost[i + 1][c] + dimension[i] * dimension[i + 1] * dimension[c + 1]);
            }
        }

        return minCost[0][d-1];
    }
}
