class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        final long[] rowSum = new long[grid.length];
        final long[] colSum = new long[grid[0].length];
        long sum = 0;
        for (int i = 0; i < rowSum.length; ++i) {
            for (int j = 0; j < colSum.length; ++j) {
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
            }
            sum += rowSum[i];
        }
        if ((sum % 2) != 0) {
            return false;
        }
        sum /= 2;
        long total = 0;
        for (int i = 0; i < rowSum.length - 1 && total < sum; ++i) {
            total += rowSum[i];
        }
        if (total == sum) {
            return true;
        }
        total = 0;
        for (int i = 0; i < colSum.length - 1 && total < sum; ++i) {
            total += colSum[i];
        }
        return total == sum;
    }
}
