import java.util.*;

class Solution {
    public int primeSubarray(int[] nums, int k) {
        int n = nums.length;
        int maxVal = Arrays.stream(nums).max().getAsInt();
        boolean[] isPrime = sieve(maxVal);

        int total = 0;

        for (int i = 0; i < n; i++) {
            int minP = Integer.MAX_VALUE, maxP = Integer.MIN_VALUE, primeCount = 0;

            for (int j = i; j < n; j++) {
                int val = nums[j];
                if (isPrime[val]) {
                    primeCount++;
                    minP = Math.min(minP, val);
                    maxP = Math.max(maxP, val);
                }

                if (primeCount >= 2 && (maxP - minP) <= k) {
                    total++;
                }
            }
        }

        return total;
    }

    private boolean[] sieve(int maxVal) {
        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= maxVal; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= maxVal; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}
