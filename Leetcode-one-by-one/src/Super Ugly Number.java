//APPROACH NO 1 :

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] ugly = new long[n];  // Change to long
        int[] indices = new int[primes.length];
        long[] next = new long[primes.length];  // Change to long

        Arrays.fill(next, 1);
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;

            for (int j = 0; j < primes.length; j++) {
                next[j] = primes[j] * ugly[indices[j]];
                min = Math.min(min, next[j]);
            }

            ugly[i] = min;

            for (int j = 0; j < primes.length; j++) {
                if (next[j] == min) {
                    indices[j]++;
                }
            }
        }

        return (int) ugly[n - 1]; // Final cast to int is safe as per constraints
    }
}

// APPROACH NO 2 :
// OPTIMIZED ONE :

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] pointers = new int[primes.length];
        long[] next = new long[primes.length];
        long[] ugly = new long[n];

        ugly[0] = 1;

        // Initialize next values as prime * ugly[0] = prime * 1
        for (int i = 0; i < primes.length; i++) {
            next[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, next[j]);
            }
            ugly[i] = min;

            // Advance all pointers whose current value equals min
            for (int j = 0; j < primes.length; j++) {
                if (next[j] == min) {
                    pointers[j]++;
                    next[j] = primes[j] * ugly[pointers[j]];
                }
            }
        }

        return (int) ugly[n - 1];  // Safe because answer fits in 32-bit int
    }
}
