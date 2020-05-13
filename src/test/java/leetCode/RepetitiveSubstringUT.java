package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class RepetitiveSubstringUT {

    @Test
    public void testRepetitiveSubstring() {
        Assert.assertFalse(RepetitiveSubstring.isRepetitiveSubstring("a"));
        Assert.assertFalse(RepetitiveSubstring.isRepetitiveSubstring("ab"));
        Assert.assertFalse(RepetitiveSubstring.isRepetitiveSubstring("aba"));
        Assert.assertFalse(RepetitiveSubstring.isRepetitiveSubstring("aacaa"));
        Assert.assertTrue(RepetitiveSubstring.isRepetitiveSubstring("abababab"));
    }
    
}
