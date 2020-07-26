package leetCode;

import java.util.List;

public class TriangleMinimumPath {
    /**
     * https://leetcode.com/problems/triangle/
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     *
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     *
     * This solution uses dynamic programming, from bottom to the top.
     * On each level, it maintains an array with the same length as the level of the triangle.
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        Integer[] sum = triangle.get(triangle.size() - 1).toArray(new Integer[0]);
        int k = triangle.size() - 1;
        while (k > 0) {
            Integer[] s = new Integer[k];
            for (int i = 0; i < s.length; i++) {
                s[i] = triangle.get(k - 1).get(i) + Math.min(sum[i], sum[i + 1]);
            }
            sum = s;
            k--;
        }
        return sum[0];
    }
}
