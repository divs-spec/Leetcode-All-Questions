class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false; // odd sum cannot be partitioned

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // sum 0 is always possible

        for (int num : nums) {
            // iterate backwards to avoid reusing same number in this round
            for (int s = target; s >= num; s--) {
                if (dp[s - num]) {
                    dp[s] = true;
                }
            }
        }
        return dp[target];
    }
}
