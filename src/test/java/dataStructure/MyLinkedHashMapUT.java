package dataStructure;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedHashMapUT {

    private IMyLinkedHashMap<Integer, String> linkedMap;

    @Before
    public void setup() {
        this.linkedMap = IMyLinkedHashMap.getInstance(3);
    }

    @Test
    public void testSize() {
        linkedMap.put(1, "Salam");
        Assert.assertEquals(1, linkedMap.size());
    }

    @Test
    public void testPut() {
        linkedMap.put(1, "Salam");
        linkedMap.put(1, "Arian");
        linkedMap.put(2, "Yasi");
        Assert.assertTrue(linkedMap.contains(1));
        Assert.assertTrue(linkedMap.contains(2));
    }

    @Test
    public void testPut_exceedSize() {
        linkedMap.put(1, "Salam");
        linkedMap.put(1, "Arian");
        linkedMap.put(2, "Yasi");
        linkedMap.put(3, "Arash");
        linkedMap.put(4, "Yalda");
        Assert.assertEquals(3, linkedMap.size());
        Assert.assertFalse(linkedMap.contains(1));
    }

    @Test
    public void testGet() {
        linkedMap.put(1, "Salam");
        Assert.assertTrue(linkedMap.contains(1));
        Assert.assertEquals("Salam", linkedMap.get(1));
    }

    @Test
    public void testRemove() {
        linkedMap.put(1, "Salam");
        linkedMap.put(2, "Yasi");
        Assert.assertEquals("Salam", linkedMap.remove(1));
        Assert.assertEquals(1, linkedMap.size());
        Assert.assertFalse(linkedMap.contains(1));
    }

    @Test
    public void testComponent() {
        linkedMap.put(1, "Salam");
        linkedMap.put(1, "Arian");
        linkedMap.put(2, "Yasi");
        linkedMap.put(3, "Mommy");
        linkedMap.put(2, "Hala");
        linkedMap.put(4, "Baba");
        Pair[] expected = new Pair[] {
                new Pair(3, "Mommy"),
                new Pair(2, "Hala"),
                new Pair(4, "Baba")
        };
        int i = 0;
        for (Pair<Integer, String> pair : linkedMap) {
            Assert.assertEquals(expected[i], pair);
            i++;
        }
    }

}
