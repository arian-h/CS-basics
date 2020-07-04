package leetCode;

public class BackspaceStringCompare {

    /**
     *
     * https://leetcode.com/problems/backspace-string-compare/
     * 
     * Given a couple of strings, check if they are equal. # is a special character though, it remove the character
     * before it.
     *
     * "###" -> ""
     * "#aa" -> "aa"
     * "a#bb#" -> "b"
     * "a##" -> ""
     *
     * The idea is simple. The algorithm moves over characters that are not skipped and check if they are equal, starting
     * from the end of the strings. It uses a special method to find the next character though, and that's where
     * the complexity lies.
     *
     * Time complexity: O(N), Space complexity: O(1)
     *
     * @param S
     * @param T
     * @return whether two input strings are equal
     */
    public static boolean backspaceCompare(String S, String T) {
        int sIndex = nextCharIndex(S, S.length() - 1);
        int tIndex = nextCharIndex(T, T.length() - 1);
        while (sIndex >= 0 && tIndex >= 0 && S.charAt(sIndex) == T.charAt(tIndex)) {
            sIndex = nextCharIndex(S, sIndex - 1);
            tIndex = nextCharIndex(T, tIndex - 1);
        }
        return sIndex < 0 && tIndex < 0;
    }

    /**
     * Given a string, and an index of that strong, find the next index of a character that shouldn't be skipped.
     *
     * The idea is simple, count the number of pounds, and skip equal number of characters.
     * When the skipping is done, index is either negative or not, if it is negative return it, otherwise
     * continue the process if there is still character to skip or index is referring to a pound
     *
     */
    private static int nextCharIndex(String str, int index) {
        int poundCount = 0;
        // continue as long as there are pounds
        while (index >= 0 && (str.charAt(index) == '#' || poundCount > 0)) {
            // move over #'s and count them
            while (index >= 0 && str.charAt(index) == '#') {
                poundCount++;
                index--;
            }
            // skip characters that map to the counted #'s
            while (index >= 0 && str.charAt(index) != '#' && poundCount > 0) {
                poundCount--;
                index--;
            }
        }
        return index; // index may be < 0
    }

}
