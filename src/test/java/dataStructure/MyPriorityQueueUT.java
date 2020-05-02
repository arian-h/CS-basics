package dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

public class MyPriorityQueueUT {

    private Comparator<Integer> comparator;
    private IMyPriorityQueue<Integer> queue;

    public MyPriorityQueueUT() {
    }

    @Before
    public void setup() {
        comparator = Comparator.comparingInt(o -> o);
        queue = MyPriorityQueueImpl.getInstance(comparator, MyPriorityQueueImpl.Mode.MIN);
    }

    @Test
    public void testSize() {
        queue.offer(10);
        Assert.assertEquals(queue.size(), 1);
    }

    @Test
    public void testOffer() {
        queue.offer(10);
        queue.offer(20);
        queue.offer(5);
        queue.offer(40);
        queue.offer(8);
        queue.offer(32);
        Assert.assertArrayEquals(new Integer[] {
                5, 8, 10, 40, 20, 32
        }, queue.toArray());
    }

    @Test
    public void testPoll() {
        queue.offer(10);
        queue.offer(20);
        queue.offer(5);
        queue.offer(40);
        queue.offer(8);
        queue.offer(32);
        Assert.assertEquals(Integer.valueOf(5), queue.poll());
        Assert.assertEquals(Integer.valueOf(8), queue.poll());
        Assert.assertEquals(Integer.valueOf(10), queue.poll());
        Assert.assertEquals(Integer.valueOf(20), queue.poll());
        Assert.assertEquals(Integer.valueOf(32), queue.poll());
        Assert.assertEquals(Integer.valueOf(40), queue.poll());
    }

    @Test
    public void testComponent() {
        queue.offer(10);
        queue.offer(20);
        queue.offer(5);
        queue.offer(40);
        queue.offer(8);
        queue.offer(32);
        queue.poll();
        queue.poll();
        Assert.assertEquals(4, queue.size());
    }

    @Test
    public void testPeek() {
        queue.offer(10);
        queue.offer(20);
        queue.offer(5);
        Assert.assertEquals(Integer.valueOf(5), queue.peek());
    }

    @Test(expected = Exception.class)
    public void testPeek_emptyQueue() {
        queue.peek();
    }

    @Test(expected = Exception.class)
    public void testPoll_emptyQueue() {
        queue.poll();
    }
}
