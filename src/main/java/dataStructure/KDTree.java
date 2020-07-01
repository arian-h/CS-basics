package dataStructure;

import com.google.common.base.Preconditions;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Data structure to store 2-dimensional data points, with efficient querying to find the closest point to a given query
 * Construction:
 *
 *  Space complexity: O(n)
 *      Each node has a dimension which is a splitting dimension. And a point that splits the points into two subsets
 *      (left and right children) according to the dimension for that node.
 *
 *  Time complexity: O(nlogn)
 *      We hope it is a balanced tree: o(logn) levels
 *      At each level the set of points, that reached that level, are split into two, this takes O(n) time
 *      therefore the whole build time complexity is O(nlogn)
 *
 *  Find closest neighbor:
 *      Move recursively from root and at each node evaluate if that it's closer to the closest found so far:
 *          - if current node is null, ignore
 *          - if current node is in a box that is farther than the closest point found so far, ignore it
 *          - otherwise, we have to check both subtrees, BUT, we use our best guess to make querying more efficient:
 *              if query is on left hand side of the current point, search left subtree first,
 *              search right subtree first
 */
public class KDTree implements IMyKDTree {

    private final List<Point> mem;
    private Node root;
    private final Random random;
    private final static int DIMENSIONS = 2;

    public KDTree(Pair<Integer, Integer>[] points) {
        Preconditions.checkNotNull(points);
        this.mem = Arrays.stream(points)
                .map(point -> new Point(point.getKey(), point.getValue()))
                .collect(Collectors.toList());
        this.random = new Random();
    }

    public void build() {
        this.root = build(this.mem, 0);
    }

    public Node build(List<Point> points, int dimension) {
        if (points.size() == 0) {
            return null;
        }
        Point mid = points.remove(random.nextInt(points.size()));
        List<Point> left = points.stream()
                .filter(point -> compare(point, mid, dimension) <= 0)
                .collect(Collectors.toList());
        List<Point> right = points.stream()
                .filter(point -> compare(point, mid, dimension) > 0)
                .collect(Collectors.toList());
        Node root = new Node(mid);
        root.left = build(left, (dimension + 1) % DIMENSIONS);
        root.right = build(right, (dimension + 1) % DIMENSIONS);
        return root;
    }

    @Override
    public Pair<Integer, Integer> findClosest(Pair<Integer, Integer> point) {
        Point closest = findClosest(root, new Point(point.getKey(),
                point.getValue()), new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), 0);
        return new Pair<>(closest.x[0], closest.x[1]);
    }

    private Point findClosest(Node node, Point p, Point closest, int dimension) {
        if (node == null) {
            return closest;
        }
        if (distance(p, node.point, dimension) > distance(p, closest)) {
            return closest;
        }
        if (distance(p, node.point) < distance(p, closest)) {
            closest = node.point;
        }
        // check both subtrees, but first the subtree that its root node is closer
        if (node.left == null) {
            return findClosest(node.right, p, closest, (dimension + 1) % 2);
        }
        if (node.right == null) {
            return findClosest(node.left, p, closest, (dimension + 1) % 2);
        }
        if (distance(p, node.left.point) < distance(p, node.right.point)) {
            closest = findClosest(node.left, p, closest, (dimension + 1) % 2);
            closest = findClosest(node.right, p, closest, (dimension + 1) % 2);
        } else {
            closest = findClosest(node.right, p, closest, (dimension + 1) % 2);
            closest = findClosest(node.left, p, closest, (dimension + 1) % 2);
        }
        return closest;
    }

    private int compare(Point p1, Point p2, int dimension) {
        return p1.x[dimension] - p2.x[dimension];
    }

    private int distance(Point p1, Point p2, int dimension) {
        return Math.abs(p1.x[dimension] - p2.x[dimension]);
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(distance(p1, p2, 0), 2) + Math.pow(distance(p1, p2, 1), 2));
    }

    private static class Node {
        Point point;
        Node left, right;
        public Node(Point p) {
            this.point = p;
        }
    }

    private static class Point {
        int[] x;
        public Point(int x, int y) {
            this.x = new int[2];
            this.x[0] = x;
            this.x[1] = y;
        }
    }
}
