package dataStructure;

public interface IMySegmentTree {

    enum Mode {
        MIN, MAX;
    }

    int query(int low, int high);

    static IMySegmentTree getInstance(int[] arr, Mode mode) {
        return MySegmentTree.getInstance(arr, mode);
    }
}
