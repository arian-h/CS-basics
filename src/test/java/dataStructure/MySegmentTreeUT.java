package dataStructure;

import org.junit.Assert;
import org.junit.Test;

import static dataStructure.IMySegmentTree.Mode;

public class MySegmentTreeUT {

    @Test
    public void testConstruct_wholeRange() {
        int[] arr = new int[] {-1, 7, 4, -5, 6, 12, 8};
        IMySegmentTree tree = IMySegmentTree.getInstance(arr, Mode.MAX);
        int max = tree.query(0, arr.length);
        Assert.assertEquals(12, max);
    }

    @Test(expected = RuntimeException.class)
    public void testConstruct_emptyArray() {
        IMySegmentTree.getInstance(new int[] {}, Mode.MAX);
    }

    @Test
    public void testConstruct_outOfRange() {
        int[] arr = new int[] {-1, 7, 4, -5, 6, 12, 8};
        IMySegmentTree tree = IMySegmentTree.getInstance(arr, Mode.MAX);
        Assert.assertEquals(Integer.MIN_VALUE, tree.query(100, 150));
    }

    @Test
    public void testConstruct_subRange() {
        int[] arr = new int[] {-1, 7, 4, -5, 6, 12, 8};
        IMySegmentTree tree = IMySegmentTree.getInstance(arr, Mode.MIN);
        Assert.assertEquals(-5, tree.query(1, 4));
    }

    @Test
    public void testConstruct_partialRange() {
        int[] arr = new int[] {-1, 7, 4, -5, 6, 12, 8};
        IMySegmentTree tree = IMySegmentTree.getInstance(arr, Mode.MIN);
        Assert.assertEquals(6, tree.query(4, 10));
    }

    @Test
    public void testConstruct_arrayPower2Length() {
        int[] arr = new int[] {-1, 7, 4, -5};
        IMySegmentTree tree = IMySegmentTree.getInstance(arr, Mode.MIN);
        Assert.assertEquals(-5, tree.query(0, 3));
    }

}
