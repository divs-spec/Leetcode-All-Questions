class Solution {
    public int[][] generateMatrix(int n) {
        int arr[][] = new int[n][n];
        int val = 1;
        // using (n+1)/2 we can find the total no of layers
        int layer = (n+1) / 2;

        // basically there will four possible directions
        for(int i = 0; i < layer; i++){
            // direction 1 - > left to right
            for(int j = i; j < n-i; j++){
                arr[i][j] = val++;
            }

            // direction 2 - > top to bottom
            for(int j = i+1; j < n-i; j++){
                arr[j][n-i-1] = val++;
            }

            // direction 3 - > right to left;
            for(int j = n-i-2; j >= i; j--){
                arr[n-i-1][j] = val++;
            }

            // direction  4 - > bottom to up
            for(int j = n-2-i; j > i; j--){
                arr[j][i] = val++;
            } 

        }
        return arr;
    }
}
