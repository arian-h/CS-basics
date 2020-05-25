package leetCode;

public class LinkedListMergeSort {

    /**
     * Sort a linked list in O(nlogn) time and O(1) space complexity.
     *
     * Merge sort is a preferred way to sort a linked list. Considering that having random access in a linkedlist is
     * costly, quick sort is not preferable, and heapsort is not possible.
     *
     * Algorithm:
     * Create an empty, fixed size array of Nodes. (memory)
     * Iterate over the nodes in the linkedlist, remove each and "merge it in the memory", move to the next.
     * What does it mean to "merge into the memory"?
     * Iterate over the array and merge the node with all non empty cells on the way, make the cell empty an go to
     * next, until you get to the end, or you get to an empty cell.
     *
     * Finally merge all the cells into one and return the merged linkedlist.
     *
     * Merging two cells is straight forward.
     *
     * Why is this algorithm uses O(1) of space? obviously we used a fixed size array.
     * Why is this algorithm uses O(nlogn) of time? The array must be of size at least 2, to get that time.
     * It's basically O(n * logm(n)), m is the base of tha logarithm and is the size of the array.
     * The algorithm gets to the end of the array every logm(n) times, and does the biggest merge.
     *
     * @param head head of the linkedlist to sort
     * @param memSize size of the array to use as fixed memory
     * @return head of the sorted linkedlist
     */
    public static Node sort(Node head, int memSize) {
        Node[] mem = new Node[memSize];
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            int index = 0;
            while (index < memSize && mem[index] != null) {
                current = merge(mem[index], current);
                mem[index] = null;
                index++;
            }
            if (index == memSize) {
                index--;
            }
            mem[index] = current;
            current = next;
        }
        current = null;
        for (Node node : mem) {
            current = merge(current, node);
        }
        return current;
    }

    /**
     * Two pointer approach to merging. Create a fake head, move over two linkedlists, and attach the smaller one to
     * the previous node.
     * @param l1 sorted linkedlist to merge
     * @param l2 sorted linkedlist to merge
     * @return merged linkedlist in sorted order
     */
    private static Node merge(Node l1, Node l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        Node tmp = new Node(0);
        Node tail = tmp;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                tail = l1;
                Node next = l1.next;
                l1.next = null;
                l1 = next;
            } else {
                tail.next = l2;
                tail = l2;
                Node next = l2.next;
                l2.next = null;
                l2 = next;
            }
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return tmp.next;
    }

    static class Node {
        Node next;
        final int val;
        public Node(int val) {
            this.val = val;
        }
    }
}

