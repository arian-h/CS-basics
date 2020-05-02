package binaryTree;

import java.util.List;

public interface IMyBinaryTree<T> {

    enum Traverse {
        IN_ORDER, PRE_ORDER, POST_ORDER;
    }

    IMyTreeNode<T> getRoot();

    void constructPreorderInorder(T[] preorder, T[] inorder);

    void constructInorderPostorder(T[] inorder, T[] postorder);

    void constructPreorderPostorder(T[] preorder, T[] postorder);

    List<T> getTree(Traverse traverse);

    int size();

    boolean isEmpty();

    static <T> IMyBinaryTree<T> getInstance() {
        return MyBinaryTree.getInstance();
    }

    static <T> IMyBinaryTree<T> getInstance(IMyTreeNode<T> node) {
        return MyBinaryTree.getInstance(node);
    }
}
