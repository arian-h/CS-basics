package dataStructure;

import org.junit.Assert;
import org.junit.Test;

public class MyStackUT {

    @Test
    public void testInitialSize() {
        MyStack<Integer> stack = new MyStack<>();
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testSize() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(12);
        Assert.assertEquals(2, stack.size());
    }

    @Test
    public void testTop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(12);
        Assert.assertEquals(new Integer(12), stack.top());
    }

    @Test
    public void testPop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(12);
        Assert.assertEquals(new Integer(12), stack.pop());
        Assert.assertEquals(new Integer(10), stack.pop());
    }
}
