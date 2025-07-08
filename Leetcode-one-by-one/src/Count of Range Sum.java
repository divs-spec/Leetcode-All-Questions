class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return countWhileMergeSort(prefix, 0, prefix.length, lower, upper);
    }

    private int countWhileMergeSort(long[] sum, int left, int right, int lower, int upper) {
        if (right - left <= 1) return 0;

        int mid = (left + right) / 2;
        int count = countWhileMergeSort(sum, left, mid, lower, upper) +
                    countWhileMergeSort(sum, mid, right, lower, upper);

        int j = mid, k = mid, t = mid;
        int r = 0;
        long[] cache = new long[right - left];

        for (int i = left, c = 0; i < mid; i++, c++) {
            // Count j such that sum[j] - sum[i] >= lower
            while (k < right && sum[k] - sum[i] < lower) k++;
            // Count j such that sum[j] - sum[i] <= upper
            while (j < right && sum[j] - sum[i] <= upper) j++;
            count += j - k;

            // Merge step (keep sorting sum[])
            while (t < right && sum[t] < sum[i]) cache[c++] = sum[t++];
            cache[c] = sum[i];
        }

        // Copy back the merged part
        System.arraycopy(cache, 0, sum, left, t - left);

        return count;
    }
}
