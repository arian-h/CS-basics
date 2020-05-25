package leetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static leetCode.LinkedListMergeSort.Node;
public class LinkedListMergeSortUT {

    @Test
    public void testSort() {
        int size = 1000;
        int memSize = 32;
        Node head = createTestLinkedList(size);
        Node sorted = LinkedListMergeSort.sort(head, memSize);
        int i = 0;
        while (i < size && sorted != null) {
            Assert.assertEquals(i, sorted.val);
            sorted = sorted.next;
            i++;
        }
        if (i != size || sorted != null) {
            Assert.fail();
        }
    }

    private Node createTestLinkedList(int n) {
        Node head = new Node(0);
        Node prev = head;
        for (int value : createRandomArray(n)) {
            Node randomNode = new Node(value);
            prev.next = randomNode;
            prev = randomNode;
        }
        return head.next;
    }

    private int[] createRandomArray(int n) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, i + random.nextInt(arr.length - i));
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
