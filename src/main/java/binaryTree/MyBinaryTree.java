package binaryTree;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.IntStream;

public class MyBinaryTree<T> implements IMyBinaryTree<T> {

    IMyTreeNode<T> root;

    private MyBinaryTree() {
        this.root = null;
    }

    private MyBinaryTree(IMyTreeNode<T> t) {
        this.root = t;
    }

    public static <T> MyBinaryTree<T> getInstance() {
        return new MyBinaryTree<>();
    }

    public static <T> MyBinaryTree<T> getInstance(IMyTreeNode<T> node) {
        return new MyBinaryTree<>(node);
    }

    @Override
    public IMyTreeNode<T> getRoot() {
        return this.root;
    }

    @Override
    public void constructPreorderInorder(T[] preorder, T[] inorder) {
        constructPreCheck(inorder, preorder);
        this.root = constructInorder(preorder, inorder, Comparator.comparingInt(i -> i));
    }

    @Override
    public void constructInorderPostorder(T[] inorder, T[] postorder) {
        constructPreCheck(inorder, postorder);
        this.root = constructInorder(postorder, inorder, (i, j) -> j - i);
    }

    /*
     * This implementation takes worst case O(n^2) time and O(n) space
     */
    private IMyTreeNode<T> constructInorder(T[] preorder, T[] inorder, Comparator<Integer> comparator) {
        Map<T, Integer> index = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            index.put(preorder[i], i);
        }
        List<T> nodes = Arrays.asList(inorder);
        return constructInorder(nodes, index, comparator);
    }

    private IMyTreeNode<T> constructInorder(List<T> nodes, Map<T, Integer> index, Comparator<Integer> comparator) {
        if (nodes.size() == 0) {
            return null;
        }
        int rootIndex = findRootIndex(nodes, index, comparator);
        IMyTreeNode<T> root = new MyTreeNode<>(nodes.get(rootIndex));
        if (nodes.size() == 1) {
            return root;
        }
        root.addLeft(constructInorder(nodes.subList(0, rootIndex), index, comparator));
        root.addRight(constructInorder(nodes.subList(rootIndex + 1, nodes.size()), index, comparator));
        return root;
    }

    private int findRootIndex(List<T> nodes, Map<T, Integer> indices, Comparator<Integer> comparator) {
        int index = 0;
        for (int i = 1; i < nodes.size(); i++) {
            if (comparator.compare(indices.get(nodes.get(i)), indices.get(nodes.get(index))) < 0) {
                index = i;
            }
        }
        return index;
    }

    /**
     * The first element in the preorder list is the root. The rest of elements can be divided into left and right
     * and go to the left and right children respectively.
     * how to divide the list into two left and right sublists? look at findRightIndex
     * @param preorder preorder representation of the tree
     * @param postorder postorder representation of the tree
     */
    @Override
    public void constructPreorderPostorder(T[] preorder, T[] postorder) {
        constructPreCheck(preorder, postorder);
        Map<T, Integer> indices = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            indices.put(postorder[i], i);
        }
        List<T> preorderList = new ArrayList<>(Arrays.asList(preorder));
        this.root = constructPreorderPostorder(preorderList, indices);
    }

    private IMyTreeNode<T> constructPreorderPostorder(List<T> preorder, Map<T, Integer> indices) {
        if (preorder.isEmpty()) {
            return null;
        }
        IMyTreeNode<T> root = new MyTreeNode<>(preorder.remove(0));
        if (!preorder.isEmpty()) {
            int rightIndex = findRightIndex(preorder, indices);
            root.addLeft(constructPreorderPostorder(preorder.subList(0, rightIndex), indices));
            root.addRight(constructPreorderPostorder(preorder, indices));
        }
        return root;
    }

    /**
     * We know that the tree is full (meaning that each node has no child, or two children)
     * How do we know that? Because we cannot reconstruct a unique tree from preorder and postorder only for full
     * binary trees.
     * the first element in this list belongs to the left child.
     * We find the first element that has an index larger than the first element. This means from that point
     * the right sublist started.
     * 
     * @param preorder
     * @param indices
     * @return the index of the first right sublist element
     */
    private int findRightIndex(List<T> preorder, Map<T, Integer> indices) {
        int index = indices.get(preorder.get(0));
        int i = 1;
        while (i < preorder.size() && indices.get(preorder.get(i)) < index) {
            i++;
        }
        return i;
    }

    private void constructPreCheck(T[] traversal1, T[] traversal2) {
        Preconditions.checkArgument(isEmpty(), "Tree is not empty");
        Preconditions.checkNotNull(traversal1);
        Preconditions.checkNotNull(traversal2);
        Preconditions.checkArgument(traversal1.length == traversal2.length, "given lists must have equal sizes");
    }

    @Override
    public List<T> getTree(Traverse traverse) {
        Preconditions.checkArgument(!isEmpty(), "Tree is empty");
        switch (traverse) {
            case IN_ORDER:
                return getInorderTree();
            case PRE_ORDER:
                return getPreorderTree();
            case POST_ORDER:
                return getPostorderTree();
            default:
                throw new IllegalArgumentException(String.format("Mode %s is not supported", traverse));
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isSymmetric() {
        if (root == null) {
            return  true;
        }
        return areSymmetric(root.getLeft(), root.getRight());
    }

    @Override
    public int size() {
        return size(this.root);
    }

    private boolean areSymmetric(IMyTreeNode<T> left, IMyTreeNode<T> right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.getValue() != right.getValue()) {
            return false;
        }
        return areSymmetric(left.getLeft(), right.getRight()) && areSymmetric(left.getRight(), right.getLeft());
    }

    private List<T> getPostorderTree() {
        Stack<IMyTreeNode<T>> stack = new Stack<>();
        stack.push(this.root);
        Set<IMyTreeNode<T>> visited = new HashSet<>();
        List<T> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            IMyTreeNode<T> node = stack.peek();
            if (visited.contains(node)) {
                list.add(stack.pop().getValue());
            } else {
                if (node.hasRight()) {
                    stack.push(node.getRight());
                }
                if (node.hasLeft()) {
                    stack.push(node.getLeft());
                }
                visited.add(node);
            }
        }
        return list;
    }

    private List<T> getPreorderTree() {
        Stack<IMyTreeNode<T>> stack = new Stack<>();
        stack.push(this.root);
        List<T> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            IMyTreeNode<T> node = stack.pop();
            list.add(node.getValue());
            if (node.hasRight()) {
                stack.push(node.getRight());
            }
            if (node.hasLeft()) {
                stack.push(node.getLeft());
            }
        }
        return list;
    }

    private List<T> getInorderTree() {
        Stack<IMyTreeNode<T>> stack = getLeftBranch(this.root);
        List<T> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            IMyTreeNode<T> node = stack.pop();
            list.add(node.getValue());
            if (node.hasRight()) {
                stack.addAll(getLeftBranch(node.getRight()));
            }
        }
        return list;
    }

    private Stack<IMyTreeNode<T>> getLeftBranch(IMyTreeNode<T> node) {
        Stack<IMyTreeNode<T>> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
        return stack;
    }

    private int size(IMyTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
}
