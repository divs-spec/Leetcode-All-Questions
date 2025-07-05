import java.util.*;

class Solution {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        int left = 0, right = n, ans = n;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canLimitStability(nums, maxC, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private boolean canLimitStability(int[] nums, int maxC, int maxLen) {
        int n = nums.length;
        int windowSize = maxLen + 1;
        int changesNeeded = 0;

        for (int i = 0; i <= n - windowSize; i++) {
            int g = nums[i];
            for (int j = i + 1; j < i + windowSize; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) break;
            }
            if (g >= 2) {
                changesNeeded++;
                // We must break this window. Skip overlapping windows
                // to avoid counting multiple changes for overlapping segments
                i = i + windowSize - 1;
            }
        }

        return changesNeeded <= maxC;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
