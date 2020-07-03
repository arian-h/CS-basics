package algorithm.dp;

public class MinimumDistance {

    /**
     * Find the minimum edit distance between two strings, operations are: remove, insert, replace, all with the same
     * cost, what is the cost to make one string like the other one.
     *
     * This algorithm is a dynamic programming solution to the minimum edit distance problem.
     *
     * First, it only changes one string (S1), and keep the other one unmodified. This wouldn't affect the final cost.
     * It compares the last characters of the strings:
     *      cost is min of these three costs:
     *          to replace the last character: Cost(S1.substring(0, S1.length - 1), S2.substring(0, S2.length - 1)) + 1
     *              the last characters will be the same, so there is no need to compare them
     *              there is a cost of 1 to replace character
     *          to remove the last character: Cost(S1.substring(0, S1.length - 1), S2) + 1
     *              the last characters will not be the same, but S1  will be one character shorter
     *              there is a cost of 1 to remove character
     *          if it insert the last character: Cost(S1, S2.substring(0, S2.length - 1)) + 1
     *              the last characters will be same
     *                  S1 will grow one character in size, but the  last character of this string and S2 will be
     *                  same so, S1's length will be S1 + 1 - 1, and S2's length will be S2 - 1
     *              there is a cost of 1 to insert character
     *
     * This algorithm uses a 2d table to keep the values for min edit distance for substrings
     *
     * The space and time complexity are both O(N*M), N: length of S1, M: length of S2
     *
     */
    public static int findMin(String s1, String s2) {
        int removeCost = 1;
        int insertCost = 1;
        int replaceCost = 1;
        int[][] cost = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            cost[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            cost[0][j] = j;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                cost[i][j] = Math.min(cost[i - 1][j] + removeCost, cost[i][j - 1] + insertCost);
                cost[i][j] = Math.min(cost[i][j], cost[i - 1][j - 1] + replaceCost);
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cost[i][j] = Math.min(cost[i][j], cost[i - 1][j - 1]);
                }
            }
        }
        return cost[s1.length()][s2.length()];
    }
}
