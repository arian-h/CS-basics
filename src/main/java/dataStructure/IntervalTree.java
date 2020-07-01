package dataStructure;

import com.google.common.base.Preconditions;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
 *      O(nlogn): time to create a list/tree of endpoints
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
public class IntervalTree implements IMyIntervalTree {

    private final List<Pair<Integer, Integer>> originalIntervals;
    private IntervalNode intervalRoot;
    private TreeSet<PointNode> pointsTree;

    public IntervalTree(List<Pair<Integer, Integer>> intervals) {
        Preconditions.checkNotNull(intervals);
        this.originalIntervals = intervals;
    }

    public void build() {
        List<Pair<Integer, Integer>> intervals = originalIntervals.stream()
                .sorted(Comparator.comparingInt(Pair::getKey))
                .collect(Collectors.toList());
        this.intervalRoot = buildIntervalsTree(intervals);
        this.pointsTree = buildPointsTree(intervals);
    }

    @Override
    public List<Pair<Integer, Integer>> find(int point) {
        return find(intervalRoot, point);
    }

    @Override
    public List<Pair<Integer, Integer>> find(Pair<Integer, Integer> interval) {
        // find the intervals that start after the query interval lower point and ends before
        // the query interval higher point
        Set<Pair<Integer, Integer>> intervals = pointsTree
                .subSet(new PointNode(interval.getKey()), true, new PointNode(interval.getValue()), true)
                .stream()
                .map(pointNode -> pointNode.interval)
                .collect(Collectors.toSet());
        // find all the intervals that have intersection with a random point of query interval
        intervals.addAll(find(intervalRoot, interval.getKey()));
        return new ArrayList<>(intervals);
    }

    private TreeSet<PointNode> buildPointsTree(List<Pair<Integer, Integer>> intervals) {
        if (intervals.size() == 0) {
            return null;
        }
        List<PointNode> pointNodes = new ArrayList<>();
        for (Pair<Integer, Integer> pair: intervals) {
            pointNodes.add(new PointNode(pair.getKey(), pair));
            pointNodes.add(new PointNode(pair.getValue(), pair));
        }
        TreeSet<PointNode> tree = new TreeSet<>(Comparator.comparingInt(interval -> interval.point));
        tree.addAll(pointNodes);
        return tree;
    }

    private IntervalNode buildIntervalsTree(List<Pair<Integer, Integer>> intervals) {
        if (intervals.size() == 0) {
            return null;
        }
        int mid = getMid(intervals.get(intervals.size() / 2));
        List<Pair<Integer, Integer>> left = intervals.stream()
                .filter(interval -> interval.getValue() < mid)
                .collect(Collectors.toList());
        List<Pair<Integer, Integer>> right = intervals.stream()
                .filter(interval -> interval.getKey() > mid)
                .collect(Collectors.toList());
        IntervalNode root = new IntervalNode();
        root.overlaps = getOverlappingIntervals(intervals, mid);
        root.left = buildIntervalsTree(left);
        root.right = buildIntervalsTree(right);
        root.point = mid;
        return root;
    }

    private int getMid(Pair<Integer, Integer> interval) {
        return (interval.getKey() + interval.getValue()) / 2;
    }

    private List<Pair<Integer, Integer>> find(IntervalNode intervalNode, Pair<Integer, Integer> query) {
        if (intervalNode == null) {
            return Collections.emptyList();
        }
        List<Pair<Integer, Integer>> overlaps = getOverlappingIntervals(intervalNode.overlaps, query);
        if (query.getValue() > intervalNode.point) {
            overlaps.addAll(find(intervalNode.right, query));
        }
        if (query.getKey() < intervalNode.point) {
            overlaps.addAll(find(intervalNode.left, query));
        }
        return overlaps;
    }

    private List<Pair<Integer, Integer>> find(IntervalNode intervalNode, int query) {
        if (intervalNode == null) {
            return Collections.emptyList();
        }
        List<Pair<Integer, Integer>> overlaps = getOverlappingIntervals(intervalNode.overlaps, query);
        List<Pair<Integer, Integer>> childrenOverlaps =
                find(query < intervalNode.point ? intervalNode.left : intervalNode.right, query);
        overlaps.addAll(childrenOverlaps);
        return overlaps;
    }

    private List<Pair<Integer, Integer>> getOverlappingIntervals(List<Pair<Integer, Integer>> intervals, int query) {
        if (intervals.size() == 0) {
            return Collections.emptyList();
        }
        return intervals.stream()
                .filter(interval -> overlaps(interval, query))
                .collect(Collectors.toList());
    }

    private List<Pair<Integer, Integer>> getOverlappingIntervals(List<Pair<Integer, Integer>> intervals,
                                                                 Pair<Integer, Integer> query) {
        if (intervals.size() == 0) {
            return Collections.emptyList();
        }
        return intervals.stream()
                .filter(interval -> overlaps(interval, query))
                .collect(Collectors.toList());
    }

    private boolean overlaps(Pair<Integer, Integer> interval, Pair<Integer, Integer> query) {
        return overlaps(interval, query.getKey()) || overlaps(interval, query.getValue()) ||
                overlaps(query, interval.getValue()) || overlaps(query, interval.getKey());
    }

    private boolean overlaps(Pair<Integer, Integer> interval, int query) {
        return query >= interval.getKey() && query <= interval.getValue();
    }

    private static class IntervalNode {
        IntervalNode left, right;
        List<Pair<Integer, Integer>> overlaps;
        int point;
    }

    private static class PointNode {
        Pair<Integer, Integer> interval;
        int point;

        public PointNode(int point, Pair<Integer, Integer> interval) {
            this.point = point;
            this.interval = interval;
        }

        public PointNode(int point) {
            this.point = point;
        }
    }

}
