class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Prefix sums
        int[][] row = new int[m][n + 1];
        int[][] col = new int[m + 1][n];
        int[][] diag1 = new int[m][n];
        int[][] diag2 = new int[m][n];

        // Build prefix sums
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i][j + 1] = row[i][j] + grid[i][j];
                col[i + 1][j] = col[i][j] + grid[i][j];

                diag1[i][j] = grid[i][j] +
                        ((i > 0 && j > 0) ? diag1[i - 1][j - 1] : 0);

                diag2[i][j] = grid[i][j] +
                        ((i > 0 && j + 1 < n) ? diag2[i - 1][j + 1] : 0);
            }
        }

        int answer = 1;

        // Try all possible square sizes
        for (int k = 2; k <= Math.min(m, n); k++) {
            for (int i = 0; i + k <= m; i++) {
                for (int j = 0; j + k <= n; j++) {

                    int target = rowSum(row, i, j, j + k);
                    boolean valid = true;

                    // Check rows and columns
                    for (int t = 0; t < k; t++) {
                        if (rowSum(row, i + t, j, j + k) != target ||
                            colSum(col, j + t, i, i + k) != target) {
                            valid = false;
                            break;
                        }
                    }

                    if (!valid) continue;

                    // Check diagonals
                    int mainDiag =
                            diag1[i + k - 1][j + k - 1] -
                            ((i > 0 && j > 0) ? diag1[i - 1][j - 1] : 0);

                    int antiDiag =
                            diag2[i + k - 1][j] -
                            ((i > 0 && j + k < n) ? diag2[i - 1][j + k] : 0);

                    if (mainDiag == target && antiDiag == target) {
                        answer = k;
                    }
                }
            }
        }

        return answer;
    }

    private int rowSum(int[][] row, int r, int c1, int c2) {
        return row[r][c2] - row[r][c1];
    }

    private int colSum(int[][] col, int c, int r1, int r2) {
        return col[r2][c] - col[r1][c];
    }
}
