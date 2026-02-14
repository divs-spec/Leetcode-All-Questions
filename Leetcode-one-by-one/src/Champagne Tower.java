class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        if (query_row == 0)
            return Math.min(1, poured);

        // symmetry: (r, g) == (r, r - g)
        query_glass = Math.min(query_glass, query_row - query_glass);

        // dp[c] = amount in glass c of the current row (not capped at 1)
        // +1 extra so dp[c+1] is always safe to update
        double[] dp = new double[query_glass + 2];
        dp[0] = (double) poured;

        for (int r = 0; r < query_row; r++) {
            int end = Math.min(r, query_glass);
            dp[end + 1] = 0.0;  // rightmost cell of the next row in our tracked region

            int next_lo = 1000000000, next_hi = -1;
            boolean pouring = false;

            // Build next row from current in-place
            for (int c = end; c >= 0; --c) {
                double x = dp[c];
                if (x > 1.0) {  // If we have overflow then pour it to the next row
                    pouring = true;
                    double overflow = (x - 1.0) * 0.5;
                    dp[c] = overflow;
                    dp[c + 1] += overflow;

                    // Track next glasses that are within 0..query_glass (tracked side)
                    if (c < next_lo) next_lo = c;
                    if (c > next_hi) next_hi = c;
                    if (c + 1 <= query_glass && c + 1 > next_hi) next_hi = c + 1;

                } else  // No overflow, then next row will not
                    dp[c] = 0.0;
            }

            // Early stop 1: nothing flows further
            if (!pouring)
                return 0.0;

            // Early stop 2: query_glass is outside the maximum possible spread window
            // From [next_lo..next_hi] at row r+1, you can expand by at most 1 per remaining row.
            int rem = query_row - (r + 1);
            if (query_glass < next_lo - rem || query_glass > next_hi + rem)
                return 0.0;
        }

        return Math.min(1.0, dp[query_glass]);
    }
}
