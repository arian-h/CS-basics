package leetCode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    /**
     * Find all the palindrome partitioning of a string.
     * e.g. given aab, the possible partitionings are "aa", "b" and "a", "a", "b"
     * The result is a list of lists of strings.
     *
     * Algorithm: It's a simple dfs algorithm, with a small modification. On each step if the selected substring
     * for that step is not palindrome, it doesn't continue.
     *
     * @param s
     * @return All possible palindrome partitiongs of a string
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> palindromes = new ArrayList<>();
        partition(s, new ArrayList<>(), palindromes);
        return palindromes;
    }

    public static void partition(String s, List<String> partition, List<List<String>> palindromes) {
        if (s.length() == 0) {
            palindromes.add(partition);
        } else {
            for (int i = 0; i < s.length(); i++) {
                String left = s.substring(0, i + 1);
                String right = s.substring(i + 1);
                if (isPalindrome(left)) {
                    List<String> p = new ArrayList<>(partition);
                    p.add(left);
                    partition(right, p, palindromes);
                }
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l <= r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }
}
