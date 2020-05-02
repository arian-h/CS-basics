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
        this.root = constructPreorderInorder(new LinkedList<>(Arrays.asList(preorder)), inorder, 0, inorder.length - 1);
    }

    @Override
    public void constructInorderPostorder(T[] inorder, T[] postorder) {
        constructPreCheck(inorder, postorder);
        Stack<T> postorderStack = new Stack<>();
        Arrays.stream(postorder).forEach(postorderStack::push);
        this.root = constructInorderPostorder(postorderStack, inorder, 0, inorder.length - 1);
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
    public int size() {
        return size(this.root);
    }

    /*
     * This implementation takes O(n^2) time and O(n) space
     */
    private IMyTreeNode<T> constructPreorderInorder(Queue<T> preorder, T[] inorder, int inorderLeftIndex, int inorderRightIndex) {
        if (preorder.isEmpty() || inorderLeftIndex > inorderRightIndex) {
            return null;
        }
        T rootValue = preorder.poll();
        IMyTreeNode<T> root = new MyTreeNode<>(rootValue);
        int index = findNode(inorder, rootValue);
        root.addLeft(constructPreorderInorder(preorder, inorder, inorderLeftIndex, index - 1));
        root.addRight(constructPreorderInorder(preorder, inorder, index + 1, inorderRightIndex));
        return root;
    }

    private IMyTreeNode<T> constructInorderPostorder(Stack<T> postorder, T[] inorder, int inorderLeftIndex, int inorderRightIndex) {
        if (postorder.isEmpty() || inorderLeftIndex > inorderRightIndex) {
            return null;
        }
        T rootValue = postorder.pop();
        IMyTreeNode<T> root = new MyTreeNode<>(rootValue);
        int index = findNode(inorder, rootValue);
        root.addRight(constructInorderPostorder(postorder, inorder, index + 1, inorderRightIndex));
        root.addLeft(constructInorderPostorder(postorder, inorder, inorderLeftIndex, index - 1));
        return root;
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
