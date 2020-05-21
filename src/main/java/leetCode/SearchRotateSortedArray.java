package leetCode;

public class SearchRotateSortedArray {

    /**
     * There is an array that is sorted in ascending order, and then shifted to right.
     * For example, the array was initially sorted in ascending order 1,3,4,5,7
     * and then it was shifted two places to right: 5, 7, 1, 3, 4
     * Given an integer, we want to find out if the integer exists in this array
     *
     * It's not possible to provide an algorithm that can do this in O(logn). For example for the following array:
     * 1 1 1 1 1 3 1 1
     * It's impossible to check the existence of '3' in O(logn) time.
     * But it's still possible to do this in an efficient time, for most cases (i.e. those that don't have too many
     * duplicate elements).
     *
     * Algorithm:
     * Just like binary search algorithm, get the middle, check if it is what we are looking for, if it is not,
     * the algorithm has to decide to go left or right. Here there is a trick to get rid of at least one
     * duplicate element. If the mid element is equal to the left element, we can throw out left element, as we
     * know it's not what we are looking for.
     * For the rest of the search, the algorithm abandons the sub array (left/right) that it's sure the element
     * doesn't exist in.
     *
     * It continues until there is only 1 element, and checks if that element is what it looks for.
     *
     *
     * @param arr a sorted, but rotated array
     * @param target the element to check its existence in array
     * @return if target exists in arr
     */
    public static boolean contains(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (arr[mid] == arr[l]) {
                l++;
            } else if (arr[mid] > arr[l]) { // left of mid is sorted
                if (target < arr[mid] && target >= arr[l]) { // definitely left of mid
                    r = mid - 1;
                } else { // definitely not on the left of mid
                    l = mid + 1;
                }
            } else { // right of mid is sorted
                if (target > arr[mid] && target <= arr[r]) { // definitely right of mid
                    l = mid + 1;
                } else { // definitely not on the right of mid
                    r = mid - 1;
                }
            }
        }
        return arr[l] == target;
    }

}
