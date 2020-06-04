package design.composite;

import org.junit.Assert;
import org.junit.Test;

public class CompositeUT {

    @Test
    public void testComposite() {
        Node root = new InternalNode(0);
        Node parent1 = new InternalNode(1);
        Node parent2 = new InternalNode(2);
        Node leaf1 = new Leaf(3);
        Node leaf2 = new Leaf(4);
        root.addChild(parent1);
        root.addChild(parent2);
        parent1.addChild(leaf1);
        parent2.addChild(leaf2);
        Assert.assertEquals(7, root.sum());
    }

}
