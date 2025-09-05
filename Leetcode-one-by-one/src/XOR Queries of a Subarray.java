/*class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] a = new int[queries.length];
        for(int i = 0 ; i < queries.length; i++){
            for(int j = queries[i][0]; j <= queries[i][1]; j++){
                a[i] = a[i] ^ arr[j];
            }
        }
        return a;
    }
}*/
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefix = new int[n + 1]; 

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0];
            int R = queries[i][1];
            ans[i] = prefix[R + 1] ^ prefix[L];
        }
        return ans;
    }
}
