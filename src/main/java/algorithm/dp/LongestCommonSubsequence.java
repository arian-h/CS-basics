package algorithm.dp;

public class LongestCommonSubsequence {

    /**
     * Finding longest common subsequence between two strings. The subsequence is not necessarily contiguous.
     *
     * This algorithm provides a dynamic programming solution to the LCS problem.
     * To find the longest common subsequence between two strings S'1, S'2:
     *      find the max between the following three:
     *          S'1[0, l1 - 1] and S'2[0, l2 - 1] (if l1-th character of S'1 is same as l2-th of S'2, plus 1)
     *          S'1[0, l1 - 1] and S'[0, l2]
     *          S'1[0, l2] and S'[0, l1 - 1]
     *
     * The algorithm uses a 2dimensional array to store the results for subproblems
     * @param s1
     * @param s2
     * @return length of the longest subsequence
     */
    public static int find(String s1, String s2) {
        int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                lcs[i][j] = Math.max(lcs[i][j], lcs[i-1][j-1]);
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    lcs[i][j] = Math.max(lcs[i][j], lcs[i - 1][j - 1] + 1);
                }
            }
        }
        return lcs[s1.length()][s2.length()];
    }

}
