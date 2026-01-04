class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for (int x : nums) {
            int sum = 0;
            int count = 0;

            for (int d = 1; d * d <= x; d++) {
                if (x % d == 0) {
                    int e = x / d;

                    if (d == e) {
                        count++;
                        sum += d;
                    } else {
                        count += 2;
                        sum += d + e;
                    }

                    if (count > 4) break;
                }
            }

            if (count == 4) {
                ans += sum;
            }
        }

        return ans;
    }
}
