class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeros = new int[n];

        // Step 1: Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                count++;
            }
            trailingZeros[i] = count;
        }

        int swaps = 0;

        // Step 2: Greedy row placement
        for (int i = 0; i < n; i++) {
            int required = n - i - 1;
            int j = i;

            // Find row with enough trailing zeros
            while (j < n && trailingZeros[j] < required) {
                j++;
            }

            // If none found, impossible
            if (j == n) {
                return -1;
            }

            // Bring row j up to position i
            while (j > i) {
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;
                swaps++;
                j--;
            }
        }

        return swaps;
    }
}
