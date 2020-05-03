package algorithm.famous;

import org.junit.Assert;
import org.junit.Test;

public class KMPUT {

    @Test
    public void testKMP_matching() {
        Assert.assertEquals(7, KMP.find("aabbabcaabaaca", "aabaac"));
        Assert.assertEquals(4, KMP.find("aacbaacabaa", "aacabaa"));
    }

    @Test
    public void testCreateStateMachine() {
        Assert.assertArrayEquals(new int[] {0, 1, 0, 1, 2, 0}, KMP.createStateMachine("aabaac"));
        Assert.assertArrayEquals(new int[] {0, 0, 0, 0, 1, 2}, KMP.createStateMachine("abcdab"));
    }
}
