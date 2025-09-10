class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 1) ans += helper(mat, i, j);
            }
        }
        return ans;
    }

    private int helper(int[][] mat, int row, int col){
        int i = row;
        int j = col;
        int maxwidth = Integer.MAX_VALUE;
        int ans = 0;
        while(i < mat.length && mat[i][col] == 1){
            while(j < mat[0].length && mat[i][j] == 1 && j < maxwidth){
                j++;
            }  
            maxwidth = Math.min(maxwidth, j);
            ans += (j - col);    
            i++;
            j = col;     
        }
        return ans;
    }
}
