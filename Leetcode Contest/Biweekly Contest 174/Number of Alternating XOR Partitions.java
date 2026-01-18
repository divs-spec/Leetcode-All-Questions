import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int alternatingXOR(int[] nums, int target1, int target2) {
        int n = nums.length;

        long[] dp1 = new long[n + 1]; // last block = target1
        long[] dp2 = new long[n + 1]; // last block = target2

        Map<Integer, Long> map1 = new HashMap<>();
        Map<Integer, Long> map2 = new HashMap<>();

        int px = 0;

        for (int i = 1; i <= n; i++) {
            px ^= nums[i - 1];

            // block XOR = target1
            long ways1 = map2.getOrDefault(px ^ target1, 0L);
            if (px == target1) ways1++; // single-block from start
            dp1[i] = ways1 % MOD;

            // block XOR = target2 (must come after target1)
            dp2[i] = map1.getOrDefault(px ^ target2, 0L) % MOD;

            map1.put(px, (map1.getOrDefault(px, 0L) + dp1[i]) % MOD);
            map2.put(px, (map2.getOrDefault(px, 0L) + dp2[i]) % MOD);
        }

        // valid partitions must end with either target1 or target2
        return (int)((dp1[n] + dp2[n]) % MOD);
    }
}
