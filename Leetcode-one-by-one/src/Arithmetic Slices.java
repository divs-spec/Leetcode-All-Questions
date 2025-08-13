class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) return 0;
        
        int total = 0;
        int dp = 0; // dp[i], but we just keep the last value
        
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp = dp + 1; // extend previous sequences
                total += dp;
            } else {
                dp = 0; // reset
            }
        }
        return total;
    }
}
