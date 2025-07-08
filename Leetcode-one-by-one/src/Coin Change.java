import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // Increase amount by 1 to handle 0-based indexing
        int n = amount + 1;
        // Initialize dp array with max value
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Base case: 0 coins needed to make 0 amount

        // Sort coins for early break optimization
        Arrays.sort(coins);

        for (int i = 1; i < n; i++) {
            for (int c : coins) {
                if (i - c < 0) break;
                if (dp[i - c] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
