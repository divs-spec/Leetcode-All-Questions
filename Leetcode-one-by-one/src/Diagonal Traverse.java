class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];
        
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        
        int row = 0, col = 0;
        boolean up = true; // direction flag
        int idx = 0;
        
        while (idx < m * n) {
            result[idx++] = mat[row][col];
            
            if (up) { // moving up-right
                if (col == n - 1) { // hit right boundary
                    row++;
                    up = false;
                } else if (row == 0) { // hit top boundary
                    col++;
                    up = false;
                } else {
                    row--;
                    col++;
                }
            } else { // moving down-left
                if (row == m - 1) { // hit bottom boundary
                    col++;
                    up = true;
                } else if (col == 0) { // hit left boundary
                    row++;
                    up = true;
                } else {
                    row++;
                    col--;
                }
            }
        }
        
        return result;
    }
}
