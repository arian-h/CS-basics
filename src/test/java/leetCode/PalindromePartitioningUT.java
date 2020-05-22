package leetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PalindromePartitioningUT {

    @Test
    public void test_1() {
        List<List<String>> partitions = PalindromePartitioning.partition("aab");
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a", "a", "b"),
                Arrays.asList("aa", "b")
        );
        Assert.assertEquals(expected, partitions);
    }

    @Test
    public void test_2() {
        List<List<String>> partitions = PalindromePartitioning.partition("abc");
        List<List<String>> expected = Collections.singletonList(
                Arrays.asList("a", "b", "c")
        );
        Assert.assertEquals(expected, partitions);
    }

    @Test
    public void test_3() {
        List<List<String>> partitions = PalindromePartitioning.partition("aaa");
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a", "a", "a"),
                Arrays.asList("a", "aa"),
                Arrays.asList("aa", "a"),
                Collections.singletonList("aaa")
        );
        Assert.assertEquals(expected, partitions);
    }

}
