package dataStructure;

import org.junit.Assert;
import org.junit.Test;

public class MyDisjointSetUT {

    @Test
    public void testUnion() {
        IMyDisjointSet<String> set = IMyDisjointSet.getInstance();
        Assert.assertTrue(set.union("sari", "farhang"));
        Assert.assertTrue(set.union("farhang", "enghelab"));
        Assert.assertFalse(set.union("enghelab", "sari"));
    }
}
