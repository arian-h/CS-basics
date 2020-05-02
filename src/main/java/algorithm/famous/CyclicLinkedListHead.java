package algorithm.famous;

public class CyclicLinkedListHead {

    /**
     * This is floyd's algorithm. Consider two runners, slow and fast. fast runner is running twice the speed of
     * slow runner. If there is a cycle in the list, they will eventually meet.
     *
     * Let's say the length of the list from head to head of cycle is x, and the meeting point is y nodes from
     * the cycle head and z nodes on the other side (it's a cycle!)
     *
     * for the slow runner, it took x + y nodes to the meeting point, for the fast runner it took x + y + z + y
     * nodes to get to the meeting point
     * 2 * (x + y) = x + y + z + y (fast runner ran twice the speed of slow runner)
     * ---> x = z
     *
     * So the distance of the cycle head from head of the list is equal to the its distance to the meeting point
     * (not y, but z)
     *
     * therefore by moving two pointers (same speed, 1 node at a time), one from head, one from the meeting point
     * we can find the cycle's head node
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     * @param head head of the linked list
     * @param <T>
     * @return head of the cycle
     */
    public static <T> Node<T> findCycleHead(Node<T> head) {
        Node<T> meetingNode = findMeetingNode(head);
        if (meetingNode == null) {
            return null;
        }
        Node<T> t = meetingNode;
        Node<T> r = head;
        while (t != r) {
            t = t.next;
            r = r.next;
        }
        return t;
    }

    private static <T> Node<T> findMeetingNode(Node<T> head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node<T> slow = head.next;
        Node<T> fast = head.next.next;
        while (slow != null && fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow == null || fast == null || fast.next == null) {
            return null;
        }
        return slow;
    }

    static class Node<T> {
        Node<T> next;
        public Node() {
            this.next = null;
        }
    }
}
