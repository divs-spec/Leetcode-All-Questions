class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        for(int i = 0; i < nums.length;i++)
        hs.add(nums[i]);

        /*int currentSum = nums[0];  // Start with first element
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Start new subarray or continue the existing one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
        */
        int result = 0;
        for (int num : hs) {
            if (num > 0) result += num;
        }

        if (result == 0) {
            result = Collections.max(hs);
        }

        return result;
    }
}
