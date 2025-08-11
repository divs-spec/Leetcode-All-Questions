import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        // Step 1: Build powers array
        List<Long> powersList = new ArrayList<>();
        for (int bit = 0; bit < 31; bit++) {
            if ((n & (1 << bit)) != 0) {
                powersList.add(1L << bit);
            }
        }

        int m = powersList.size();
        long[] prefix = new long[m];
        prefix[0] = powersList.get(0) % MOD;
        for (int i = 1; i < m; i++) {
            prefix[i] = (prefix[i - 1] * powersList.get(i)) % MOD;
        }

        // Step 2: Answer queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            if (l == 0) {
                ans[i] = (int)(prefix[r] % MOD);
            } else {
                long val = (prefix[r] * modPow(prefix[l - 1], MOD - 2, MOD)) % MOD;
                ans[i] = (int) val;
            }
        }
        return ans;
    }

    // Modular exponentiation
    private long modPow(long base, long exp, long mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
