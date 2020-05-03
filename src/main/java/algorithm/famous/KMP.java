package algorithm.famous;

public class KMP {


    /**
     * The naive method to find a pattern within a text is to move the pattern one index at a time through the text and
     * compare characters of the pattern with the text. This results an algorithm with O(n*m) time complexity, which
     * doesn't perform well for long texts. The main reason behind this high time complexity is "backtracking" which
     * wastes time. When a text doesn't match with the pattern, in this method, we go back in the text. This
     * backtracking is the main reason for this high time complexity.
     *
     * KMP lets us to move only in forward direction within the text, examining one character at a time, and therefore
     * no backtracking and no time waste and O(n + m) time complexity. (n: text length, m: pattern length)
     * This time improvement becomes possible with cost of preprocessing the pattern, inducing O(m) space complexity
     * while in the naive method, space complexity was O(1).
     *
     * 
     *
     * @param text input text to search through
     * @param p pattern to find within the text
     * @return index of the pattern occurrence in text
     */
    public static int find(String text, String p) {
        int[] states = createStateMachine(p);
        int nextIndexToMatch = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == p.charAt(nextIndexToMatch)) {
                nextIndexToMatch++;
            } else {
                while (nextIndexToMatch > 0 && c != p.charAt(nextIndexToMatch)) {
                    nextIndexToMatch = states[nextIndexToMatch - 1];
                }
            }
            if (nextIndexToMatch == p.length()) {
                return i - p.length() + 1;
            }
        }
        return -1;
    }

//    public static int[] createStateMachine(String p) {
//        int[] states = new int[p.length()];
//        for (int i = 0; i < p.length(); i++) {
//            for (int l = 1; l <= i; l++) {
//                if (p.substring(0, i + 1).substring(l, i + 1).equals(p.substring(0, i - l + 1))) {
//                    states[i] = i - l + 1;
//                    break;
//                }
//            }
//        }
//        return states;
//    }

    public static int[] createStateMachine(String p) {
        int[] states = new int[p.length()];
        for (int i = 1; i < p.length(); i++) {
            int nextIndexToMatch = i;
            while (nextIndexToMatch > 0 && p.charAt(states[nextIndexToMatch - 1]) != p.charAt(i)) {
                nextIndexToMatch = states[nextIndexToMatch - 1];
            }
            if (nextIndexToMatch > 0) {
                states[i] = states[nextIndexToMatch - 1] + 1;
            }
        }
        return states;
    }
}
