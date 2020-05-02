package binaryTree;

public interface IMyTreeNode<T> {
    IMyTreeNode<T> addLeft(T t);
    IMyTreeNode<T> addRight(T t);
    IMyTreeNode<T> addLeft(IMyTreeNode<T> node);
    IMyTreeNode<T> addRight(IMyTreeNode<T> node);
    IMyTreeNode<T> getRight();
    IMyTreeNode<T> getLeft();
    /*
     it's not a good practice to implement methods in interface using "default" keyword, as the original idea
     for it was backward compatibility
     */
    default boolean hasRight() {
        return getRight() != null;
    }
    default boolean hasLeft() {
        return getLeft() != null;
    }
    T getValue();
}
