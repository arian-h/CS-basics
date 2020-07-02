package algorithm.famous;

public class CyclicLinkedListHead {

    /**
     * This is floyd's algorithm. Consider two runners, slow and fast. fast runner is running twice the speed of
     * slow runner. If there is a cycle in the list, they will eventually meet.
     *
     * Let's say the length of the list from head to head of cycle is x, and the meeting point is y nodes from
     * the cycle head and z nodes on the other side (it's a cycle!), so the cycle is y + z nodes
     *
     * for the slow runner, it takes x + y nodes to get to the meeting point, for the fast runner it takes x + y + z + y
     * nodes:
     * 2 * (x + y) = x + y + z + y (fast runner ran twice the speed of slow runner)
     * ---> x = z
     *
     * So the number of nodes from the list head to the cycle head is equal to the number of nodes from the
     * meeting node to the cycle head.
     *
     * Therefore by moving two pointers (same speed, 1 node at a time), one from head, another from the meeting point
     * we can find the cycle's head node.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     * @param head head of the linked list
     * @param <T>
     * @return head of the cycle
     */
    public static <T> Node<T> findCycleHead(Node<T> head) {
        Node<T> slow = head;
        Node<T> fast = head;
        do {
            if (slow == null || fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        // fast == slow
        Node<T> current = head;
        while (current != slow) {
            slow = slow.next;
            current = current.next;
        }
        return current;
    }

    static class Node<T> {
        Node<T> next;
        public Node() {
            this.next = null;
        }
    }
}
