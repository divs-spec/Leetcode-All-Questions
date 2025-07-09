class Solution {
    public int totalNQueens(int n) {
        // Create an n x n chessboard
        boolean[][] board = new boolean[n][n];
        
        // Start placing queens from row 0
        int count = NQueens(board, 0);
        return count;
    }

    // Recursive method to try placing queens row by row
    private int NQueens(boolean[][] board, int row){
        int n = board.length;
        
        // Base case: if we placed queens in all rows, count this solution
        if(row == n){
            return 1;
        }

        int count = 0;

        // Try placing queen in every column of the current row
        for(int col = 0; col < n; col++){
            if(isSafe(board, row, col)){
                board[row][col] = true; // Place queen
                count += NQueens(board, row + 1); // Recurse to next row
                board[row][col] = false; // Backtrack
            }
        }

        return count;
    }

    // Check if placing a queen at (row, col) is safe
    private static boolean isSafe(boolean[][] board, int row, int col) {
        // Check vertical column
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // Check upper left diagonal
        for (int i = 1; i <= Math.min(row, col); i++) {
            if (board[row - i][col - i]) {
                return false;
            }
        }

        // Check upper right diagonal
        for (int i = 1; i <= Math.min(row, board.length - 1 - col); i++) {
            if (board[row - i][col + i]) {
                return false;
            }
        }

        return true; // Safe to place the queen
    }
}
