package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class CountDistinctIslandsUT {

    @Test
    public void testCount() {
        Assert.assertEquals(2, CountDistinctIslands.count(new int[][] {
                new int[] {1, 1, 1, 0, 0, 0},
                new int[] {1, 0, 1, 0, 0, 0},
                new int[] {1, 0, 0, 0, 0, 1},
                new int[] {0, 0, 0, 0, 1, 1},
                new int[] {0, 0, 0, 0, 1, 0},
                new int[] {0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 1, 1, 1, 0},
                new int[] {0, 0, 1, 0, 1, 0},
                new int[] {0, 0, 1, 0, 0, 0}
        }));
        Assert.assertEquals(0, CountDistinctIslands.count(new int[][] {
                new int[] {0, 0, 0, 0, 0, 0},
                new int[] {0, 0, 0, 0, 0, 0}
        }));
        Assert.assertEquals(1, CountDistinctIslands.count(new int[][] {
                new int[] {0, 0, 1, 1, 1, 0},
                new int[] {0, 0, 1, 0, 0, 0}
        }));
    }
}
