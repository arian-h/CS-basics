package leetCode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseMatrixMultiplication {

    /**
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
     * For each element in the resulting matrix, check the list and col that contribute to that element,
     * and multiply them by putting two indexes on two lists.
     *
     * Time complexity: (nm + mq + nq), where A is n * m, B is m * q
     *
     * @param A
     * @param B
     * @return
     */
    public static int[][] multiplySparse(int[][] A, int[][] B) {
        Map<Integer, List<Pair<Integer, Integer>>> matrix1 = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            List<Pair<Integer, Integer>> elements = new ArrayList<>();
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    elements.add(new Pair<>(j, A[i][j]));
                }
            }
            if (elements.size() > 0) {
                matrix1.put(i, elements);
            }
        }
        Map<Integer, List<Pair<Integer, Integer>>> matrix2 = new HashMap<>();
        for (int i = 0; i < B[0].length; i++) {
            List<Pair<Integer, Integer>> elements = new ArrayList<>();
            for (int j = 0; j < B.length; j++) {
                if (B[j][i] != 0) {
                    elements.add(new Pair<>(j, B[j][i]));
                }
            }
            if (elements.size() > 0) {
                matrix2.put(i, elements);
            }
        }
        int[][] m = new int[A.length][B[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = multiply(matrix1.get(i), matrix2.get(j));
            }
        }
        return m;
    }

    private static int multiply(List<Pair<Integer, Integer>> l1, List<Pair<Integer, Integer>> l2) {
        if (l1 == null || l2 == null) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int m = 0;
        while (i < l1.size() && j < l2.size()) {
            Pair<Integer, Integer> p1 = l1.get(i);
            Pair<Integer, Integer> p2 = l2.get(j);
            if (p1.getKey().equals(p2.getKey())) {
                m += p1.getValue() * p2.getValue();
                i++;
                j++;
            } else if (p1.getKey() < p2.getKey()) {
                i++;
            } else {
                j++;
            }
        }
        return m;
    }
}
