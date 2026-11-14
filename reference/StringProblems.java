import java.util.HashMap;
import java.util.Map;

public class StringProblems {

    // EASY: Reverse a string
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // EASY: Check if a string is a palindrome (ignores case and non-alphanumerics)
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // EASY: Count vowels
    public static int countVowels(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    // MEDIUM: Length of the longest substring without repeating characters (sliding window)
    public static int longestUniqueSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int best = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= start) {
                start = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, end);
            best = Math.max(best, end - start + 1);
        }
        return best;
    }

    // MEDIUM: Longest palindromic substring (expand around center) - O(n^2)
    public static String longestPalindrome(String s) {
        if (s.isEmpty()) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // MEDIUM: String compression ("aaabbc" -> "a3b2c1"); return original if not shorter
    public static String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int count = 0;
            while (i < s.length() && s.charAt(i) == c) {
                i++;
                count++;
            }
            sb.append(c).append(count);
        }
        return sb.length() < s.length() ? sb.toString() : s;
    }

    public static void run() {
        IO.println("===== STRINGS =====");
        IO.println("reverse(\"hello\") = " + reverse("hello"));
        IO.println("isPalindrome(\"A man, a plan, a canal: Panama\") = "
                + isPalindrome("A man, a plan, a canal: Panama"));
        IO.println("countVowels(\"Technical Assessment\") = " + countVowels("Technical Assessment"));
        IO.println("longestUniqueSubstring(\"abcabcbb\") = " + longestUniqueSubstring("abcabcbb"));
        IO.println("longestPalindrome(\"babad\") = " + longestPalindrome("babad"));
        IO.println("compress(\"aaabbbbcc\") = " + compress("aaabbbbcc"));
        IO.println();
    }
}
