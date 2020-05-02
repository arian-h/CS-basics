package dataStructure;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyBinarySearchTree<T> implements IMyBinarySearchTree<T> {

    private Node<T> root;
    private Comparator<T> comparator;

    private MyBinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public static <T> IMyBinarySearchTree<T> getInstance(Comparator<T> comparator) {
        return new MyBinarySearchTree<T>(comparator);
    }

    @Override
    public void build(T[] arr) {
        Preconditions.checkArgument(arr != null, "input array cannot be null");
        Arrays.sort(arr, comparator);
        this.root = build_rec(arr, 0, arr.length - 1);
    }

    @Override
    public void insert(T t) {
        Node<T> node = this.root;
        Node<T> parent = null;
        while (node != null) {
            parent = node;
            if (this.comparator.compare(t, node.value) <= 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        node = parent;
        Node<T> toInsert = new Node<>(t);
        if (node == null) {
            this.root = toInsert;
        } else {
            if (comparator.compare(t, node.value) <= 0) {
                node.left = toInsert;
            } else {
                node.right = toInsert;
            }
        }
    }

    @Override
    public void remove(T t) {
        if (isEmpty()) {
            throw new RuntimeException("Tree is empty");
        }
        Node<T> node = findNode(this.root, t);
        if (node != null) {
            removeNode(node);
        }
    }

    @Override
    public boolean contains(T t) {
        return findNode(this.root, t) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyBinarySearchTreeIterator();
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(this.root);
    }

    private void removeNode(Node<T> node) {
        Node<T> substitute = getPredecessor(node);
        if (substitute == null) {
            substitute = getSuccessor(node);
        }
        if (substitute == null) { // no predecessor or postprocessor
            removeFromParent(node);
        } else {
            node.value = substitute.value;
            removeNode(substitute);
        }
    }

    private void removeFromParent(Node<T> node) {
        Node<T> parent = node.parent;
        if (parent != null) {
            if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
    }

    private Node<T> findNode(Node<T> node, T t) {
        if (node == null) {
            return null;
        }
        if (this.comparator.compare(t, node.value) == 0) {
            return node;
        }
        if (this.comparator.compare(t, node.value) < 0) {
            return findNode(node.left, t);
        } else {
            return findNode(node.right, t);
        }
    }

    private int size(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + 1 + size(root.right);
    }

    private Node<T> build_rec(T[] arr, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        Node<T> root = new Node<>(arr[mid]);
        root.left = build_rec(arr, l, mid - 1);
        root.right = build_rec(arr, mid + 1, r);
        return root;
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        public Node(T value) {
            Preconditions.checkArgument(value != null, "value cannot be null");
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private Node<T> getLeftMostChild(Node<T> node) {
        Preconditions.checkArgument(node != null, "input node cannot be null");
        Node<T> parent;
        do {
            parent = node;
            node = node.left;
        } while(node != null);
        return parent;
    }

    private Node<T> getRightMostNode(Node<T> node) {
        Preconditions.checkArgument(node != null, "input node cannot be null");
        Node<T> parent;
        do {
            parent = node;
            node = node.right;
        } while(node != null);
        return parent;
    }

    private Node<T> getSuccessor(Node<T> node) {
        if (node.right != null) {
            // if node has right child, then successor is under right subtree
            return getLeftMostChild(node.right);
        } else {
            // go up the ancestor chain and find the first node is a left child, the parent would be successor
            while (node != null && !isLeftChild(node)) {
                node = node.parent;
            }
            if (node == null) {
                return null;
            }
            return node.parent;
        }
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.left != null) {
            // if node has right child, then successor is under right subtree
            return getRightMostNode(node.left);
        } else {
            // go up the ancestor chain and find the first node is a left child, the parent would be successor
            while (node != null && !isRightChild(node)) {
                node = node.parent;
            }
            if (node == null) {
                return null;
            }
            return node.parent;
        }
    }

    private boolean isLeftChild(Node<T> node) {
        if (node == null || node.parent == null) {
            return false;
        }
        return node.parent.left == node;
    }

    private boolean isRightChild(Node<T> node) {
        if (node == null || node.parent == null) {
            return false;
        }
        return node.parent.right == node;
    }

    private class MyBinarySearchTreeIterator implements Iterator<T> {
        private Node<T> node;

        public MyBinarySearchTreeIterator() {
            node = getLeftMostChild(root);
        }

        @Override
        public boolean hasNext() {
            return getSuccessor(node) != null;
        }

        @Override
        public T next() {
            Preconditions.checkArgument(node != null, "there is no more elements");
            Node<T> current = node;
            node = getSuccessor(node);
            return current.value;
        }
    }
}
