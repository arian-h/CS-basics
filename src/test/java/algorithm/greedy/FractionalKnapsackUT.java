package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class FractionalKnapsackUT {

    @Test
    public void testChoose() {
        double values = FractionalKnapsack.choose(10, new HashMap<Double, Double>() {{
            put(7d, 11d);
            put(2d, 5d);
            put(3d, 8d);
            put(10d, 3d);
        }});
        Assert.assertEquals(79, values, 0.001);
    }

    @Test
    public void testChoose_emptySack() {
        double values = FractionalKnapsack.choose(10, new HashMap<>());
        Assert.assertEquals(0, values, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChoose_negativeWeight() {
        FractionalKnapsack.choose(-10, new HashMap<>());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testChoose_nullValues() {
        FractionalKnapsack.choose(10, null);
    }
}
