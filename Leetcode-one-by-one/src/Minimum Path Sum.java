class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int dp[][]= new int[n][m];
         for (int[] row : dp) {
        Arrays.fill(row, -1);
    }
        // return solvewithdp(grid, n - 1, m - 1,dp);
        // return tabulation(grid, n, m,dp);
        return space_optimization(grid, grid.length, grid[0].length);

    }

    public int solve(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) return grid[0][0];
        if (i < 0 || j < 0) return Integer.MAX_VALUE;

        int up = solve(grid, i - 1, j);
        int left = solve(grid, i, j - 1);

        return grid[i][j] + Math.min(up, left);
    }

    // memoization
    public int solvewithdp(int[][] grid, int i, int j, int dp[][]) {
        if (i == 0 && j == 0) return grid[0][0];
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if(dp[i][j] != -1) return dp[i][j];
        int up = solve(grid, i - 1, j);
        int left = solve(grid, i, j - 1);

        return dp[i][j]=grid[i][j] + Math.min(up, left);
    }


    public int tabulation(int[][] grid, int n, int m, int[][] dp) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (i == 0 && j == 0) {
                dp[i][j] = grid[i][j];
            } else {
                int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
                if (i > 0) up = dp[i - 1][j] + grid[i][j];
                if (j > 0) left = dp[i][j - 1] + grid[i][j];
                dp[i][j] = Math.min(up, left);
            }
        }
    }
    return dp[n - 1][m - 1];
}


public int space_optimization(int[][] grid, int n, int m) {
    int[] prev = new int[m];

    for (int i = 0; i < n; i++) {
        int[] curr = new int[m];
        for (int j = 0; j < m; j++) {
            if (i == 0 && j == 0) {
                curr[j] = grid[i][j];
            } else {
                int up = i > 0 ? prev[j] : Integer.MAX_VALUE;
                int left = j > 0 ? curr[j - 1] : Integer.MAX_VALUE;
                curr[j] = grid[i][j] + Math.min(up, left);
            }
        }
        prev = curr;
    }

    return prev[m - 1];
}


}
