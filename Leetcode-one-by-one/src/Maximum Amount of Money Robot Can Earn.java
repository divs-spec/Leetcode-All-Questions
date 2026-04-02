class Solution {
    public int maximumAmount(int[][] coins) {
        int n = coins.length, m = coins[0].length;
        long NEG = (long)-1e18;

        long[][][] dp = new long[n][m][3];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < 3; k++)
                    dp[i][j][k] = NEG;

        // start
        for (int k = 0; k < 3; k++) {
            if (coins[0][0] >= 0) {
                dp[0][0][k] = coins[0][0];
            } else {
                dp[0][0][k] = (k > 0) ? 0 : coins[0][0];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 && j == 0) continue;

                for (int k = 0; k < 3; k++) {
                    long val = coins[i][j];

                    // from top
                    if (i > 0) {
                        // no neutralize
                        if (dp[i-1][j][k] != NEG) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k] + val);
                        }

                        // neutralize
                        if (val < 0 && k > 0 && dp[i-1][j][k-1] != NEG) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k-1]);
                        }
                    }

                    // from left
                    if (j > 0) {
                        if (dp[i][j-1][k] != NEG) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k] + val);
                        }

                        if (val < 0 && k > 0 && dp[i][j-1][k-1] != NEG) {
                            dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k-1]);
                        }
                    }
                }
            }
        }

        return (int)Math.max(dp[n-1][m-1][0],
               Math.max(dp[n-1][m-1][1], dp[n-1][m-1][2]));
    }
}
