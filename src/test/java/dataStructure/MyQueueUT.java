package dataStructure;

import org.junit.Assert;
import org.junit.Test;

public class MyQueueUT {

    @Test
    public void testIsEmpty() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsFull() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        Assert.assertTrue(queue.isFull());
    }

    @Test
    public void testPrint() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        Assert.assertEquals(queue.print(), "10, 20, 30");
    }

    @Test
    public void testPoll() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        Integer r = queue.poll();
        Assert.assertEquals(queue.print(), "20, 30");
        Assert.assertEquals(r.intValue(), 10);
        Assert.assertEquals(queue.peek().intValue(), 20);
    }

    @Test
    public void testCyclicQueue() {
        MyQueue<Integer> queue = new MyQueue<>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.poll();
        queue.poll();
        queue.poll();
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        Assert.assertEquals(queue.print(), "4, 5, 6, 7, 8");
    }

    @Test(expected = Exception.class)
    public void testOffer_fullQueue() {
        MyQueue<Integer> queue = new MyQueue<>(1);
        queue.offer(1);
        queue.offer(2);
    }

    @Test(expected = Exception.class)
    public void testPoll_emptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>(1);
        queue.poll();
    }
}
