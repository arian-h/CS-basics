package algorithm.famous;

import org.junit.Assert;
import org.junit.Test;

import static algorithm.famous.CyclicLinkedListHead.Node;

public class CyclicLinkedListHeadUT {

    @Test
    public void testCyclicListHead() {
        Node<Integer> node1 = new Node<>();
        Node<Integer> node2 = new Node<>();
        Node<Integer> node3 = new Node<>();
        Node<Integer> node4 = new Node<>();
        Node<Integer> node5 = new Node<>();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node3;
        Assert.assertEquals(node3, CyclicLinkedListHead.findCycleHead(node1));
    }

    @Test
    public void testCyclicListHead_noCycle() {
        Node<Integer> node1 = new Node<>();
        Node<Integer> node2 = new Node<>();
        Node<Integer> node3 = new Node<>();
        Node<Integer> node4 = new Node<>();
        Node<Integer> node5 = new Node<>();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Assert.assertNull(CyclicLinkedListHead.findCycleHead(node1));
    }


    @Test
    public void testCyclicListHead_singleNode() {
        Node<Integer> node1 = new Node<>();
        node1.next = node1;
        Assert.assertEquals(node1, CyclicLinkedListHead.findCycleHead(node1));
    }
}
