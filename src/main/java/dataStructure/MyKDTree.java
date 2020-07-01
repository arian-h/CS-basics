package dataStructure;

import com.google.common.base.Preconditions;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;


public class MyKDTree implements IMyKDTree {

    private final Node root;
    private final static Random random = new Random();

    public static IMyKDTree getInstance(Pair<Integer, Integer>[] points) {
        return new MyKDTree(points);
    }

    @Override
    public Pair<Integer, Integer> findClosest(Pair<Integer, Integer> query) {
        Preconditions.checkArgument(query != null, "query cannot be null");
        if (root == null) {
            return null;
        }
        return findNearest(root, query, root.point);
    }

    private MyKDTree(Pair<Integer, Integer>[] points) {
        Preconditions.checkArgument(points != null, "input list of points cannot be null");
        this.root = construct(Arrays.stream(points).collect(Collectors.toList()), 0);
    }

    private Pair<Integer, Integer> findNearest(Node root, Pair<Integer, Integer> query, Pair<Integer, Integer> closest) {
        if (root == null) {
            return null;
        }
        // if the rectangle is further than the closest point so far, ignore the whole subtree
        if (Math.abs(getValue(root.point, root.dimension) - getValue(query, root.dimension)) > distance(query, closest)) {
            return null;
        }
        closest = updateClosest(query, root.point, closest);
        /*
            we hope that if the point is on the left of root,
            there is a better chance to find the closest point on the left subtree, but we try both
            only the orders differ
         */
        if (isOnLeft(query, root.point, root.dimension)) {
            closest = updateClosest(query, findNearest(root.left, query, closest), closest);
            closest = updateClosest(query, findNearest(root.right, query, closest), closest);
        } else {
            closest = updateClosest(query, findNearest(root.right, query, closest), closest);
            closest = updateClosest(query, findNearest(root.left, query, closest), closest);
        }
        return closest;
    }

    private boolean isOnLeft(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2, int dimension) {
        return getValue(p1, dimension) <= getValue(p2, dimension);
    }

    private Pair<Integer, Integer> updateClosest(Pair<Integer, Integer> query, Pair<Integer, Integer> point, Pair<Integer, Integer> closest) {
        if (point == null) {
            return closest;
        }
        if (distance(query, point) < distance(query, closest)) {
            return point;
        }
        return closest;
    }

    private int distance(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return (int) Math.sqrt(Math.pow(p1.getKey() - p2.getKey(), 2) + Math.pow(p1.getValue() - p2.getValue(), 2));
    }

    private Node construct(List<Pair<Integer, Integer>> points, int dimension) {
        if (points.size() == 0) {
            return null;
        }
        Pair<Integer, Integer> median = findMedian(points);
        Node node = new Node(median, dimension);
        node.left = construct(points.stream()
                .filter(point -> point != median)
                .filter(point -> getValue(point, dimension) <= getValue(median, dimension))
                .collect(Collectors.toList()), (dimension + 1) % 2);
        node.right = construct(points.stream()
                .filter(point -> point != median)
                .filter(point -> getValue(point, dimension) > getValue(median, dimension))
                .collect(Collectors.toList()), (dimension + 1) % 2);
        return node;
    }

    private int getValue(Pair<Integer, Integer> point, int dimension) {
        if (dimension == 0) {
            return point.getKey();
        }
        return point.getValue();
    }

    Pair<Integer, Integer> findMedian(List<Pair<Integer, Integer>> points) {
        return points.get(random.nextInt(points.size()));
    }

    private static class Node {
        int dimension;
        Pair<Integer, Integer> point;
        Node left, right;
        Node(Pair<Integer, Integer> point, int dimension) {
            this.dimension = dimension;
            this.point = point;
        }
    }
}
