class Solution {
    int dp[][];

    public boolean isMatch(String s, String p) {
        int l1 = s.length();
        int l2 = p.length();
        
        // Initialize memoization table with -1
        dp = new int[l1 + 1][l2 + 1];
        for (int[] x : dp) Arrays.fill(x, -1);

        // Start recursive check from end of both strings
        return solve(l1, l2, s, p) == 1;
    }

    int solve(int i, int j, String s, String p) {
        // If both string and pattern are empty
        if (i <= 0 && j <= 0) return 1;

        // If string is empty but pattern is not
        if (i <= 0 && j > 0) {
            // Check if all characters in pattern so far are '*'
            for (int x = 1; x <= j; x++) {
                if (p.charAt(x - 1) != '*') return dp[i][j] = 0;
            }
            return dp[i][j] = 1;
        }

        // If pattern is empty but string is not
        if (i > 0 && j <= 0) return dp[i][j] = 0;

        // Return memoized result if already computed
        if (dp[i][j] != -1) return dp[i][j];

        // If characters match or pattern has '?'
        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
            return dp[i][j] = solve(i - 1, j - 1, s, p);
        }

        // If pattern has '*', match with:
        // 1. One character in string (i - 1, j)
        // 2. Zero characters in string (i, j - 1)
        if (p.charAt(j - 1) == '*') {
            return dp[i][j] = (solve(i - 1, j, s, p) == 1 || solve(i, j - 1, s, p) == 1) ? 1 : 0;
        }

        // Characters do not match
        return dp[i][j] = 0;
    }
}
