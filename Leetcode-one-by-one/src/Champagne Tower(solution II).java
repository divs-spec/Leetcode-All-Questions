class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] dp = new double[query_row + 2];
        dp[0] = poured;

        for (int r = 0; r < query_row; r++) {
            for (int c = r; c >= 0; c--) {
                double overflow = Math.max(0.0, dp[c] - 1.0) / 2.0;
                dp[c] = overflow;        // left child for next row
                dp[c + 1] += overflow;  // right child for next row
            }
        }

        return Math.min(1.0, dp[query_glass]);
    }
}
