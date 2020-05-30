package leetCode;

import org.junit.Assert;
import org.junit.Test;

public class MinKnightMovesUT {

    @Test
    public void testMinMoves() {
        Assert.assertEquals(94, MinKnightMoves.minMoves_AStar(-172, -110));
        Assert.assertEquals(78, MinKnightMoves.minMoves(-34, -156));
    }
}
