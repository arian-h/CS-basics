package leetCode;

public class LongestValidParentheses {

    /**
     *
     *
     * Given a string containing just the characters '(' and ')', find the length of the longest valid
     * (well-formed) parentheses substring.
     * Example 1:
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     * Example 2:
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     *
     * Solution: start from left: count the number of open and close parentheses, if open == close, then compare it
     * with max, and update it if necessary.
     * if right > left, then because we are moving from left, it's not recoverable, so we reset open and close
     * we do the same thing, moving from right
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            } else if (right > left) {
                right = left = 0;
            }
        }
        right = left = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            } else if (left > right) {
                right = left = 0;
            }
        }
        return max;
    }

}
