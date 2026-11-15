import java.util.*;

public class HashMapProblems {

    /*
     * EASY: Two Sum
     * Given an array of integers and a target, return the indices of the two
     * numbers that add up to the target. Assume exactly one answer exists,
     * and you may not use the same element twice. Return the smaller index first.
     *
     * Example 1: nums = [2, 7, 11, 15], target = 9 -> [0, 1]  (2 + 7 = 9)
     * Example 2: nums = [3, 2, 4],      target = 6 -> [1, 2]  (2 + 4 = 6)
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) return new int[]{seen.get(need), i};
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /*
     * EASY: First Unique Character
     * Return the first character in s that appears exactly once.
     * If every character repeats, return '0'.
     *
     * Example 1: "swiss"    -> 'w'  ('s' repeats, 'w' appears once)
     * Example 2: "leetcode" -> 'l'
     * Example 3: "aabb"     -> '0'
     */
    public static char firstUniqueChar(String s) {
        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : s.toCharArray()) counts.merge(c, 1, Integer::sum);
        for (Map.Entry<Character, Integer> e : counts.entrySet()) {
            if (e.getValue() == 1) return e.getKey();
        }
        return '0';
    }

    public static void run() {
        System.out.println("===== HASHMAPS =====");
        System.out.println("twoSum([2, 7, 11, 15], 9) = " + Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("twoSum([3, 2, 4], 6) = " + Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println("firstUniqueChar(\"swiss\") = " + firstUniqueChar("swiss"));
        System.out.println("firstUniqueChar(\"aabb\") = " + firstUniqueChar("aabb"));
        System.out.println();
    }
}
