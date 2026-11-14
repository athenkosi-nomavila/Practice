import java.util.*;

public class HashMapProblems {

    // EASY: Two Sum - return indices of the two numbers that add up to target
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) return new int[]{seen.get(need), i};
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // EASY: Valid anagram
    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : a.toCharArray()) counts.put(c, counts.getOrDefault(c, 0) + 1);
        for (char c : b.toCharArray()) {
            int left = counts.getOrDefault(c, 0) - 1;
            if (left < 0) return false;
            counts.put(c, left);
        }
        return true;
    }

    // EASY: First non-repeating character (returns '_' if none)
    public static char firstUniqueChar(String s) {
        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : s.toCharArray()) counts.merge(c, 1, Integer::sum);
        for (Map.Entry<Character, Integer> e : counts.entrySet()) {
            if (e.getValue() == 1) return e.getKey();
        }
        return '_';
    }

    // MEDIUM: Group anagrams together
    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String w : words) {
            char[] chars = w.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }
        return new ArrayList<>(groups.values());
    }

    // MEDIUM: Top K frequent elements (bucket sort) - O(n)
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) buckets.add(new ArrayList<>());
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            buckets.get(e.getValue()).add(e.getKey());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = buckets.size() - 1; i >= 0 && result.size() < k; i--) {
            for (int n : buckets.get(i)) {
                if (result.size() < k) result.add(n);
            }
        }
        return result;
    }

    // MEDIUM: Count subarrays whose sum equals k (prefix sums + hashmap) - O(n)
    public static int subarraySumEqualsK(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int sum = 0, count = 0;
        for (int n : nums) {
            sum += n;
            count += prefixCount.getOrDefault(sum - k, 0);
            prefixCount.merge(sum, 1, Integer::sum);
        }
        return count;
    }

    public static void run() {
        IO.println("===== HASHMAPS =====");
        IO.println("twoSum([2,7,11,15], 9) = " + Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        IO.println("isAnagram(\"listen\", \"silent\") = " + isAnagram("listen", "silent"));
        IO.println("firstUniqueChar(\"swiss\") = " + firstUniqueChar("swiss"));
        IO.println("groupAnagrams([eat,tea,tan,ate,nat,bat]) = "
                + groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        IO.println("topKFrequent([1,1,1,2,2,3], 2) = " + topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        IO.println("subarraySumEqualsK([1,2,3], 3) = " + subarraySumEqualsK(new int[]{1, 2, 3}, 3));
        IO.println();
    }
}
