package dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyHashMapUT {

    private IMyHashMap<String, Integer> map;

    @Before
    public void setup() {
        map = new MyHashMapImpl<>();
    }

    @Test
    public void testSize() {
        map.put("Salam", 10);
        map.put("Hello", 20);
        map.put("Salam", 40);
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void testSize_afterGrowth() {
        map.put("Salam", 10);
        map.put("Hello", 20);
        map.put("Arian", -12);
        map.put("Yasaman", 202);
        map.put("Pure", 300);
        Assert.assertEquals(5, map.size());
    }

    @Test
    public void testPut() {
        map.put("Salam", 10);
        map.put("Hello", 20);
        map.put("Salam", 40);
        Assert.assertTrue(map.contains("Salam"));
        Assert.assertFalse(map.contains("hello"));
        Assert.assertTrue(map.contains("Hello"));
    }

    @Test
    public void testGet() {
        map.put("Salam", 10);
        map.put("Hello", 20);
        map.put("Salam", 40);
        Assert.assertEquals(Integer.valueOf(40), map.get("Salam"));
        Assert.assertEquals(Integer.valueOf(20), map.get("Hello"));
    }

    @Test(expected = Exception.class)
    public void testGet_nonExistingKey() {
        map.get("salam");
    }

    @Test
    public void testContains() {
        Assert.assertTrue(map.isEmpty());
        map.put("Salam", 10);
        Assert.assertFalse(map.isEmpty());
    }

    @Test
    public void testRemove() {
        map.put("Salam", 10);
        map.put("Arian", 20);
        map.put("Yasaman", 30);
        map.remove("Salam");
        Assert.assertEquals(2, map.size());
        Assert.assertFalse(map.contains("Salam"));
    }

    @Test(expected = RuntimeException.class)
    public void testRemove_nonExistingKey() {
        map.remove("Salam");
    }

    @Test
    public void testComponent() {
        map.put("Salam", 10);
        map.put("Hello", 20);
        map.put("Arian", -12);
        map.put("Yasaman", 202);
        map.put("Pure", 300);
        Assert.assertEquals(5, map.size());
        Assert.assertFalse(map.isEmpty());
        Assert.assertTrue(map.contains("Arian"));
        Assert.assertEquals(Integer.valueOf(202), map.get("Yasaman"));
    }

}
