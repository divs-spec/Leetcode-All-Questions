class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;

        int[][] arr = new int[m + 2][2];
        arr[0][0] = 1;
        arr[0][1] = 0;

        for (int i = 0; i < m; i++) {
            arr[i + 1] = restrictions[i];
        }

        arr[m + 1][0] = n;
        arr[m + 1][1] = n - 1;

        java.util.Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            arr[i][1] = Math.min(
                arr[i][1],
                arr[i - 1][1] + (arr[i][0] - arr[i - 1][0])
            );
        }

        for (int i = len - 2; i >= 0; i--) {
            arr[i][1] = Math.min(
                arr[i][1],
                arr[i + 1][1] + (arr[i + 1][0] - arr[i][0])
            );
        }

        long ans = 0;

        for (int i = 0; i < len - 1; i++) {
            long x1 = arr[i][0];
            long h1 = arr[i][1];

            long x2 = arr[i + 1][0];
            long h2 = arr[i + 1][1];

            long dist = x2 - x1;

            long peak = (h1 + h2 + dist) / 2;
            ans = Math.max(ans, peak);
        }

        return (int) ans;
    }
}
