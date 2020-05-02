package dataStructure;

import com.google.common.base.Preconditions;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Data structure to store intervals
 * Space complexity: O(n)
 * Construction:
 *  Select a point to split the list of intervals in three (e.g. sort and then choose or randomly select an interval and
 *      its mid point)
 *  Split the list of intervals into three, one overlapping with the selected midpoint, one end before this midpoint
 *      and one list starting after this midpoint
 *  Recursively continue doing this
 *  To facilitate interval queries, create a set of nodes for each endpoint of the intervals, sort them increasingly
 *  Time complexity: O(nlogn)
 *      O(logn) levels: in case of creating a balanced tree
 *      O(n): on each level, splitting into three lists for each node
 *      O(nlog): time to create a list/tree of endpoints
 * Query:
 *  Point query:
 *      Start from the root, and add the found intersecting intervals to a list
 *      On each node, add the intersecting intervals to the list
 *      If the point is before the node's midpoint (which is already stored in node) go to the left, and repeat
 *          otherwise, go to the right child and repeat
 *  Interval query:
 *      1. Find all intervals that have a start/endpoint within the query:
 *          Find the successor of query left endpoint in the list/tree of interval endpoints
 *          Find the predecessor of query right endpoint in the list/tree of interval endpoints
 *          Iterate through the nodes between these two nodes, and add associated interval to the list of results
 *      2. Find the intervals that totally encompass the query
 *          Use the point query method to find all intersecting intervals with a random point within the query interval
 * Point query time complexity: O(m + logn):
 *  O(m): find the intersecting intervals (with query) associated to each node
 *  log(n): traversing down the tree
 * Interval query time complexity: O(m + logn) + O(m + logn) = O(m + logn)
 *  O(m + logn) : to find all the intervals that encompass the query (as explained in point query time complexity analysis)
 *  O(m + logn) :
 *      O(logn): to find the successor and predecessor of query endpoints (this implementation is using list: O(n))
 *      O(m): to find all the intervals with one endpoint within the query interval
 */
public class MyIntervalTree implements IMyIntervalTree {

    private final DSNode dsRoot; // main tree root, where left, right, and overlapping intervals are kept for each node
    private final List<Node> endpoints; // set of endpoints, each referencing the interval it belongs to

    private MyIntervalTree(List<Pair<Integer, Integer>> intervals) {
        Preconditions.checkArgument(intervals != null, "input list of intervals cannot be null");
        Preconditions.checkArgument(intervals.stream().noneMatch(pair -> pair.getKey() > pair.getValue()), "bad input interval");
        this.dsRoot = constructMainTree(intervals.stream().sorted(Comparator.comparing(Pair::getKey)).collect(Collectors.toList()));
        this.endpoints = constructEndpointsTree(intervals);
    }


    @Override
    public List<Pair<Integer, Integer>> find(int query) {
        return find(dsRoot, query);
    }

    @Override
    public List<Pair<Integer, Integer>> find(Pair<Integer, Integer> query) {
        /*
            1. Use point-query method to find all intersecting intervals with any point of query
            2. Find all the intervals that their endpoints is in [interval.left, interval.right]
         */
        Set<Pair<Integer, Integer>> intersects = findEndpointsInRange(query);
        intersects.addAll(find(query.getKey()));
        return new ArrayList<>(intersects);
    }

    public static IMyIntervalTree getInstance(List<Pair<Integer, Integer>> intervals) {
        return new MyIntervalTree(intervals);
    }

    private Set<Pair<Integer, Integer>> findEndpointsInRange(Pair<Integer, Integer> query) {
        // these couple of whiles can be improved by using binary search for lists with possible duplicate values
        // which is out of scope of this data structure
        int l = 0;
        while (l < endpoints.size() && endpoints.get(l).val < query.getKey()) {
            l++;
        }
        int r = endpoints.size() - 1;
        while (r > 0 && endpoints.get(r).val > query.getValue()) {
            r--;
        }
        Set<Pair<Integer, Integer>> result = new HashSet<>();
        for (int i = l; i <= r; i++) {
            result.add(endpoints.get(i).interval);
        }
        return result;
    }

    private List<Node> constructEndpointsTree(List<Pair<Integer, Integer>> intervals) {
        List<Node> endpoints = new ArrayList<>();
        for (Pair<Integer, Integer> interval: intervals) {
            endpoints.add(new Node(interval.getKey(), interval));
            endpoints.add(new Node(interval.getValue(), interval));
        }
        endpoints.sort(Comparator.comparingInt(Node::getVal));
        return endpoints;
    }

    private DSNode constructMainTree(List<Pair<Integer, Integer>> intervals) {
        if (intervals.size() == 0) {
            return null;
        }
        int midPoint = getMidPoint(intervals.get(intervals.size() / 2));
        List<Pair<Integer, Integer>> center = new ArrayList<>();
        List<Pair<Integer, Integer>> left = new ArrayList<>();
        List<Pair<Integer, Integer>> right = new ArrayList<>();
        for (Pair<Integer, Integer> pair: intervals) {
            if (intersects(pair, midPoint)) {
                center.add(pair);
            } else if (pair.getKey() > midPoint) {
                right.add(pair);
            } else {
                left.add(pair);
            }
        }
        return new DSNode(midPoint, center, constructMainTree(left), constructMainTree(right));
    }

    private boolean intersects(Pair<Integer, Integer> interval, int point) {
        return interval.getKey() <= point && interval.getValue() >= point;
    }

    private int getMidPoint(Pair<Integer, Integer> interval) {
        return (interval.getValue() + interval.getKey()) / 2;
    }

    private List<Pair<Integer, Integer>> find(DSNode DSNode, int query) {
        if (DSNode == null) {
            return Collections.emptyList();
        }
        List<Pair<Integer, Integer>> result = new ArrayList<>(DSNode.findIntersects(query));
        if (query < DSNode.midPoint) {
            result.addAll(find(DSNode.left, query));
        } else {
            result.addAll(find(DSNode.right, query));
        }
        return result;
    }

    /**
     * Data structure node to keep the main interval tree
     */
    private static class DSNode {
        DSNode left, right;
        int midPoint;
        List<Pair<Integer, Integer>> center_L; // intervals overlapping with midPoint, sorted based on left end
        List<Pair<Integer, Integer>> center_R; // intervals overlapping with midPoint, sorted based on right end
        DSNode(int midPoint, List<Pair<Integer, Integer>> center, DSNode left, DSNode right) {
            this.midPoint = midPoint;
            this.left = left;
            this.right = right;
            this.center_L = new ArrayList<>(center);
            this.center_R = new ArrayList<>(center);
            this.center_L.sort(Comparator.comparing(Pair::getKey));
            this.center_R.sort(Comparator.comparing(Pair::getValue, Comparator.reverseOrder()));
        }
        List<Pair<Integer, Integer>> findIntersects(int query) {
            List<Pair<Integer, Integer>> intersects = new ArrayList<>();
            int index = 0;
            if (query < midPoint) {
                while (index < center_L.size() && center_L.get(index).getKey() <= query) {
                    intersects.add(center_L.get(index));
                    index++;
                }
            } else {
                while (index < center_R.size() && center_R.get(index).getValue() >= query) {
                    intersects.add(center_R.get(index));
                    index++;
                }
            }
            return intersects;
        }
    }

    /**
     * interval endpoint and a pointer to the interval it references to
     */
    private static class Node {
        int val;
        Pair<Integer, Integer> interval;
        Node(int val, Pair<Integer, Integer> interval) {
            this.val = val;
            this.interval = interval;
        }
        int getVal() {
            return val;
        }
    }
}
