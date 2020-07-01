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

    @Override
    public void constructPreorderPostorder(T[] preorder, T[] postorder) {
        constructPreCheck(preorder, postorder);
        this.root = constructPreorderPostorder(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
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

    /*
        Took a different approach for this algorithm, comparing to the other two.
        Recursively follows this logic:
            For each subproblem, the first element of the preorder array is same as the last element, which is root
            The remaining subarray must be broken down into two subarrays for left and right subtrees
            Figure out the size of the left subtree by finding first element of the preorder list the post order list
            Call the method recursively using the new left and right for pre and post order lists
     */
    private IMyTreeNode<T> constructPreorderPostorder(T[] preorder, T[] postorder, int preorderLeftIndex, int preorderRightIndex, int postorderLeftIndex, int postorderRightIndex) {
        if (preorderLeftIndex > preorderRightIndex || postorderLeftIndex > postorderRightIndex) {
            return null;
        }
        IMyTreeNode<T> root = new MyTreeNode<>(preorder[preorderLeftIndex]);
        postorderRightIndex--;
        preorderLeftIndex++;
        if (preorderLeftIndex > preorderRightIndex) {
            return root;
        }
        int index = findNode(postorder, preorder[preorderLeftIndex]);
        int leftSubtreeSize = index - postorderLeftIndex;
        root.addLeft(constructPreorderPostorder(preorder, postorder, preorderLeftIndex, preorderLeftIndex + leftSubtreeSize, postorderLeftIndex, index));
        root.addRight(constructPreorderPostorder(preorder, postorder, preorderLeftIndex + leftSubtreeSize + 1, preorderRightIndex, index + 1, postorderRightIndex));
        return root;
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

    private int findNode(T[] values, T toFind) {
        return IntStream.range(0, values.length).filter(i -> values[i].equals(toFind)).findFirst().orElse(-1);
    }

    private int size(IMyTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
}
