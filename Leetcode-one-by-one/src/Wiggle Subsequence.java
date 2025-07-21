//Approach no 2:
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;

        int up = 1, down = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }
}

// Approach no 1:

import java.util.*;

class Solution {
    public int wiggleMaxLength(int[] nums) {
        List<Integer> v = new ArrayList<>();
        int n = nums.length;

        if (n == 1) return 1;

        // Build trend vector: 1 for up, 0 for down
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] == 0) continue;
            v.add((nums[i + 1] - nums[i] > 0) ? 1 : 0);
        }

        if (v.isEmpty()) return 1;

        int c = v.get(0);
        int count = 2;

        for (int i = 1; i < v.size(); i++) {
            if (c != v.get(i)) {
                c = v.get(i);
                count++;
            }
        }

        return count;
    }
}
