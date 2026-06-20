class Solution {
    public int splitArray(int[] nums, int k) {
        long left = 0, right = 0;

        for (int x : nums) {
            left = Math.max(left, x);
            right += x;
        }

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (int) left;
    }

    private boolean canSplit(int[] nums, int k, long limit) {
        int parts = 1;
        long curr = 0;

        for (int x : nums) {
            if (curr + x > limit) {
                parts++;
                curr = x;
            } else {
                curr += x;
            }
        }

        return parts <= k;
    }
}
