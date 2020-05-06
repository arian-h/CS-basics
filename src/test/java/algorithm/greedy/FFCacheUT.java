package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FFCacheUT {

    @Test
    public void testEviction() {
        List<String> evictions = FFCache.getEvictions(3, new String[] {"salam", "khoobi", "aryan", "chakkeram", "khoobi", "aryan"});
        Assert.assertEquals(new ArrayList<String>() {{
            add(null);
            add(null);
            add(null);
            add("salam");
            add(null);
            add(null);
        }}, evictions);
    }

    @Test
    public void testEviction_2() {
        List<String> evictions = FFCache.getEvictions(2, new String[] {"a", "b", "b", "c", "c", "d", "a", "b", "b", "a"});
        Assert.assertEquals(new ArrayList<String>() {{
            add(null);
            add(null);
            add(null);
            add("b");
            add(null);
            add("c");
            add(null);
            add("d");
            add(null);
            add(null);
        }}, evictions);
    }

}
