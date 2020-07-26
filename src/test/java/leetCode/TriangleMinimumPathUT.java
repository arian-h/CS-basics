package leetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TriangleMinimumPathUT {

    @Test
    public void test() {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(-1),
                Arrays.asList(3, 2),
                Arrays.asList(-3, 1, -1)
        );
        Assert.assertEquals(-1, TriangleMinimumPath.minimumTotal(triangle));
    }

}
