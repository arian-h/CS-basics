package leetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class NumberFactorsUT {

    @Test
    public void getFactorsTest() {
        Assert.assertEquals(Arrays.asList(
                Arrays.asList(2, 2, 3),
                Arrays.asList(2, 6),
                Arrays.asList(3, 4)),
                NumberFactors.getFactors(12));
        Assert.assertEquals(Collections.emptyList(), NumberFactors.getFactors(1));
        Assert.assertEquals(Collections.singletonList(Arrays.asList(2, 7)),
                NumberFactors.getFactors(14));
    }

}
