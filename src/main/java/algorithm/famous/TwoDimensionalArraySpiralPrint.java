package algorithm.famous;

import java.util.ArrayList;
import java.util.List;

public class TwoDimensionalArraySpiralPrint {

    /**
     * Time complexity: O(n), n being the number of cells in the array
     * Space complexity: O(n), n being the number of cells in the array, which is used for response object.
     * This algorithm is recursive. It simply gets a rectangle, and prints the outer layer of it.
     * One should pay attention about the indices (not to go out of the given array, and not print twice)
     * as well as row and column special cases
     * @param matrix given 2 dimensional array
     * @param <T>
     * @return List of elements printed in spiral order
     */
    public static <T> List<T> print(T[][] matrix) {
        List<T> result = new ArrayList<>();
        print_sub(matrix, result, 0, matrix[0].length - 1, 0, matrix.length - 1);
        return result;
    }

    private static <T> void print_sub(T[][] matrix, List<T> list, int i, int j, int l, int h) {
        if (i > j || l > h) {
            return;
        }
        int x = i;
        int y = l;
        while (x <= j) {
            list.add(matrix[y][x]);
            x++;
        }
        x--;
        y++;
        // x = j
        while (y <= h) {
            list.add(matrix[y][x]);
            y++;
        }
        y--;
        x--;
        if (l < h) {
            // y = h
            while (x >= i) {
                list.add(matrix[y][x]);
                x--;
            }
            x++;
            y--;
        }
        // x = i
        if (i < j) {
            while (y > l) {
                list.add(matrix[y][x]);
                y--;
            }
            // y = l
        }
        print_sub(matrix, list, i + 1, j - 1, l + 1, h - 1);
    }
}
