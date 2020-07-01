package dataStructure;

import javafx.util.Pair;

import java.util.List;

/**
 * a data structure to keep distinct intervals
 * point queries can be made to find intersecting intervals
 */
public interface IMyIntervalTree {

    static IMyIntervalTree getInstance(List<Pair<Integer, Integer>> intervals) {
        IntervalTree tree = new IntervalTree(intervals);
        tree.build();
        return tree;
    }

    List<Pair<Integer, Integer>> find(int point);

    List<Pair<Integer, Integer>> find(Pair<Integer, Integer> interval);

}
