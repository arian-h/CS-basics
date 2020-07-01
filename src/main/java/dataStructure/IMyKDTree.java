package dataStructure;

import javafx.util.Pair;

public interface IMyKDTree {

    Pair<Integer, Integer> findClosest(Pair<Integer, Integer> point);

    static IMyKDTree getInstance(Pair<Integer, Integer>[] points) {
        KDTree kdTree = new KDTree(points);
        kdTree.build();
        return kdTree;
    }
}
