package binaryTree;

import java.util.List;

public interface IMyBinaryTree<T> {

    enum Traverse {
        IN_ORDER, PRE_ORDER, POST_ORDER;
    }

    IMyTreeNode<T> getRoot();

    void constructPreorderInorder(T[] preorder, T[] inorder);

    void constructInorderPostorder(T[] inorder, T[] postorder);

    /**
     * Creating a unique tree given preorder and postorder is only possible for full binary trees.
     */
    void constructPreorderPostorder(T[] preorder, T[] postorder);

    List<T> getTree(Traverse traverse);

    int size();

    boolean isEmpty();

    /**
     * Whether tree is symmetric value-wise or not
     */
    boolean isSymmetric();

    static <T> IMyBinaryTree<T> getInstance() {
        return MyBinaryTree.getInstance();
    }

    static <T> IMyBinaryTree<T> getInstance(IMyTreeNode<T> node) {
        return MyBinaryTree.getInstance(node);
    }
}
