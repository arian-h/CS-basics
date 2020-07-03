package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubsequenceUT {

    @Test
    public void lcs() {
        Assert.assertEquals(3, LongestCommonSubsequence.find("ABCDGH", "AEDFHR"));
    }

    @Test
    public void lcs_2() {
        Assert.assertEquals(4, LongestCommonSubsequence.find("AGGTAB", "GXTXAYB"));
    }

}
