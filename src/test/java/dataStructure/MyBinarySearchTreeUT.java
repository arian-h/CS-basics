package dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyBinarySearchTreeUT {

    private IMyBinarySearchTree<Integer> tree;
    private Comparator<Integer> comparator;

    public MyBinarySearchTreeUT() {
        this.comparator = Comparator.comparingInt(o -> o);
    }

    @Before
    public void setup() {
        this.tree = IMyBinarySearchTree.getInstance(this.comparator);
    }

    @Test
    public void testBuild() {
        Integer[] arr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15};
        tree.build(arr);
        Assert.assertEquals(arr.length, tree.size());
        for (int i: arr) {
            Assert.assertTrue(tree.contains(i));
        }
    }

    @Test
    public void testBuild_emptyArray() {
        Integer[] arr = new Integer[] {};
        tree.build(arr);
        Assert.assertEquals(arr.length, tree.size());
    }

    @Test
    public void testBuild_oneElementArray() {
        Integer[] arr = new Integer[] {1};
        tree.build(arr);
        Assert.assertEquals(arr.length, tree.size());
        Assert.assertTrue(tree.contains(1));
    }

    @Test
    public void testBuild_internal() {
        Integer[] arr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15};
        tree.build(arr);
        Arrays.sort(arr);
        Iterator<Integer> itr = tree.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Assert.assertEquals(arr[i], itr.next());
            i++;
        }
    }

    @Test
    public void testInsert() {
        Integer[] arr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15};
        tree.build(arr);
        tree.insert(14);
        Integer[] newArr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15, 14};
        Arrays.sort(newArr);
        Iterator<Integer> itr = tree.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Assert.assertEquals(newArr[i], itr.next());
            i++;
        }
    }

    @Test
    public void testInsert_emptyTree() {
        Integer[] arr = new Integer[] {};
        tree.build(arr);
        tree.insert(14);
        Iterator<Integer> itr = tree.iterator();
        Assert.assertEquals(Integer.valueOf(14), itr.next());
    }

    @Test
    public void testRemove() {
        Integer[] arr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15};
        tree.build(arr);
        tree.remove(15);
        Integer[] newArr = new Integer[] {7, 9, 10, 0, -1, 4, 5};
        Arrays.sort(newArr);
        Iterator<Integer> itr = tree.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Assert.assertEquals(newArr[i], itr.next());
            i++;
        }
    }

    @Test
    public void testRemove_noExistingElement() {
        Integer[] arr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15};
        tree.build(arr);
        tree.remove(16);
        Arrays.sort(arr);
        Iterator<Integer> itr = tree.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Assert.assertEquals(arr[i], itr.next());
            i++;
        }
    }

    @Test(expected = RuntimeException.class)
    public void testRemove_emptyTree() {
        Integer[] arr = new Integer[] {};
        tree.build(arr);
        tree.remove(16);
    }

    @Test
    public void testSize_empty() {
        Integer[] arr = new Integer[] {};
        tree.build(arr);
        Assert.assertEquals(0, tree.size());
    }

    @Test
    public void testClear() {
        Integer[] arr = new Integer[] {7, 9, 10, 0, -1, 4, 5, 15};
        tree.build(arr);
        tree.clear();
        Assert.assertEquals(0, tree.size());
    }
}
