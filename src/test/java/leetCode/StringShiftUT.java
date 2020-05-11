package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class StringShiftUT {
    @Test
    public void testShift() {
        Assert.assertEquals("cab", StringShift.shift("abc", new int[][] {
                new int[] {0 , 1}, new int[] {1 , 2},
        }));
        Assert.assertEquals("kmecs", StringShift.shift("mecsk",
                new int[][] {new int[]{1,4}, new int[] {0,5}, new int[] {0,4}, new int[] {1,1} ,new int[] {1,5}}));
    }
}
