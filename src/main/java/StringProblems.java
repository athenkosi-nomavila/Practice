public class StringProblems {

    /*
     * EASY: Reverse a String
     * Return a new string with the characters of s in reverse order.
     *
     * Example 1: "hello" -> "olleh"
     * Example 2: "Java"  -> "avaJ"
     * Example 3: ""      -> ""
     */
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /*
     * EASY: Valid Palindrome
     * A palindrome reads the same forwards and backwards.
     * Return true if s is a palindrome, considering only letters and digits
     * and ignoring upper/lower case. Spaces and punctuation are skipped.
     *
     * Example 1: "A man, a plan, a canal: Panama" -> true  ("amanaplanacanalpanama")
     * Example 2: "race a car"                     -> false ("raceacar")
     * Example 3: "Madam"                          -> true
     */
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

    /*
     * EASY: Count Vowels
     * Return how many vowels (a, e, i, o, u) are in s.
     * Count both upper and lower case. 'y' is NOT a vowel here.
     *
     * Example 1: "Technical Assessment" -> 6  (e, i, a, A, e, e)
     * Example 2: "rhythm"               -> 0
     */
    public static int countVowels(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    public static void run() {
        System.out.println("===== STRINGS =====");
        System.out.println("reverse(\"hello\") = " + reverse("hello"));
        System.out.println("isPalindrome(\"A man, a plan, a canal: Panama\") = "
                + isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("isPalindrome(\"race a car\") = " + isPalindrome("race a car"));
        System.out.println("countVowels(\"Technical Assessment\") = " + countVowels("Technical Assessment"));
        System.out.println();
    }
}
