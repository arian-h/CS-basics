package dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyTrieUT {

    private IMyTrie trie;

    @Before
    public void setup() {
        this.trie = IMyTrie.getInstance();
    }

    @Test
    public void testInsert() {
        trie.insert("aryan");
        Assert.assertTrue(trie.contains("aryan"));
    }

    @Test
    public void testInsert_multipleString() {
        trie.insert("aryan");
        trie.insert("arya");
        trie.insert("ariel");
        trie.insert("aria");
        Assert.assertTrue(trie.contains("aryan"));
        Assert.assertTrue(trie.contains("arya"));
        Assert.assertTrue(trie.contains("ariel"));
        Assert.assertTrue(trie.contains("aria"));
    }

    @Test
    public void testContains() {
        trie.insert("aryan");
        Assert.assertTrue(trie.contains("aryan"));
        Assert.assertFalse(trie.contains("arya"));
        Assert.assertFalse(trie.contains(""));
    }

    @Test
    public void testRemove() {
        trie.insert("aryan");
        trie.insert("arya");
        trie.insert("ariel");
        trie.insert("aria");
        trie.remove("arya");
        Assert.assertFalse(trie.contains("arya"));
    }

    @Test
    public void testComponent() {
        trie.insert("aryan");
        trie.insert("arya");
        trie.insert("ariel");
        trie.insert("aria");
        trie.remove("arya");
        trie.remove("aryan");
        List<String> list = trie.getAll();
        Assert.assertTrue(list.contains("ariel"));
        Assert.assertTrue(list.contains("aria"));
        Assert.assertEquals(2, list.size());
    }
}
