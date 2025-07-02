class Solution {
    public void solveSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] subgrids = new int[9];

        // Preenche os arrays de controle
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    rows[i] |= (1 << num);  // Marca o número na linha
                    cols[j] |= (1 << num);  // Marca o número na coluna
                    subgrids[(i / 3) * 3 + (j / 3)] |= (1 << num); // Marca o número no subgrid
                }
            }
        }

        solve(board, rows, cols, subgrids);
    }

    private boolean solve(char[][] board, int[] rows, int[] cols, int[] subgrids) {
        // Encontra a próxima célula vazia
        int minOptions = Integer.MAX_VALUE;
        int row = -1, col = -1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    // Conta quantas opções são válidas para a célula
                    int availableOptions = countAvailableOptions(i, j, rows, cols, subgrids);
                    if (availableOptions < minOptions) {
                        minOptions = availableOptions;
                        row = i;
                        col = j;
                    }
                }
            }
        }

        // Se não há mais células vazias, estamos terminados
        if (row == -1) {
            return true;
        }

        // Tenta os números válidos para a célula encontrada
        for (int num = 0; num < 9; num++) {
            if (isValid(rows, cols, subgrids, row, col, num)) {
                // Marca o número como válido
                board[row][col] = (char) (num + '1');
                rows[row] |= (1 << num);
                cols[col] |= (1 << num);
                subgrids[(row / 3) * 3 + (col / 3)] |= (1 << num);

                // Recursivamente tenta resolver o tabuleiro
                if (solve(board, rows, cols, subgrids)) {
                    return true;
                }

                // Se falhar, desfaz as alterações (backtrack)
                board[row][col] = '.';
                rows[row] &= ~(1 << num);
                cols[col] &= ~(1 << num);
                subgrids[(row / 3) * 3 + (col / 3)] &= ~(1 << num);
            }
        }
        return false;
    }

    private int countAvailableOptions(int row, int col, int[] rows, int[] cols, int[] subgrids) {
        int options = 0;
        for (int num = 0; num < 9; num++) {
            if (isValid(rows, cols, subgrids, row, col, num)) {
                options++;
            }
        }
        return options;
    }

    private boolean isValid(int[] rows, int[] cols, int[] subgrids, int row, int col, int num) {
        return (rows[row] & (1 << num)) == 0 && (cols[col] & (1 << num)) == 0 && (subgrids[(row / 3) * 3 + (col / 3)] & (1 << num)) == 0;
    }
}
