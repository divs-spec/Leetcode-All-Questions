//Approach no 1
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);       // Odd length palindrome
            int len2 = expandFromCenter(s, i, i + 1);   // Even length palindrome
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of palindrome
    }
}





//Approach no 2

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        // Step 1: Transform the string to handle even-length palindromes
        StringBuilder t = new StringBuilder();
        t.append('^'); // Starting boundary
        for (int i = 0; i < s.length(); i++) {
            t.append('#');
            t.append(s.charAt(i));
        }
        t.append("#$"); // Ending boundary

        char[] arr = t.toString().toCharArray();
        int[] p = new int[arr.length]; // p[i] = radius of palindrome around center i
        int center = 0, right = 0;     // Current center and right boundary
        int maxLen = 0, centerIndex = 0;

        // Step 2: Core loop
        for (int i = 1; i < arr.length - 1; i++) {
            int mirror = 2 * center - i; // Mirror position of i around current center

            if (i < right)
                p[i] = Math.min(right - i, p[mirror]); // Minimize unnecessary expansion

            // Try to expand palindrome centered at i
            while (arr[i + (1 + p[i])] == arr[i - (1 + p[i])])
                p[i]++;

            // Update center and right boundary if expanded beyond right
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }

            // Track longest palindrome found
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        // Step 3: Extract the actual substring
        int start = (centerIndex - maxLen) / 2; // map back to original string indices
        return s.substring(start, start + maxLen);
    }
}





//Approach no 3

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;

        // Every single character is a palindrome
        for (int i = 0; i < n; i++) dp[i][i] = true;

        // Check for substring of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // Check for substrings of length >= 3
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // Ending index

                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }
}
