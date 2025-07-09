class Solution {
    public static int help(int i,int j,int m,int n,int arr[][]){
        if(i==m&&j==n){
            return 1;
        }
        else if(i>m||j>n){
            return 0;
        }
        else if(arr[i][j]!=-1){
            return arr[i][j];
        }
        arr[i][j]=(help(i+1,j,m,n,arr)+help(i,j+1,m,n,arr));
        return arr[i][j];
    }
    public int uniquePaths(int m, int n) {
        int arr[][]=new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(arr[i],-1);
        }
        return help(0,0,m-1,n-1,arr);
    }
}
