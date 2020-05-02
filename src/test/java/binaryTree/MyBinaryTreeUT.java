package binaryTree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyBinaryTreeUT {

    @Test
    public void testSize() {
        IMyBinaryTree<String> tree = createTestTree();
        Assert.assertEquals(8, tree.size());
    }

    @Test
    public void testInorderTraversal() {
        IMyBinaryTree<String> tree = createTestTree();
        List<String> nodes = tree.getTree(IMyBinaryTree.Traverse.IN_ORDER);
        List<String> list = Arrays.asList("3", "1", "9", "4", "0", "2", "6", "14");
        Assert.assertEquals(list, nodes);
    }

    @Test
    public void testPreorderTraversal() {
        IMyBinaryTree<String> tree = createTestTree();
        List<String> nodes = tree.getTree(IMyBinaryTree.Traverse.PRE_ORDER);
        List<String> list = Arrays.asList("0", "1", "3", "4", "9", "2", "6", "14");
        Assert.assertEquals(list, nodes);
    }

    @Test
    public void constructPreorderInorder() {
        IMyBinaryTree<String> tree = IMyBinaryTree.getInstance();
        tree.constructPreorderInorder(new String[] {"0", "1", "3", "4", "9", "2", "6", "14"}, new String[] {"3", "1", "9","4", "0", "2", "6", "14"});
        List<String> inorderNodes = tree.getTree(IMyBinaryTree.Traverse.IN_ORDER);
        List<String> expectedInorderNodes = Arrays.asList("3", "1", "9", "4", "0", "2", "6", "14");
        Assert.assertEquals(expectedInorderNodes, inorderNodes);
        List<String> preorderNodes = tree.getTree(IMyBinaryTree.Traverse.PRE_ORDER);
        List<String> expectedPreorderNodes = Arrays.asList("0", "1", "3", "4", "9", "2", "6", "14");
        Assert.assertEquals(expectedPreorderNodes, preorderNodes);
    }

    @Test
    public void testPostorderTraversal() {
        IMyBinaryTree<String> tree = createTestTree();
        List<String> nodes = tree.getTree(IMyBinaryTree.Traverse.POST_ORDER);
        List<String> expected = Arrays.asList("3", "9", "4", "1", "14", "6", "2", "0");
        Assert.assertEquals(expected, nodes);
    }

    @Test
    public void constructInorderPostorder() {
        IMyBinaryTree<String> tree = IMyBinaryTree.getInstance();
        tree.constructInorderPostorder(new String[] {"3", "1", "9", "4", "0", "2", "6", "14"}, new String[] {"3", "9", "4", "1", "14", "6", "2", "0"});
        List<String> inorderNodes = tree.getTree(IMyBinaryTree.Traverse.IN_ORDER);
        List<String> expectedInorderNodes = Arrays.asList("3", "1", "9", "4", "0", "2", "6", "14");
        Assert.assertEquals(expectedInorderNodes, inorderNodes);
        List<String> preorderNodes = tree.getTree(IMyBinaryTree.Traverse.PRE_ORDER);
        List<String> expectedPreorderNodes = Arrays.asList("0", "1", "3", "4", "9", "2", "6", "14");
        Assert.assertEquals(expectedPreorderNodes, preorderNodes);
    }

    @Test
    public void constructPreorderPostorder() {
        IMyBinaryTree<String> tree = IMyBinaryTree.getInstance();
        tree.constructPreorderPostorder(new String[] {"2", "4", "5", "6", "7"}, new String[] {"4", "6", "7", "5", "2"});
        List<String> preorderNodes = tree.getTree(IMyBinaryTree.Traverse.PRE_ORDER);
        List<String> expectedPreorderNodes = Arrays.asList("2", "4", "5", "6", "7");
        Assert.assertEquals(expectedPreorderNodes, preorderNodes);
        List<String> postorderNodes = tree.getTree(IMyBinaryTree.Traverse.POST_ORDER);
        List<String> expectedPostorderNodes = Arrays.asList("4", "6", "7", "5", "2");
        Assert.assertEquals(expectedPostorderNodes, postorderNodes);
    }

    @Test
    public void constructPreorderPostorder_2() {
        IMyBinaryTree<String> tree = IMyBinaryTree.getInstance();
        tree.constructPreorderPostorder(new String[] {"1", "2", "4", "5", "6", "7", "3"}, new String[] {"4", "6", "7", "5", "2", "3", "1"});
        List<String> preorderNodes = tree.getTree(IMyBinaryTree.Traverse.PRE_ORDER);
        List<String> expectedPreorderNodes = Arrays.asList("1", "2", "4", "5", "6", "7", "3");
        Assert.assertEquals(expectedPreorderNodes, preorderNodes);
        List<String> postorderNodes = tree.getTree(IMyBinaryTree.Traverse.POST_ORDER);
        List<String> expectedPostorderNodes = Arrays.asList("4", "6", "7", "5", "2", "3", "1");
        Assert.assertEquals(expectedPostorderNodes, postorderNodes);
    }

    /*
                                        0
                            1                      2
                    3                 4                6
                                9                           14
     */
    private IMyBinaryTree<String> createTestTree() {
        IMyTreeNode<String> root = new MyTreeNode<>("0");
        IMyTreeNode<String> node1 = root.addLeft("1");
        IMyTreeNode<String> node2 = root.addRight("2");
        IMyTreeNode<String> node3 = node1.addLeft("3");
        IMyTreeNode<String> node4 = node1.addRight("4");
        IMyTreeNode<String> node6 = node2.addRight("6");
        IMyTreeNode<String> node9 = node4.addLeft("9");
        IMyTreeNode<String> node14 = node6.addRight("14");
        return IMyBinaryTree.getInstance(root);
    }

}
