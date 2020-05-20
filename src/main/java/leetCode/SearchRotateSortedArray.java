package leetCode;

public class SearchRotateSortedArray {

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
