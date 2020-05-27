package leetCode;

public class FindDuplicate {

    /**
     * There is a list of (n + 1) integers in range of 1 to n. There is exactly one integer that exists more than once
     * in this list. Find that number in O(1) space and O(n) time. Array is unmodifiable.
     *
     * Algorithm:
     * The idea is same as finding a cycle in a linkedlist. Each node refers to another, using its index. The value
     * of each number is used as an index for the next node.
     *
     * @return number that happens at least twice in the list
     */
    public static int find(int[] arr) {
        int slow = 0;
        int fast = 0;
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        } while (arr[slow] != arr[fast]);
        fast = 0;
        while (arr[slow] != arr[fast]) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return arr[slow];
    }
}
