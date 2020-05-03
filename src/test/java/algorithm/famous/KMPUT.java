package algorithm.famous;

import org.junit.Assert;
import org.junit.Test;

public class KMPUT {

    @Test
    public void testKMP_matching() {
        Assert.assertEquals(7, KMP.find("aabbabcaabaaca", "aabaac"));
        Assert.assertEquals(4, KMP.find("aacbaacabaa", "aacabaa"));
        Assert.assertEquals(0, KMP.find("aacbaacabaa", "a"));
        Assert.assertEquals(-1, KMP.find("helloariahowareyouaria", "arian"));
        Assert.assertEquals(10, KMP.find("googoolimagooli", "gooli"));
    }

    @Test
    public void testCreateStateMachine() {
        Assert.assertArrayEquals(new int[] {0, 1, 0, 1, 2, 0}, KMP.createStateMachine("aabaac"));
        Assert.assertArrayEquals(new int[] {0, 0, 0, 0, 1, 2}, KMP.createStateMachine("abcdab"));
        Assert.assertArrayEquals(new int[] {0, 0, 0, 1, 2, 3, 4, 0}, KMP.createStateMachine("ariarian"));
    }
}
