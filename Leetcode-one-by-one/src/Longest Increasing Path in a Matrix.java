class Solution {
    private int[][] matrix;
    private int[][] dp;
    private int m, n;
    private final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.dp = new int[m][n];

        int maxLen = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxLen = Math.max(maxLen, dfs(i, j));
            }
        }
        return maxLen;
    }

    private int dfs(int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];

        int max = 1; // minimum path length is 1 (itself)
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] > matrix[i][j]) {
                max = Math.max(max, 1 + dfs(ni, nj));
            }
        }
        dp[i][j] = max;
        return max;
    }
}
