package leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountDistinctIslands {

    /**
     *
     * https://leetcode.com/problems/number-of-distinct-islands/
     *
     * Given a matrix of 0's and 1's, count the number of separate islands that are distinct in shape.
     * Example:
     *
     *      1110000
     *      1010000
     *      0000001
     *      0000011
     *      0000000
     *      0000111
     *      0000101
     *
     * There are three islands which two of them are distinct (top left, and bottom right)
     *
     * The algorithm starts from each element in matrix, and mark the visited elements of an island.
     * It creates a list of directions used to visit each island, and use it as a signature for that island.
     * Finally it counts the number of distinct islands.
     *
     * @return The number of distinct islands
     */

    enum DIRECTION {
        TOP, RIGHT, LEFT, DOWN;
    }

    public static int count(int[][] grid) {
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<DIRECTION> signature = new ArrayList<>();
                    visit(grid, i, j, signature);
                    islands.add(signature.toString());
                }
            }
        }
        return islands.size();
    }

    private static void visit(int[][] grid, int i, int j, List<DIRECTION> signature) {
        grid[i][j] = -1;
        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
            signature.add(DIRECTION.DOWN);
            visit(grid, i + 1, j, signature);
        }

        if (i > 0 && grid[i - 1][j] == 1) {
            signature.add(DIRECTION.TOP);
            visit(grid, i - 1, j, signature);
        }

        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
            signature.add(DIRECTION.RIGHT);
            visit(grid, i, j + 1, signature);
        }

        if (j > 0 && grid[i][j - 1] == 1) {
            signature.add(DIRECTION.LEFT);
            visit(grid, i, j - 1, signature);
        }
    }
}
