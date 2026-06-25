import java.util.Arrays;

class Solution {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);

        long ans = 0;
        long cur = 1;

        for (int num : nums) {
            if (num < cur) {
                continue;
            }

            if (cur < num) {
                long cnt = Math.min((long) k, (long) num - cur);
                long last = cur + cnt - 1;
                ans += (cur + last) * cnt / 2;
                k -= (int) cnt;
                if (k == 0) {
                    return ans;
                }
            }

            cur = (long) num + 1;
        }

        if (k > 0) {
            long last = cur + k - 1L;
            ans += (cur + last) * k / 2;
        }

        return ans;
    }
}
