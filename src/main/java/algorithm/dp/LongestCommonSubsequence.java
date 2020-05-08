package algorithm.dp;

public class LongestCommonSubsequence {

    public static int lcs(String s1, String s2) {
        int[][] mem = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int max = Integer.MIN_VALUE;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    max = Math.max(mem[i - 1][j - 1] + 1, max);
                }
                max = Math.max(max, mem[i - 1][j]);
                max = Math.max(max, mem[i][j - 1]);
                mem[i][j] = max;
            }
        }
        return mem[s1.length()][s2.length()];
    }

}
