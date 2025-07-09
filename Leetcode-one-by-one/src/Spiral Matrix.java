class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> a = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;

        int c = 0;
        int total = row * col;

        int startingrow = 0;
        int startingcol = 0;
        int endingrow = row - 1;
        int endingcol = col - 1;

        while (c < total) {
            for (int i = startingcol; c < total && i <= endingcol; i++) {
                a.add(matrix[startingrow][i]);
                c++;
            }
            startingrow++;
            for (int i = startingrow; c < total && i <= endingrow; i++) {
                a.add(matrix[i][endingcol]);
                c++;
            }
            endingcol--;
            for (int i = endingcol; c < total && i >= startingcol; i--) {
                a.add(matrix[endingrow][i]);
                c++;
            }
            endingrow--;
            for (int i = endingrow; c < total && i >= startingrow; i--) {
                a.add(matrix[i][startingcol]);
                c++;
            }
            startingcol++;
        }
        return a;
    }
}
