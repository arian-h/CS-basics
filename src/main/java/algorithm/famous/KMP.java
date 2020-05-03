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
     * The state machine for the pattern tells the algorithm that in case of a mismatch between pattern and text,
     * how many characters from the beginning of the pattern can be skipped, so the algorithm doesn't need to backtrack
     * on the text.
     * This is the main reason that this algorithm works in such time.
     *
     * How the algorithm works?
     * initialize state of the state machine to 0. We need this state to move between different states.
     * Iterate over text, one character at a time, feed it to state machine and get the next state
     * Check if the given text character matches with the expected character that state is pointing to
     * If it does, then increase state by one (go to the next state in sequence) and go to the next character in text
     * If it doesn't, then find another state and compare text character with that one.
     * How to find another state?
     * The state machine contains length of the longest prefix that is also a suffix of each substring
     * (starting from the beginning of the pattern).
     * If there is a mismatch, then algorithm looks at the previous state in sequence to find how many characters
     * from the pattern it can skip, and it compare the text character with the next character after the skipped ones.
     *
     * It continues until the state gets to the final state, or the text ends.
     *
     * @param text input text to search through
     * @param p pattern to find within the text
     * @return index of the pattern occurrence in text
     */
    public static int find(String text, String p) {
        int[] states = createStateMachine(p);
        int state = 0;
        int textIndex = 0;
        while (textIndex < text.length() && state < p.length()) {
            state = nextState(states, text.charAt(textIndex), p, state);
            textIndex++;
        }
        if (state == p.length()) {
            return textIndex - p.length();
        }
        return -1;
    }

    /**
     * Given a state and the state machine, what is the next state?
     * check the given character with pattern.charAt(state).
     * If they are same, then next state in sequence is the valid valid state
     * Otherwise, look at the previous state, as it gives us the length of characters we can skip
     * before checking characters next time
     *
     * @param stateMachine
     * @param textCharacter
     * @param pattern
     * @param state given state
     * @return the next state
     */
    private static int nextState(int[] stateMachine, char textCharacter, String pattern, int state) {
        while (state > 0 && textCharacter != pattern.charAt(state)) {
            state = stateMachine[state - 1];
        }
        if (textCharacter == pattern.charAt(state)) {
            state++;
        }
        return state;
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

    /**
     * Each state is the length of the longest prefix that is also a suffix of the substring ending at the index of state
     * Except for the whole sub-pattern. For example for "aaba",
     * index 0: 0 there is no prefix for substring "a" that is also suffix of it and is shorter than "a" as well.
     * index 1: 1 the length of the longest prefix of "aa" that is also suffix of it, and is shorter than the whole string
     * index 2: 0, there is no prefix of "aab" that is also suffix of it
     * index 3: 1, the length of the longest prefix of "aaba" that is also suffix of it and is shorter than "aaba" as well
     * so the state machine would look like: "aaba"
     *
     * @param p given pattern
     * @return state machine for the pattern
     */
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
