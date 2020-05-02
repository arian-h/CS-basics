package dataStructure;

import org.junit.Assert;
import org.junit.Test;

public class MyLinkedListUT {

    @Test
    public void testSize() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(12);
        list.add(20);
        list.add(40);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testGet() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(12);
        list.add(20);
        list.add(40);
        Assert.assertEquals(list.get(0), new Integer(12));
        Assert.assertEquals(list.get(1), new Integer(20));
        Assert.assertEquals(list.get(2), new Integer(40));
    }

    @Test
    public void testRemove() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(12);
        list.add(20);
        list.add(40);
        list.remove(2);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0), new Integer(12));
        Assert.assertEquals(list.get(1), new Integer(20));
        list.remove(0);
        Assert.assertEquals(list.get(0), new Integer(20));
    }

    @Test(expected = Exception.class)
    public void testRemove_emptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.remove(0);
    }

    @Test
    public void testContains() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(12);
        list.add(20);
        list.add(40);
        list.remove(1);
        Assert.assertFalse(list.contains(20));
    }

    @Test
    public void testPrint() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(12);
        list.add(20);
        list.add(40);
        Assert.assertEquals(list.print(), "12, 20, 40");
    }

    /**
     * Test a complicated scenario
     */
    @Test
    public void testComponent() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(12);
        list.add(20);
        list.add(40);
        list.add(50);
        list.add(60);
        list.remove(2);
        list.remove(2);
        list.remove(0);
        list.remove(1);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), new Integer(20));
    }
}
