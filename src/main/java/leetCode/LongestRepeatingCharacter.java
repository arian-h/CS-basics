package leetCode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacter {

    /**
     * Given a string, and a maximum number of allowed replacement operations, what is the length of the longest
     * substring with same characters?
     *
     * Example
     *  String: AABABBA
     *  Maximum number of operations: 1
     *  Answer is 4. Replace `B` at index 2 or A at index 3, and AAAA or BBBB are the longest substrings made of
     *  a single character.
     *
     *
     * This algorithm uses the Sliding Window concept.
     * The window starts at the beginning of the string with size 1. It grows as long as the number of replacements
     * allows. If the number of operations required to turn that substring into a string only made of a single character
     * is more than allowed, it moves window by one.
     * The size of window never shrinks. As the goal is to find the longest window.
     * There is one trick. How to find if the substring, covered by window, is made of a single character if maximum
     * number of operations is applied on it? The algorithm uses a freq map, and increase and decrease frequency of
     * characters as it the window moves.
     *
     * @param s string to find the longest substring made of a single character
     * @param k maximum number of replacement operations allowed to be applied on string s
     * @return the length of the longest substring made of a single character, if maximum number of replacement
     * operations applies on it.
     */
    public static int longestSubArray(String s, int k) {
        Map<Character, Integer> charFreqInWindow = new HashMap<>();
        int windowStartIndex = 0;
        int windowEndIndex = 0;
        int maxWindowLength = 0;

        while (windowEndIndex < s.length()) {
            increaseFreq(charFreqInWindow, s.charAt(windowEndIndex));
            if (countChangesToMake(windowStartIndex, windowEndIndex, charFreqInWindow) <= k) {
                maxWindowLength = Math.max(maxWindowLength, windowLength(windowStartIndex, windowEndIndex));
            } else {
                decreaseFreq(charFreqInWindow, s.charAt(windowStartIndex));
                windowStartIndex++;
            }
            windowEndIndex++;
        }

        return maxWindowLength;
    }

    private static int countChangesToMake(int windowStartIndex, int windowEndIndex, Map<Character, Integer> charFreqInWindow) {
        return windowLength(windowStartIndex, windowEndIndex) - maxFreq(charFreqInWindow);
    }

    private static void increaseFreq(Map<Character, Integer> freqMap, char c) {
        freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }

    private static void decreaseFreq(Map<Character, Integer> freqMap, char c) {
        freqMap.put(c, freqMap.get(c) - 1);
    }

    private static int windowLength(int startIndex, int endIndex) {
        return endIndex - startIndex + 1;
    }

    private static int maxFreq(Map<Character, Integer> charFreq) {
        return charFreq.values().stream().max(Comparator.comparingInt(a -> a)).get();
    }
}
