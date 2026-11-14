import java.util.Arrays;

public class ArrayProblems {

    // EASY: Find the maximum value in an array
    public static int findMax(int[] nums) {
        int max = nums[0];
        for (int n : nums) {
            if (n > max) max = n;
        }
        return max;
    }

    // EASY: Reverse an array in place (two pointers)
    public static void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // EASY: Move all zeros to the end, keeping order of non-zeros
    public static void moveZeros(int[] nums) {
        int insertPos = 0;
        for (int n : nums) {
            if (n != 0) nums[insertPos++] = n;
        }
        while (insertPos < nums.length) nums[insertPos++] = 0;
    }

    // MEDIUM: Maximum subarray sum (Kadane's algorithm) - O(n)
    public static int maxSubarraySum(int[] nums) {
        int best = nums[0], current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            best = Math.max(best, current);
        }
        return best;
    }

    // MEDIUM: Rotate array to the right by k steps - O(n) time, O(1) space
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverseRange(nums, 0, nums.length - 1);
        reverseRange(nums, 0, k - 1);
        reverseRange(nums, k, nums.length - 1);
    }

    private static void reverseRange(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }

    // MEDIUM: Product of array except self (no division) - O(n)
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];   // product of everything to the left
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right;                        // times product of everything to the right
            right *= nums[i];
        }
        return result;
    }

    public static void run() {
        IO.println("===== ARRAYS =====");

        IO.println("findMax([3, 9, 2, 7]) = " + findMax(new int[]{3, 9, 2, 7}));

        int[] rev = {1, 2, 3, 4, 5};
        reverse(rev);
        IO.println("reverse([1,2,3,4,5]) = " + Arrays.toString(rev));

        int[] zeros = {0, 1, 0, 3, 12};
        moveZeros(zeros);
        IO.println("moveZeros([0,1,0,3,12]) = " + Arrays.toString(zeros));

        IO.println("maxSubarraySum([-2,1,-3,4,-1,2,1,-5,4]) = "
                + maxSubarraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        int[] rot = {1, 2, 3, 4, 5, 6, 7};
        rotate(rot, 3);
        IO.println("rotate([1..7], 3) = " + Arrays.toString(rot));

        IO.println("productExceptSelf([1,2,3,4]) = "
                + Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        IO.println();
    }
}
