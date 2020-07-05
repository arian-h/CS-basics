package leetCode;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrixMultiplication {

    /**
     *  https://leetcode.com/problems/sparse-matrix-multiplication/
     *
     * Given two sparse matrices A and B, return the result of AB.
     * You may assume that A's column number is equal to B's row number.
     *
     * The idea is same as multiplying two matrices, but with a check to avoid looping through the elements
     * of the second matrix, if the element on the first matrix is 0.
     *
     * The time complexity for this algorithm is same as normal multiplication of two matrix: O(nm)
     *
     * @param A
     * @param B
     * @return
     */
    public static int[][] multiplyNormal(int[][] A, int[][] B) {
        int[][] output = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < B[0].length; k++) {
                        output[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }
        return output;
    }

    /**
     * Second way to multiply two sparse matrix. For each matrix, only keep the elements that are non-zero in a map.
     * For the left matrix, keep a list of non-zero elements, for each row.
     * For the right matrix, keep a list of non-zero elements, for each column.
     * For each element in the resulting matrix, check the list and col that contribute to that element.
     *
     * Time complexity: (nm + mq + nq), where A is n * m, B is m * q
     *
     * @param A
     * @param B
     * @return
     */
    public static int[][] multiplySparse(int[][] A, int[][] B) {
        Map<Integer, Map<Integer, Integer>> m1 = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    m1.putIfAbsent(i, new HashMap<>());
                    m1.get(i).put(j, A[i][j]);
                }
            }
        }

        Map<Integer, Map<Integer, Integer>> m2 = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                if (B[i][j] != 0) {
                    m2.putIfAbsent(j, new HashMap<>());
                    m2.get(j).put(i, B[i][j]);
                }
            }
        }

        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = 0;
                if (m1.containsKey(i)) {
                    for (int k: m1.get(i).keySet()) {
                        if (m2.containsKey(k) && m2.get(k).containsKey(j)) {
                            result[i][j] += m1.get(i).get(k) * m2.get(k).get(j);
                        }
                    }
                }
            }
        }
        return result;
    }

}
