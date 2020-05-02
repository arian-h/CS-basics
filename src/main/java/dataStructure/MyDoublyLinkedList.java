package dataStructure;

import com.google.common.base.Preconditions;
import java.util.Iterator;

/**
 * Instead of using two pointers, one for prev, one for next, on each node, we could use only one pointer
 * Here is how: each node keeps the XOR of prev and next nodes in the list, when moving in one direction we know the
 * address of the prev node, so we can calculate the mem address to the next node in list
 *      void insert(T t, Direction direction) {
 *          Node n = new Node(t);
 *          int memAddr = n.memAddr();
 *          if (isEmpty()) {
 *              head = n;
 *              tail = n;
 *              n.npx =
 *          } else {
 *              if (direction == FORWARD) {
 *                  head.npx = head.npx ^ memAddr;
 *              } else {
 *                  tail.npx = tailnpx ^ memAddr;
 *              }
 *          }
 *      }
 *
 *      // starts with 0 for prevMemAddr for the head or tail
 *      Node getNext(Node n, int prevMemAddr) {
 *          return memory.get(n.npx ^ prevMemAddr);
 *      }
 *
 *      void remove(Node n) {
 *          //if it doesn't contain n don't continue
 *          int prevMemAddr = 0;
 *          int currentMemAddr = resolveMemAddr(head);
 *          while (mem.get(currentMemAddr) != n) {
 *              int t = currentMemAddr;
 *              int currentMemAddr = prevMemAddr ^ mem.get(currentMemAddr).npx;
 *              prevMemAddr = t;
 *          }
 *          //first find next node
 *          int nextMemAddr = mem.get(currentMemAddr).npx ^ prevMemAddr;
 *          Node next = mem.get(nextMemAddr);
 *          Node prev = mem.get(prevMemAddr);
 *          next.npx = next.npx ^ currentMemAddr;
 *          next.npx = next.npx ^ prevMemoryAddr;
 *          prev.npx = prev.npx ^ currentMemAddr;
 *          prev.npx = prev.npx ^ nextMemAddr;
 *      }
 *
 * @param <T>
 */
public class MyDoublyLinkedList<T> implements IMyDoublyLinkedList<T> {

    private Node head, tail;

    public static <T> IMyDoublyLinkedList<T> getInstance() {
        return new MyDoublyLinkedList<>();
    }

    @Override
    public void add(T t, Direction direction) {
        Node node = new Node(t);
        if (isEmpty()) {
            this.head = node;
            this.tail = node;
        } else {
            if (direction == Direction.FORWARD) {
                node.next = head;
                head.prev = node;
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }
    }

    @Override
    public boolean remove(T t) {
        Node node = getNode(t);
        if (node == null) {
            return false;
        }
        Node prev = node.prev;
        Node next = node.next;
        node.prev = null;
        node.next = null;
        if (prev != null) {
            prev.next = next;
        } else { // it was head that got removed
            head = next;
        }
        if (next != null) {
            next.prev = prev;
        } else { // it was tail that got removed
            tail = prev;
        }
        return true;
    }

    @Override
    public boolean contains(T t) {
        return getNode(t) != null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator(Direction direction) {
        return new MyIterator(direction);
    }

    @Override
    public Iterator<T> iterator() {
        return iterator(Direction.FORWARD);
    }

    private Node getNode(T t) {
        Node current = head;
        while (current != null && !current.value.equals(t)) {
            current = current.next;
        }
        return current;
    }

    private MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    private class MyIterator implements Iterator<T> {
        private Node current;
        private final Direction direction;

        public MyIterator(Direction direction) {
            Preconditions.checkArgument(!isEmpty(), "List is empty");
            this.direction = direction;
            if (direction == Direction.FORWARD) {
                current = head;
            } else {
                current = tail;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            Preconditions.checkArgument(current != null, "there is no more elements");
            Node node = current;
            current = getNext();
            return node.value;
        }

        private Node getNext() {
            if (direction == Direction.FORWARD) {
                return current.next;
            }
            return current.prev;
        }
    }

    private class Node {
        T value;
        Node next, prev;
        public Node(T value) {
            this.value = value;
        }
    }
}
