package leetCode;

import java.util.Stack;

public class ValidParenthesis {

    /**
     * Check if a given string of parenthesis is valid. The string can include (, ), *
     * '*' can be either '(', ')', or nothing
     *
     * This algorithm uses two stacks to store the indices, one for open parenthesis, and one for asterix
     * It iterates over the characters,
     *  1. '(': pushes it to the open parenthesis index stack
     *  2. '*': pushes it to the astrix index stack
     *  3. '): pops an element of  the stack. It prefers to pop from open parenthesis index stack, if it is empty
     *  it pops from astrix index stack, if that one is also empty, it means string is invalid
     *  4. At the end, it checks if the open parenthesis stack is valid considering the astrix index stack
     *
     * @return whether given string is valid
     */
    public static boolean isValid(String s) {
        Stack<Integer> astrixIndex = new Stack<>();
        Stack<Integer> openParenthesisIndex = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openParenthesisIndex.push(i);
            } else if (s.charAt(i) == '*') {
                astrixIndex.push(i);
            } else {
                if (openParenthesisIndex.isEmpty()) {
                    if (astrixIndex.isEmpty()) {
                        return false;
                    } else {
                        astrixIndex.pop();
                    }
                } else {
                    openParenthesisIndex.pop();
                }
            }
        }
        while (!openParenthesisIndex.isEmpty() && !astrixIndex.isEmpty() && astrixIndex.peek() > openParenthesisIndex.peek()) {
            openParenthesisIndex.pop();
            astrixIndex.pop();
        }
        return openParenthesisIndex.isEmpty();
    }
}
