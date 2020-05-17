package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class LongestRepeatingCharacterUT {

    @Test
    public void testLongestSubArray() {
        Assert.assertEquals(4, LongestRepeatingCharacter.longestSubArray("ABAB", 2));
        Assert.assertEquals(4, LongestRepeatingCharacter.longestSubArray("AABABBA", 1));
    }
}
