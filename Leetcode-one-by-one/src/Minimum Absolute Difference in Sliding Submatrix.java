import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        if (k == 1) {
            return new int[m][n];
        }

        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

                int[] arr = new int[k * k];
                int idx = 0;

                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        arr[idx++] = grid[x][y];
                    }
                }

                Arrays.sort(arr);

                int minDiff = Integer.MAX_VALUE;

                for (int t = 1; t < arr.length; t++) {
                    if (arr[t] == arr[t - 1]) continue;

                    minDiff = Math.min(minDiff, arr[t] - arr[t - 1]);
                }

                // 🔥 FINAL FIX
                if (minDiff == Integer.MAX_VALUE) {
                    minDiff = 0;
                }

                ans[i][j] = minDiff;
            }
        }

        return ans;
    }
}
