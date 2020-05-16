package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class ValidParenthesisUT {

    @Test
    public void isValid() {
        Assert.assertTrue(ValidParenthesis.isValid(""));
        Assert.assertFalse(ValidParenthesis.isValid("("));
        Assert.assertTrue(ValidParenthesis.isValid("()"));
        Assert.assertTrue(ValidParenthesis.isValid("(*))"));
        Assert.assertTrue(ValidParenthesis.isValid("**(())"));
        Assert.assertFalse(ValidParenthesis.isValid(")*"));
        Assert.assertFalse(ValidParenthesis.isValid("*("));
        Assert.assertTrue(ValidParenthesis.isValid("*"));
    }

}
