package dataStructure;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static dataStructure.IMyDoublyLinkedList.Direction.FORWARD;
import static dataStructure.IMyDoublyLinkedList.Direction.REVERSE;

public class MyDoublyLinkedListUT {

    @Test
    public void testAdd() {
        IMyDoublyLinkedList<Integer> list = IMyDoublyLinkedList.getInstance();
        list.add(10, FORWARD);
        list.add(20, REVERSE);
        list.contains(10);
        list.contains(20);
    }

    @Test
    public void testRemove() {
        IMyDoublyLinkedList<Integer> list = IMyDoublyLinkedList.getInstance();
        list.add(10, FORWARD);
        list.add(20, REVERSE);
        Assert.assertTrue(list.remove(10));
        Assert.assertFalse(list.contains(10));
        Assert.assertTrue(list.contains(20));
    }

    @Test
    public void testIsEmpty_nonEmpty() {
        IMyDoublyLinkedList<Integer> list = IMyDoublyLinkedList.getInstance();
        list.add(10, FORWARD);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testIsEmpty_emptyList() {
        Assert.assertTrue(IMyDoublyLinkedList.getInstance().isEmpty());
    }

    @Test
    public void testIterate() {
        IMyDoublyLinkedList<Integer> list = IMyDoublyLinkedList.getInstance();
        list.add(10, FORWARD);
        list.add(-1, FORWARD);
        list.add(8, FORWARD);
        list.add(9, FORWARD);
        list.add(11, FORWARD);
        list.add(20, REVERSE);
        list.add(14, REVERSE);
        list.add(-1, REVERSE);
        list.add(11, REVERSE);
        Integer[] expected = new Integer[] {11, -1, 14, 20, 10, -1, 8, 9, 11};
        int index = 0;
        Iterator<Integer> itr = list.iterator(REVERSE);
        while (itr.hasNext()) {
            Assert.assertEquals(expected[index], itr.next());
            index++;
        }
        Assert.assertEquals(expected.length, index);
    }
}
