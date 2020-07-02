package algorithm.famous;

import java.util.ArrayList;
import java.util.List;

public class TwoDimensionalArraySpiralPrint {

    /**
     * Time complexity: O(n), n being the number of cells in the array
     * Space complexity: O(n), n being the number of cells in the array, which is used for response object.
     * This algorithm is recursive. It simply gets a rectangle, and prints the outer layer of it.
     *
     * Pay attention to the indices (not to go out of the given matrix, and not to print twice)
     * as well as row and column special cases.
     *
     * It's easier to write this algorithm recursively and then convert it to iterative
     *
     * @param matrix given 2 dimensional array
     * @param <T>
     * @return List of elements printed in spiral order
     */
    public static <T> List<T> print(T[][] matrix) {
        List<T> traverse = new ArrayList<>();
        int rLow = 0;
        int rHigh = matrix.length - 1;
        int cLow = 0;
        int cHigh = matrix[0].length - 1;

        while (rLow <= rHigh && cLow <= cHigh) {

            // traverse the top row
            int c = cLow;
            while (c <= cHigh) {
                traverse.add(matrix[rLow][c]);
                c++;
            }

            // traverse the right column
            int r = rLow + 1;
            while (r <= rHigh) {
                traverse.add(matrix[r][cHigh]);
                r++;
            }

            if (rLow != rHigh) {
                // traverse the bottom row
                c = cHigh - 1;
                while (c >= cLow) {
                    traverse.add(matrix[rHigh][c]);
                    c--;
                }
            }

            if (cLow != cHigh) {
                // traverse the left column
                r = rHigh - 1;
                while (r > rLow) {
                    traverse.add(matrix[r][cLow]);
                    r--;
                }
            }

            rLow++;
            cLow++;
            cHigh--;
            rHigh--;
        }

        return traverse;
    }

}
