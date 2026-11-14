import java.util.Arrays;

public class ArrayProblems {

    /*
     * EASY: Find the Maximum
     * Given an array of integers, return the largest value in it.
     * The array always has at least one element and may contain negative numbers.
     *
     * Example 1: [3, 9, 2, 7]   -> 9
     * Example 2: [-5, -2, -8]   -> -2
     */
    public static int findMax(int[] nums) {
        int max = nums[0];
        for (int n : nums) {
            if (n > max) max = n;
        }
        return max;
    }

    /*
     * EASY: Reverse an Array (in place)
     * Reverse the order of the elements in the array.
     * Modify the given array directly; do not create a new array.
     *
     * Example 1: [1, 2, 3, 4, 5] -> [5, 4, 3, 2, 1]
     * Example 2: [7, 8]          -> [8, 7]
     */
    public static void reverse(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /*
     * EASY: Move Zeros
     * Move every 0 in the array to the end, while keeping the non-zero
     * numbers in the same relative order. Modify the array in place.
     *
     * Example 1: [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
     * Example 2: [0, 0, 5]        -> [5, 0, 0]
     */
    public static void moveZeros(int[] nums) {
        int writeIndex = 0;
        for (int n : nums) {
            if (n != 0) nums[writeIndex++] = n;
        }
        while (writeIndex < nums.length) nums[writeIndex++] = 0;
    }

    public static void run() {
        IO.println("===== ARRAYS =====");

        IO.println("findMax([3, 9, 2, 7]) = " + findMax(new int[]{3, 9, 2, 7}));
        IO.println("findMax([-5, -2, -8]) = " + findMax(new int[]{-5, -2, -8}));

        int[] a = {1, 2, 3, 4, 5};
        reverse(a);
        IO.println("reverse([1, 2, 3, 4, 5]) = " + Arrays.toString(a));

        int[] b = {0, 1, 0, 3, 12};
        moveZeros(b);
        IO.println("moveZeros([0, 1, 0, 3, 12]) = " + Arrays.toString(b));

        IO.println();
    }
}
