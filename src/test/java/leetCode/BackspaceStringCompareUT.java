package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class BackspaceStringCompareUT {

    @Test
    public void testCompare() {
        Assert.assertTrue(BackspaceStringCompare.backspaceCompare("a###c", "#a#c"));
        Assert.assertTrue(BackspaceStringCompare.backspaceCompare("ac", "aa#a#c"));
        Assert.assertTrue(BackspaceStringCompare.backspaceCompare("", "#a#c#"));
        Assert.assertTrue(BackspaceStringCompare.backspaceCompare("ab#c#def#", "ade"));
        Assert.assertFalse(BackspaceStringCompare.backspaceCompare("acb##ddd#", "acb#ddd#"));
    }

}
