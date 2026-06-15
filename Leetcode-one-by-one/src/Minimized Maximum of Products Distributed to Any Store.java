// https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/description/

class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 1;
        int right = 0;

        for (int q : quantities) {
            right = Math.max(right, q);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            long storesNeeded = 0;
            for (int q : quantities) {
                storesNeeded += (q + mid - 1) / mid;
            }

            if (storesNeeded <= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
