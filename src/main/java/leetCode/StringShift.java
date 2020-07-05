package leetCode;

public class StringShift {

    /**
     * You are given a string s containing lowercase English letters, and a matrix shift,
     * where shift[i] = [direction, amount]:
     *
     * direction can be 0 (for left shift) or 1 (for right shift).
     * amount is the amount by which string s is to be shifted.
     * A left shift by 1 means remove the first character of s and append it to the end.
     * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
     *
     * @param s
     * @param shifts
     * @return
     */
    public static String shift(String s, int[][] shifts) {
        char[] arr = s.toCharArray();
        int leftShifts = getLeftShifts(shifts, s.length());
        reverseSubstring(arr, 0, leftShifts - 1);
        reverseSubstring(arr, leftShifts, s.length() - 1);
        reverseSubstring(arr, 0, s.length() - 1);
        return new String(arr);
    }

    private static int getLeftShifts(int[][] shifts, int stringLength) {
        int totalShift = 0;
        for (int[] shift: shifts) {
            totalShift += shift[1] * (shift[0] == 0 ? -1 : 1);
        }
        return stringLength - (((totalShift % stringLength) + stringLength) % stringLength);
    }

    private static void reverseSubstring(char[] arr, int l, int r) {
        while (l < r) {
            swap(arr, r, l);
            l++;
            r--;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
