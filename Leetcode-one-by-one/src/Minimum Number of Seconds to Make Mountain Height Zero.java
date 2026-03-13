class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 0, right = (long)1e18;

        while (left < right) {
            long mid = (left + right) / 2;

            if (can(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean can(long time, int height, int[] workers) {
        long total = 0;

        for (int t : workers) {
            long val = time / t;
            long x = (long)((Math.sqrt(1 + 8.0 * val) - 1) / 2);
            total += x;

            if (total >= height) return true;
        }

        return false;
    }
}
