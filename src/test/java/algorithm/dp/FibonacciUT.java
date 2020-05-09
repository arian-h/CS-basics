package algorithm.dp;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciUT {

    @Test
    public void testFibonacci() {
        Assert.assertEquals(1, Fibonacci.compute(1));
        Assert.assertEquals(1, Fibonacci.compute(2));
        Assert.assertEquals(2, Fibonacci.compute(3));
        Assert.assertEquals(3, Fibonacci.compute(4));
        Assert.assertEquals(5, Fibonacci.compute(5));
        Assert.assertEquals(34, Fibonacci.compute(9));
    }

}
