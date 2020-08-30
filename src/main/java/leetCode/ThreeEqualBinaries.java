package leetCode;

public class ThreeEqualBinaries {

    /**
     * Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent
     * the same binary value.
     *
     * If it is possible, return any [i, j] with i+1 < j, such that:
     *
     * A[0], A[1], ..., A[i] is the first part;
     * A[i+1], A[i+2], ..., A[j-1] is the second part, and
     * A[j], A[j+1], ..., A[A.length - 1] is the third part.
     * All three parts have equal binary value.
     * If it is not possible, return [-1, -1].
     *
     * Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0]
     * represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
     *
     * Example 1:
     *
     * Input: [1,0,1,0,1]
     * Output: [0,3]
     *
     *
     * Solution:
     * Two equal binary numbers, have same number of 1's.
     * First count the number of 1's. If it's not divisible by 3, there is no solution.
     * If they are equal, start from the beginning and skip the leading zero's and count the number of ones, until
     * you have enough 1's, now you have the first part. Then do the same thing and find the second part, and the
     * rest will be the third part. But there could be an issue, so far, we didn't account for the trailing
     * zeros. For example the 0's between part 1 and 2, could either go to first or second part.
     * We know that the first and second number and third number must be equal, so they should have the same number of
     * 0's trailing them. We use this trick to divide these 0's between them. Finally we check to make sure that
     * the placement of 1's are equal as well.
     * @return int[]
     */
    public static int[] threeEqualParts(int[] arr) {
        int countOnes = 0;
        for (int j : arr) {
            if (j == 1) {
                countOnes++;
            }
        }
        if (countOnes == 0) {
            return new int[] { 0, 2 };
        }
        int[] impossibleResult = new int[] {-1, -1};
        if (countOnes % 3 != 0) {
            return impossibleResult;
        }
        int firstPartEndIndex = findPart(arr, 0, countOnes / 3);
        int secondPartEndIndex = findPart(arr, firstPartEndIndex + 1, countOnes / 3);
        int index = arr.length - 1;
        int countThirdPartTrailingZeros = 0;
        while (arr[index] != 1) {
            countThirdPartTrailingZeros++;
            index--;
        }
        if (getFirstNonZeroIndex(arr, firstPartEndIndex + 1) - firstPartEndIndex - 1 < countThirdPartTrailingZeros) {
            return impossibleResult;
        }
        if (getFirstNonZeroIndex(arr, secondPartEndIndex + 1) - secondPartEndIndex - 1 < countThirdPartTrailingZeros) {
            return impossibleResult;
        }
        firstPartEndIndex += countThirdPartTrailingZeros;
        secondPartEndIndex += countThirdPartTrailingZeros;
        if (!binariesEqual(arr, 0, firstPartEndIndex, secondPartEndIndex)
                || !binariesEqual(arr, firstPartEndIndex + 1, secondPartEndIndex, arr.length - 1)) {
            return impossibleResult;
        }
        return new int[] { firstPartEndIndex,
                secondPartEndIndex + 1 };
    }

    private static int findPart(int[] arr, int start, int countOnes) {
        int index = getFirstNonZeroIndex(arr, start);
        int ones = 0;
        while (ones < countOnes) {
            if (arr[index] == 1) {
                ones++;
            }
            index++;
        }
        return index - 1;
    }

    private static boolean binariesEqual(int[] arr, int i0, int i1, int i2) {
        int index1 = getFirstNonZeroIndex(arr, i0);
        int index2 = getFirstNonZeroIndex(arr, i1 + 1);
        while (index1 <= i1 && index2 <= i2) {
            if (arr[index1] != arr[index2]) {
                return false;
            }
            index1++;
            index2++;
        }
        return index1 == i1 + 1 && index2 == i2 + 1;
    }

    private static int getFirstNonZeroIndex(int[] arr, int index) {
        int i = index;
        while (i < arr.length && arr[i] == 0) {
            i++;
        }
        return i;
    }
}
