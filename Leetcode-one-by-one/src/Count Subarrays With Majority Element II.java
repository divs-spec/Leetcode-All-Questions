import java.util.*;

class Solution {
    static class Fenwick {
        long[] bit;

        Fenwick(int n) {
            bit = new long[n + 2];
        }

        void add(int idx, long val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long res = 0;
            while (idx > 0) {
                res += bit[idx];
                idx -= idx & -idx;
            }
            return res;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] == target ? 1 : -1);
        }

        long[] vals = pref.clone();
        Arrays.sort(vals);

        Map<Long, Integer> rank = new HashMap<>();
        int idx = 1;
        for (long v : vals) {
            if (!rank.containsKey(v)) {
                rank.put(v, idx++);
            }
        }

        Fenwick fw = new Fenwick(idx + 2);
        long ans = 0;

        for (long p : pref) {
            int r = rank.get(p);

            ans += fw.query(r - 1); // count previous prefix sums < p

            fw.add(r, 1);
        }

        return ans;
    }
}
