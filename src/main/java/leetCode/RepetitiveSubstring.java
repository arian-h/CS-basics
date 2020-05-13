package leetCode;

public class RepetitiveSubstring {

    /**
     * Find whether a string is made of repetitive substrings
     *
     * Let's say S is our string. As it is made of repetitive substrings, it can be represented as: A0A1A2 (AX is the repetitive
     * substring here, and they are all the same).
     * If we contact two S's, we will have: A0A1A2A0A1A2. If the first character of SS is taken out, S still
     * should be found within this string, as A1A2A3 is same as A0A1A2
     * We have to note that SS.substring(1) always contains S, no matter what S is, but if S can be found
     * in SS.substring(1) before the end of S, then it means it's made of repetitive substrings.
     *
     * Time complexity: O(n) assuming java indexOf is O(n)
     * Space complexity: O(n)
     *
     * @return whether given string is made of repetitive substrings
     */
    public static boolean isRepetitiveSubstring(String S) {
        return (S + S).substring(1).indexOf(S) + 1 < S.length();
    }
}
