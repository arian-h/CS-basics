package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class GasStationUT {

    @Test
    public void testGetStation() {
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,1,2};
        Assert.assertEquals(3, GasStation.getStation(gas, cost));
    }
}
