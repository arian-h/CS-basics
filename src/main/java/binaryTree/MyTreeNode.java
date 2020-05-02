package binaryTree;

import com.google.common.base.Preconditions;

public class MyTreeNode<T> implements IMyTreeNode<T> {
    T value;
    IMyTreeNode<T> left;
    IMyTreeNode<T> right;

    public MyTreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public IMyTreeNode<T> addLeft(T t) {
        Preconditions.checkArgument(this.left == null, "Node already has a left child");
        this.left = new MyTreeNode<>(t);
        return this.left;
    }

    @Override
    public IMyTreeNode<T> addRight(T t) {
        Preconditions.checkArgument(this.right == null, "Node already has a right child");
        this.right = new MyTreeNode<>(t);
        return this.right;
    }

    @Override
    public IMyTreeNode<T> addLeft(IMyTreeNode<T> node) {
        Preconditions.checkArgument(this.left == null, "Node already has a left child");
        this.left = node;
        return this.left;
    }

    @Override
    public IMyTreeNode<T> addRight(IMyTreeNode<T> node) {
        Preconditions.checkArgument(this.right == null, "Node already has a right child");
        this.right = node;
        return this.right;
    }

    @Override
    public IMyTreeNode<T> getRight() {
        return this.right;
    }

    @Override
    public IMyTreeNode<T> getLeft() {
        return this.left;
    }

    @Override
    public T getValue() {
        return this.value;
    }
}
