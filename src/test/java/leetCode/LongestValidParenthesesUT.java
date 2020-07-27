package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class LongestValidParenthesesUT {

    @Test
    public void test() {
        Assert.assertEquals(0, LongestValidParentheses.longestValidParentheses(""));
        Assert.assertEquals(2, LongestValidParentheses.longestValidParentheses("(()"));
        Assert.assertEquals(2, LongestValidParentheses.longestValidParentheses("()"));
        Assert.assertEquals(4, LongestValidParentheses.longestValidParentheses(")()())"));
    }

}
