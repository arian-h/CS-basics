package leetCode;

public class StringShift {

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
